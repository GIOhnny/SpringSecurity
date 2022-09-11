package ro.giohnnysoftware.ssc6.security.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.giohnnysoftware.ssc6.entities.Otp;
import ro.giohnnysoftware.ssc6.managers.TokenManager;
import ro.giohnnysoftware.ssc6.repositories.OtpRepository;
import ro.giohnnysoftware.ssc6.security.authentications.OtpAuthentication;
import ro.giohnnysoftware.ssc6.security.authentications.UsernamePasswordAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        //Step 1 : username & password
        //Step 2 : username & otp

        var username = request.getHeader("username");
        var password = request.getHeader("password");
        var otp = request.getHeader("otp");

        if (otp == null) {
            //Step 1
            Authentication a = new UsernamePasswordAuthentication(username, password);
            a= authenticationManager.authenticate(a);
            //we generate an otp
            String code = String.valueOf(new Random().nextInt(9999)+1000);

            Otp otpEntity = new Otp();
            otpEntity.setUsername(username);
            otpEntity.setOtp(code);
            otpRepository.save(otpEntity);
        } else {
            //Step 2
            Authentication  a = new OtpAuthentication(username,otp);
            a = authenticationManager.authenticate(a);
            //we issue a token
            var token = UUID.randomUUID().toString();
            tokenManager.add(token);
            response.setHeader("Authorization", token);
            //SecurityContextHolder.getContext().setAuthentication(a);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        //return super.shouldNotFilter(request);
        return !request.getServletPath().equals("/login");
    }
}
