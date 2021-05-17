package weatherAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class InitializeData extends WebSecurityConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger(InitializeData.class);

    @Autowired
    LocationService service;

    //Authentication
    //username: user
    //password: password
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Bean
    public CommandLineRunner initDatabase() {

        service.fetchWeatherInfo();

        return args -> {
            log.info("Initialization complete");
        };
    }
}
