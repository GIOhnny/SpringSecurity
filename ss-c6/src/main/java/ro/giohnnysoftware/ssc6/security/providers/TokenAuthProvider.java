package ro.giohnnysoftware.ssc6.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ro.giohnnysoftware.ssc6.managers.TokenManager;
import ro.giohnnysoftware.ssc6.security.authentications.TokenAuthentication;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        boolean exists = tokenManager.contains(token);

        if (exists) {
            return new TokenAuthentication(token, null, null);
        }
        throw new BadCredentialsException("Invalid token!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
