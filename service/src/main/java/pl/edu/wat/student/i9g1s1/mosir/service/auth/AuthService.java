package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.ClientRepository;
import pl.edu.wat.student.i9g1s1.mosir.UserRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;
import pl.edu.wat.student.i9g1s1.mosir.domain.Client;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.*;
import pl.edu.wat.student.i9g1s1.mosir.service.email.EmailService;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.exception.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtilBean jwtUtilBean;
    private final RegistrationValidationService registrationValidationService;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final EmailService emailService;
    private final AccountOperationTokenService tokenService;
    private final MosirUserDetailsService userDetailsService;

    public AuthResponseDTO authenticate(String username, String password) throws BadCredentialsException{
        MosirUserPrincipal user = (MosirUserPrincipal) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)).getPrincipal();
        return new AuthResponseDTO(true, user.getUser().getIsActive(), jwtUtilBean.generateToken(username),
                user.getAuthorities().iterator().next().getAuthority(),
                user.getUser().getFirstName(), user.getUser().getLastName()
        );
    }

    public RegistrationResponseDTO register(RegistrationRequestDTO registrationRequestDTO) {
        RegistrationResponseDTO ret = new RegistrationResponseDTO();
        Map<String, String> errors = registrationValidationService.validate(registrationRequestDTO);
        if(!errors.isEmpty()) {
            ret.setErrors(errors);
            ret.setSuccess(false);
            return ret;
        }
        MosirUser userToAdd = buildUser(registrationRequestDTO);
        Client usersProfile = new Client();
        usersProfile.setUser(userToAdd);
        clientRepository.save(usersProfile);
        userToAdd.setClient(usersProfile);
        userRepository.save(userToAdd);
        ret.setSuccess(true);
        AccountOperationToken token = tokenService.generate(userToAdd, AccountOperationToken.OperationType.ACTIVATION);
        try {
            emailService.sendNewAccountMail(userToAdd, token);
        } catch (IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    public AccountActivationResponseDTO activateAccount(String tokenString) {
        try {
            AccountOperationToken token = tokenService.validateAndUse(tokenString, AccountOperationToken.OperationType.ACTIVATION);
            MosirUser user = token.getUser();
            if(user.getIsActive())
                throw new UsedOperationTokenException();
            user.setIsActive(true);
            userRepository.save(user);
            return new AccountActivationResponseDTO(true, null);
        } catch (InvalidOperationTokenException e) {
            return new AccountActivationResponseDTO(false, TokenVerificationResponseDTO.TokenStatus.INVALID);
        } catch (ExpiredOperationTokenException e) {
            return new AccountActivationResponseDTO(false, TokenVerificationResponseDTO.TokenStatus.EXPIRED);
        } catch (UsedOperationTokenException e) {
            return new AccountActivationResponseDTO(false, TokenVerificationResponseDTO.TokenStatus.USED);
        } catch (OperationTokenException e) {
            return new AccountActivationResponseDTO(false, TokenVerificationResponseDTO.TokenStatus.DROPPED);
        }
    }

    public void resendActivationMail(String usernameOrEmail) throws UsernameNotFoundException, IllegalStateException, MessagingException, IOException {
        MosirUserPrincipal userPrincipal = (MosirUserPrincipal) userDetailsService.loadUserByUsername(usernameOrEmail);
        if(userPrincipal.getUser().getIsActive())
            throw new IllegalStateException();
        AccountOperationToken token = tokenService.generate(userPrincipal.getUser(), AccountOperationToken.OperationType.ACTIVATION);
        emailService.sendAccountActivationMail(userPrincipal.getUser(), token);
    }

    public MosirUserPrincipal getCurrentUser() {
        return (MosirUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private MosirUser buildUser(RegistrationRequestDTO registrationRequestDTO) {
        MosirUser user = new MosirUser();
        user.setIsActive(false);
        user.setUsername(registrationRequestDTO.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));
        user.setFirstName(registrationRequestDTO.getFirstName());
        user.setLastName(registrationRequestDTO.getLastName());
        user.setGender(registrationRequestDTO.getGender().equalsIgnoreCase("w") ?
                "f" : registrationRequestDTO.getGender().toLowerCase());
        user.setDateOfBirth(LocalDate.parse(registrationRequestDTO.getDateOfBirth()));
        user.setNationalRegistryNumber(registrationRequestDTO.getNationalRegistryNumber());
        user.setEmail(registrationRequestDTO.getEmail());
        user.setPhoneNumber(registrationRequestDTO.getPhoneNumber());
        user.setAddressLine1(registrationRequestDTO.getAddressLine1());
        user.setAddressLine2(registrationRequestDTO.getAddressLine2());
        user.setZipCode(registrationRequestDTO.getZipCode());
        user.setCity(registrationRequestDTO.getCity());
        return user;
    }

}
