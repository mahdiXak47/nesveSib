package NesveSib.Installment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FrontAllowedConfigs implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://a94c6971-6cc5-4bf1-8cb4-aff211be6904.hsvc.ir",
                        "http://a94c6971-6cc5-4bf1-8cb4-aff211be6904.hsvc.ir",
                        "http://ns-ui.mahdiaxak-customer-ns.svc",
                        "https://ns-ui.mahdiaxak-customer-ns.svc",
                        "https://www.yedamanebemabededige.ir",
                        "http://www.yedamanebemabededige.ir"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600); // Cache preflight requests for 1 hour
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "https://a94c6971-6cc5-4bf1-8cb4-aff211be6904.hsvc.ir",
                                "http://a94c6971-6cc5-4bf1-8cb4-aff211be6904.hsvc.ir",
                                "http://ns-ui.mahdiaxak-customer-ns.svc",
                                "https://ns-ui.mahdiaxak-customer-ns.svc"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}