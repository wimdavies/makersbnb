package com.makers.makersbnb.config;

import com.makers.makersbnb.model.User;
import com.makers.makersbnb.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests( request -> {
            request.requestMatchers("/", "/contactus", "/termsandconditions", "/team").permitAll();
            request.anyRequest().authenticated();
        })
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .successHandler(authenticationSuccessHandler())
                                .defaultSuccessUrl("/", true)
                )
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                String name = oAuth2User.getAttribute("name");
                Integer githubId = oAuth2User.getAttribute("id");

                User foundUser = userRepository.findByGithubId(githubId);
                if (foundUser == null) {
                    User user = new User();
                    user.setName(name);
                    user.setGithubId(githubId);
                    userRepository.save(user);
                }
            }
        };
    }
}
