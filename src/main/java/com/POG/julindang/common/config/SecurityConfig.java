package com.POG.julindang.common.config;

import com.POG.julindang.admin.member.repository.MemberRepository;
import com.POG.julindang.common.util.JwtCustomFilter;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final MemberRepository memberRepository;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html/**"))
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                .requestMatchers(new AntPathRequestMatcher("/swagger-resources/**"))
                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .cors(c -> {
                    CorsConfigurationSource source = request -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
                        config.setAllowedHeaders(List.of("*"));
                        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                        config.setAllowCredentials(true);
                        return config;
                    };
                    c.configurationSource(source);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()    // 어떠한 요청이라도 인증필요
                )
                .addFilterBefore(new JwtCustomFilter(memberRepository), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}