package senla.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@Import({
        HibernateConfig.class,
        LiquibaseConfig.class,
        ObjectMapperConfig.class
})
@EnableWebMvc
@ComponentScan("senla")
public class AppConfig {
}
