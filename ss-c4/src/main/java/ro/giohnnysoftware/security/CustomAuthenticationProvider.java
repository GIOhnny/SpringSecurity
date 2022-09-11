package ro.giohnnysoftware.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //here you implement the authentication logic
        // if the request is authenticated return a fully authenticated Authentication instance
        //
        // if the request is not authenticated throw AuthenticationException
        //
        // if  the Authentication isn't supported => return null
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        UserDetails u =userDetailsService.loadUserByUsername(username);
        if (u != null) {
            if (passwordEncoder.matches(password, u.getPassword())) {
                var a = new UsernamePasswordAuthenticationToken(username, password, u.getAuthorities());
                return a;
            }
        }

        throw new BadCredentialsException("Error!");
    }

    @Override
    public boolean supports(Class<?> authenticationType) {//type of authentication
        return UsernamePasswordAuthenticationToken.class.equals(authenticationType);
    }
}
