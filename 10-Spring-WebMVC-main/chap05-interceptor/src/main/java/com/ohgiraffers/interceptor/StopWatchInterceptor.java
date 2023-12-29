package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
* 핸들러 인터셉터를 구현한다.
* default 메소드 이전에는 모두 오버라이딩 해야하는 책임을 가지기 때문에 JHandlerInterceptorAdaptor를 이용해 부담을 줄여 사용했으나
* default 메소드가 인터페이스에서 사용 가능하게 된 1.8 이후부터는 인터페이스만 구현하여 필요한 메소드만 오버라이딩해서 사용할 수 있다.
* */
@Component
public class StopWatchInterceptor implements HandlerInterceptor {
    private final MenuService menuService;

    public StopWatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    /*전처리 메소드*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!request.getParameter("auth").equals("admin")){ //auth의 값이 admin을 주지 않았다면
            response.sendRedirect("/"); //다시 메인화면으로 돌아간다
            return false; //admin이 아니기 때문에 false로 반환
        }

        System.out.println("prehandler 호출함");
        long startTime = System.currentTimeMillis();
        //System.currentTimeMillis();는 1970년1월1일부터 UTC시간 기준으로 현재까지의 시간차를 밀리세컨으로 나타낸것
        request.setAttribute("startTime", startTime);
        //startTime이란 객체를 satrtTime이라는 이름으로 값을 보내줌
        //사용자 요청속성에다가 속성타입을 만들어서 넣어줬음

        // 컨트롤러를 이어서 호출한다. false인 경우 핸들러 메소드를 호출하지 않는다.
        return true; //2번 실행
    }

    // 후처리 메소드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler 호출함");
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        //startTime을 삭제한다 왜 삭제함?
        long endTime = System.currentTimeMillis();

        modelAndView.addObject("interval",endTime - startTime); //끝난 시간에서 시작 시간을 빼서 걸리는 시간이 나옴
        //여기서의 interval이 result.html에 출력됨
        //3번 실행
        //시간초와 postHandler가 나옴
    }

    @Override //handlerInterceptor를 사용하여 요청 처리가 완료된 후에 실행되는
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after Complate 호출함.");
        menuService.method();
        //전역변수로 설정한 menuService의 method()를 호출한다
        //4번 실행
    }
}
