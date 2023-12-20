/*

로그인 실패 핸들러 (AuthFailHandler 클래스):
로그인 실패 시 처리를 담당하는 클래스로,
SimpleUrlAuthenticationFailureHandler를 확장합니다.
다양한 인증 실패 상황에 대한 메시지를 설정하고,
실패 시 이동할 URL을 지정합니다.

HTML 페이지:
코드에서는 뷰 템플릿의 이름으로 문자열을 반환하고 있으므로,
각 경로에 해당하는 HTML 페이지가 존재해야 합니다.
예를 들면 index.html, admin/admin.html, user/user.html,
auth/fail.html 등입니다.

Spring Boot 및 Spring MVC:
코드 상에서는 Spring Boot와 Spring MVC를 사용하여
웹 애플리케이션을 구성하고, 컨트롤러를 작성하고 있습니다.
Spring Security를 이용하여 사용자 인증 및 권한 부여를 처리하고 있습니다.

Spring Data JPA:
코드에서는 Spring Data JPA를 사용하여
데이터베이스와의 상호 작용을 처리할 것으로 예상됩니다.

BCryptPasswordEncoder:
비밀번호 암호화를 위해 BCryptPasswordEncoder를 사용하고 있습니다.

 */




package com.ohgiraffers.semilogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SemiloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemiloginApplication.class, args);
    }

}
