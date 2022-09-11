package ro.giohnnysoftware.springsecurityc2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ro.giohnnysoftware.springsecurityc2.entities.User;
import ro.giohnnysoftware.springsecurityc2.repositories.UserRepository;

import java.util.Optional;

public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        User u = user.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new SecurityUser(u);
    }
}
