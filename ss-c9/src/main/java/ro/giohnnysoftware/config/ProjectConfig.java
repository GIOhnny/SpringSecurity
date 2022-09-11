package ro.giohnnysoftware.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import ro.giohnnysoftware.security.CsrfTokenLoggerFilter;
import ro.giohnnysoftware.security.CustomCsrfTokenRepository;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        //http.csrf().disable(); //GENERALLY DON'T DO THIS, useless for OAuth2
        http.csrf(c-> {
            c.ignoringAntMatchers("/csrfdisabled/**");//disable for certain paths
            //c.csrfTokenRepository(new CustomCsrfTokenRepository()); //Custom CSRF Token
        });

        http.addFilterAfter(new CsrfTokenLoggerFilter(), CsrfFilter.class);
    }
}
