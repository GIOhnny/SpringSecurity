package ro.giohnnysoftware.ssc6.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ro.giohnnysoftware.ssc6.security.filters.TokenAuthFilter;
import ro.giohnnysoftware.ssc6.security.filters.UsernamePasswordAuthFilter;
import ro.giohnnysoftware.ssc6.security.providers.OtpAuthProvider;
import ro.giohnnysoftware.ssc6.security.providers.TokenAuthProvider;
import ro.giohnnysoftware.ssc6.security.providers.UsernamePasswordAuthenticationProvider;

@Configuration
@EnableAsync
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernamePasswordAuthenticationProvider authProvider;

    @Autowired
    private OtpAuthProvider otpAuthProvider;

    @Autowired
    private TokenAuthProvider tokenAuthProvider;

    @Bean
    public UsernamePasswordAuthFilter usernamePasswordAuthFilter() {
        return new UsernamePasswordAuthFilter();
    };

    @Bean
    public TokenAuthFilter tokenAuthFilter() {return new TokenAuthFilter();};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.authenticationProvider(authProvider)
            .authenticationProvider(otpAuthProvider)
            .authenticationProvider(tokenAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      //  super.configure(http);
        http.addFilterAt(usernamePasswordAuthFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(tokenAuthFilter(),BasicAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public InitializingBean initializingBean() {
        return () -> {
            SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        };
    }
}
