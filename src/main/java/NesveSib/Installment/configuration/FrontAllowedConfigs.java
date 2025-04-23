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
                    "https://github.com/mahdiXak47/NesveSib-UI-Next.js",
                    "http://ns-ui-554f664447-zdcb7:3000",
                    "registry.hamdocker.ir/mahdiaxak-customer-ns-ui",
                    "http://ns-ui.mahdiaxak-customer-ns.svc"
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
                            "https://github.com/mahdiXak47/NesveSib-UI-Next.js",
                            "http://ns-ui-554f664447-zdcb7:3000",
                            "registry.hamdocker.ir/mahdiaxak-customer-ns-ui",
                            "http://ns-ui.mahdiaxak-customer-ns.svc"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}