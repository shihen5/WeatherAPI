package weatherAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitializeData {
    private static final Logger log = LoggerFactory.getLogger(InitializeData.class);

    @Autowired
    LocationService service;

    @Bean
    public CommandLineRunner initDatabase() {

        service.fetchWeatherInfo();

        return args -> {
            log.info("Initialization complete");
        };
    }
}
