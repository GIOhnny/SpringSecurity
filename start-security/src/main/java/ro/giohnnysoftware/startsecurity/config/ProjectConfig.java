package ro.giohnnysoftware.startsecurity.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import ro.giohnnysoftware.startsecurity.security.PlainTextPasswordEncoder;
import ro.giohnnysoftware.startsecurity.security.UserClass;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        /* Version 1
        return new PlainTextPasswordEncoder();*/
    }

    @Bean
    public UserDetailsManager userDetailsManager () {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("john")
                               .password("12345")
                               .authorities("ADMIN")
                .build();

        manager.createUser(user);
        /*
        //Version 1 manual
        AppUserDetailsManager manager = new AppUserDetailsManager();
        manager.createUser(new UserClass("john","12345"));
        */

        manager.createUser(new UserClass("john","12345"));
        return manager;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
        //http.authorizeRequests().anyRequest().permitAll();
        //super.configure(http);
    }
}
