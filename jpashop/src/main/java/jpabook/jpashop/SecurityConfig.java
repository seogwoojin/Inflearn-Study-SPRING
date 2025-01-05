package jpabook.jpashop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig { // 스프링 필터 역할

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disabling CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").authenticated()  // URLs accessible to authenticated users
                        .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")  // URLs accessible to users with specified roles
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // URLs accessible to users with ADMIN role
                        .anyRequest().permitAll()  // All other requests are permitted without authentication
                )
                .formLogin(form -> form
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/login")  // Login URL
                        .defaultSuccessUrl("/")  // Default success URL after login
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/loginForm")  // Custom login page for OAuth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(principalOauth2UserService)  // Setting the OAuth2 user service
                        )
                );
        return http.build();
    }
}