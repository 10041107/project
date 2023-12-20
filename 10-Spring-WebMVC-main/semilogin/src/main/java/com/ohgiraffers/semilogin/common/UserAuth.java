/*
사용자 권한 Enum (UserAuth 클래스):

UserAuth Enum은 사용자의 권한을 정의합니다.
주로 사용자 및 관리자 권한이 여기에서 정의되어 있습니다.
 */


package com.ohgiraffers.semilogin.common;

// 사용자 권한을 정의하는 열거형
public enum UserAuth {
    USER("USER"),  // 일반 사용자 권한
    ADMIN("ADMIN"); // 관리자 권한

    private String auth;

    // 생성자
    UserAuth(String auth) {
        this.auth = auth;
    }

    // 권한 문자열 반환
    public String getAuth() {
        return auth;
    }

    // 권한 설정
    public void setAuth(String auth) {
        this.auth = auth;
    }

    // 객체를 문자열로 표현
    @Override
    public String toString() {
        return "UserAuth{" +
                "auth='" + auth + '\'' +
                '}';
    }
}
