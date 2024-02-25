package com.POG.julindang.common.config;

import com.POG.julindang.common.util.JwtCustomFilter;
import com.POG.julindang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MemberRepository memberRepository;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html/**"))
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                .requestMatchers(new AntPathRequestMatcher("/swagger-resources/**"))
                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**"))
                .requestMatchers(new AntPathRequestMatcher("/no-auth/**"))
                .requestMatchers(new AntPathRequestMatcher("/login/**"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .cors(c -> c.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/member").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/party").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/feed").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/comment").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/alarm").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()    // 어떠한 요청이라도 인증필요
                )
                .cors(c->c.disable())
                .addFilterBefore(new JwtCustomFilter(memberRepository), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}