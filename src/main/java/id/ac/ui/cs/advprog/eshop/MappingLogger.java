package id.ac.ui.cs.advprog.eshop;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class MappingLogger {

    @Bean
    ApplicationRunner logMappings(RequestMappingHandlerMapping mapping) {
        return args -> mapping.getHandlerMethods().forEach((info, method) ->
                System.out.println(info + " -> " + method)
        );
    }
}