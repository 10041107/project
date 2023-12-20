/*
Spring Security 구성 (SecurityConfig 클래스):

@EnableWebSecurity 어노테이션을 통해 Spring Security를 활성화하고,
@Configuration 어노테이션으로 설정 클래스임을 나타냅니다.
SecurityConfig 클래스는 WebSecurityConfigurerAdapter를 확장하여 구성되었습니다.
configure 메서드에서는 HTTP 요청에 대한 보안 구성이 이루어집니다.
정적 리소스에 대한 요청은 인증을 필요로하지 않도록
webSecurityCustomizer 빈이 설정됩니다.
 */

package com.ohgiraffers.semilogin.config;

import com.ohgiraffers.semilogin.common.UserAuth;
import com.ohgiraffers.semilogin.config.handler.AuthFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AuthFailHandler authFailHandler;

    // 비밀번호 암호화를 위한 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 정적 리소스에 대한 요청을 무시하기 위한 빈 등록
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // Security 설정을 구성하는 메서드
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            // 특정 URL에 대한 권한 설정
            auth.requestMatchers("/auth/login", "/user/signup", "/auth/fail", "/").permitAll();
            auth.requestMatchers("/admin/*").hasAnyAuthority(UserAuth.ADMIN.getAuth());
            auth.requestMatchers("/user/*").hasAnyAuthority(UserAuth.USER.getAuth());
            auth.anyRequest().authenticated(); // 나머지 요청은 인증된 사용자만 허용
        }).formLogin(login -> {
            // 로그인 페이지 및 로그인 관련 설정
            login.loginPage("/auth/login");
            login.usernameParameter("user");
            login.passwordParameter("pass");
            login.defaultSuccessUrl("/");
            login.failureHandler(authFailHandler); // 로그인 실패 시 핸들러 호출
        }).logout(logout -> {
            // 로그아웃 설정
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/"); // 로그아웃 성공 시 이동할 페이지
        }).sessionManagement(session -> {
            // 세션 관리 설정
            session.maximumSessions(1); // 동시 접속 제한
            session.invalidSessionUrl("/"); // 세션 만료 시 이동할 페이지
        }).csrf(csrf -> csrf.disable()); // CSRF 보안 설정 해제

        return http.build();
    }
}
