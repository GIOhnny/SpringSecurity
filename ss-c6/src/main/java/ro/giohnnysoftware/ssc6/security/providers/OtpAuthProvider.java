package ro.giohnnysoftware.ssc6.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ro.giohnnysoftware.ssc6.repositories.OtpRepository;
import ro.giohnnysoftware.ssc6.security.authentications.OtpAuthentication;

import java.util.List;

@Component
public class OtpAuthProvider implements AuthenticationProvider {

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otp = (String) authentication.getCredentials();

        var o = otpRepository.findOtpByUsername(username);
        if (o.isPresent()) {
            return new OtpAuthentication(username, otp, List.of(()->"read"));
        }

        throw new BadCredentialsException("Bad OTP!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}
