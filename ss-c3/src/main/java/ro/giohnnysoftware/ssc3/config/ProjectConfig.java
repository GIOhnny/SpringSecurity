package ro.giohnnysoftware.ssc3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration

public class ProjectConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcUserDetailsManager userDetailsService() {
        // InMemoryUserDetailsManager
        // JdbcUserDetailsManager
        return new JdbcUserDetailsManager(dataSource);
    }

/*
    @Bean
    public DataSource dataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mariadb://??????????????????/spring");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        return dataSource;
    }
*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
