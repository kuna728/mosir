package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.UserRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MosirUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = userRepository.findByUsername(username);
        if(userByUsername.isEmpty()) {
            Optional<User> userByEmail = userRepository.findByEmail(username);
            if(userByEmail.isPresent())
                return new MosirUserPrincipal(userByEmail.get());
            throw new UsernameNotFoundException(username);
        }
        return new MosirUserPrincipal(userByUsername.get());
    }
}
