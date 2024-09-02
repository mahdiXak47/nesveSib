package NesveSib.Installment.security;

import NesveSib.Installment.auth.ApplicationUserService;
import NesveSib.Installment.jwt.JwtUserDetailsAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static NesveSib.Installment.security.ApplicationUserPermission.SELLER_WRITE;
import static NesveSib.Installment.security.ApplicationUserRule.ADMIN;
import static NesveSib.Installment.security.ApplicationUserRule.SELLER;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        http
//                .csrf( csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilter(new JwtUserDetailsAuthenticationFilter(authenticationManager(http)) //TODO debugging filtering jwt requests!
                .authorizeHttpRequests(authorize ->
                        authorize // orders matters in requestMatchers
                                .requestMatchers("/", "index", "/css/*", "/js/*").permitAll()
                                .requestMatchers("/seller-panel/*").hasRole(SELLER.name())
                                .requestMatchers(HttpMethod.POST, "/seller-panel/*").hasAuthority(SELLER_WRITE.getPermission())
                                .requestMatchers(HttpMethod.GET, "seller-panel/*").hasAnyRole(ADMIN.name(), SELLER.name())
                                .anyRequest()
                                .authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/loginPanel")
                        .passwordParameter("password")
                        .usernameParameter("username"))
                .rememberMe(rememberMe -> rememberMe
                        .rememberMeParameter("remember-me") // default is 2 weeks !
                        .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                        .key("somethingverysecure")
                        .rememberMeParameter("remember-me"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // if csrf is enabled this line should be deleted!
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "remember-me")
                        .logoutSuccessUrl("/login"));
//                .formLogin(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        // Create an AuthenticationManagerBuilder to configure the AuthenticationManager
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        // Customize the AuthenticationManagerBuilder as needed (e.g., setting user details service, password encoder)
        authenticationManagerBuilder.userDetailsService(applicationUserService).passwordEncoder(passwordEncoder);

        // Build the AuthenticationManager
        return authenticationManagerBuilder.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        //TODO: creating UserDetailsService dynamically!
//        UserDetails mahdiAkbari = User.builder()
//                .username("mahdixak")
//                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name()) //ROLE_ADMIN
//                .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//
//        UserDetails javadAbdollahi = User.builder()
//                .username("mmdjvd")
//                .password(passwordEncoder.encode("password123"))
//                .roles(SELLER.name())
//                .authorities(SELLER.getGrantedAuthorities())
//                .build();
//
//        UserDetails arshiaSharifi = User.builder()
//                .username("arshiash")
//                .password("password123@")
//                .roles(CUSTOMER.name())
//                .authorities(CUSTOMER.getGrantedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(mahdiAkbari, javadAbdollahi, arshiaSharifi);
//    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
