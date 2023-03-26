package configuration;

import entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MySpringConfiguration {

    @Bean
    @Scope("singleton")
    public Student student(){
        return new Student();
    }
}
