package pl.edu.wat.student.i9g1s1.mosir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.edu.wat.student.i9g1s1.mosir.MosirUserPrincipal;
import pl.edu.wat.student.i9g1s1.mosir.domain.User;
import pl.edu.wat.student.i9g1s1.mosir.repository.UserRepository;

import java.util.Optional;

public class MosirUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException(username);
        return new MosirUserPrincipal(user.get());
    }
}
