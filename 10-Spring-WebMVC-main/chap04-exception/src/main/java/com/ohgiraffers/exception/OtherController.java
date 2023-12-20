package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    @GetMapping("other-controller-null")
    public String otherNullPointException(){
        String str = null;
        System.out.println(str.charAt(0)); // 에러발생하면 글로벌이 처리해줌
        return "/";
    }

    @GetMapping("other-controller-user")
    public String otherUserExceptionTest() throws MemeberRegistException{

        if(true){
            throw new MemeberRegistException("입사 불가");
        }
        return "/";
    }

    @GetMapping("other-controller-array")
    public String otherArrayExceptionTest(){
        double[] array = new double[0];
        System.out.println(array[0]); //아마 초기화되지 않아 생기는 에러가 발생할 것
        //얘는 배열의 길이가 0인데 0번째 인덱스를 불러올려고해서 범위가 넘어가는 에러가 나옴
        return "/";
    }

//    @ExceptionHandler(Exception.class) //여기가 가장 상위라서 먼저 에러를 잡는다
//    public String nullPointerExceptionHandler(Exception e){
//        System.out.println(e.getClass());
//        System.out.println("exception이 발생한다");
//        return "error/default";
//        //class java.lang.NullPointerException는 객체를 정의한 뒤에 생성자를 이용해 생성하지 않고 그 객체를 사용하려고해서 발생한 에러
//        //class java.lang.ArrayIndexOutOfBoundsException 배열의 크기보다 큰 인덱스를 가져오려고 해서 발생하는 에러
//    }
}
