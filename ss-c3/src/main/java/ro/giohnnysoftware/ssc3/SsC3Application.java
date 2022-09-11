package ro.giohnnysoftware.ssc3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SsC3Application {

    public static void main(String[] args) {
        SpringApplication.run(SsC3Application.class, args);
    }

}
