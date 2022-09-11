package ro.giohnnysoftware.ssc6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.giohnnysoftware.ssc6.entities.User;
import ro.giohnnysoftware.ssc6.repositories.UserRepository;
import ro.giohnnysoftware.ssc6.security.model.SecurityUser;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var o = userRepository.findUserByUsername(username);
        User u = o.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new SecurityUser(u);
    }
}
