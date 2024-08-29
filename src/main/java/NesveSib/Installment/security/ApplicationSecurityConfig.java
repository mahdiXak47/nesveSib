package NesveSib.Installment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static NesveSib.Installment.security.ApplicationUserPermission.SELLER_WRITE;
import static NesveSib.Installment.security.ApplicationUserRule.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf( csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(authorize ->
                        authorize // orders matters in requestMatchers
                                .requestMatchers("/", "index", "/css/*", "/js/*").permitAll()
                                .requestMatchers("/seller-panel/*").hasRole(SELLER.name())
                                .requestMatchers(HttpMethod.POST,"/seller-panel/*").hasAuthority(SELLER_WRITE.getPermission())
                                .requestMatchers(HttpMethod.GET,"seller-panel/*").hasAnyRole(ADMIN.name(),SELLER.name())
                                .anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //TODO: creating UserDetailsService dynamically!
        UserDetails mahdiAkbari = User.builder()
                .username("mahdixak")
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name()) //ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails javadAbdollahi = User.builder()
                .username("mmdjvd")
                .password(passwordEncoder.encode("password123"))
                .roles(SELLER.name())
                .authorities(SELLER.getGrantedAuthorities())
                .build();

        UserDetails arshiaSharifi = User.builder()
                .username("arshiash")
                .password("password123@")
                .roles(CUSTOMER.name())
                .authorities(CUSTOMER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(mahdiAkbari, javadAbdollahi, arshiaSharifi);
    }

}
