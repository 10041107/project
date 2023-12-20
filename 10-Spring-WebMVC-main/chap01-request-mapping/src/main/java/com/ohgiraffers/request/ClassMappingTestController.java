package com.ohgiraffers.request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller //사용자의 요청을 받아주는 서블릿, 외부에서 요청을 받을 수 있게 해줌
//@RestController //요청 데이터만 받아주는 식 Mapping과 Controller의 차이는 view resolver 가 있고 없고 차이
@RequestMapping("/order/*") //어떤요청이 들어왔을때 여기서 처리할거야
public class ClassMappingTestController {

    // port : 80 = web
    // port : 8080 = was
    // port : 443 = https

    // Get : localhost:8080/order/regist  <- 이 요청이 들어오면 매핑하겠다
    @GetMapping("/regist")
    public String registOrder(Model model){
        model.addAttribute("message", "Get 방식의 주문 등록용 핸들러 메소드 호출");
        //model이라는 녀석한테 우리가 속성을 정의해주겠다 어트리뷰트는 속성
        //message가 key고 Get 방식의 주문 등록용 핸들러 메소드 호출이 value값

        return "mappingResult";
        //스태틱일때 html파일을 찾아간다
    }

    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.POST)
    //RequestMethod를 POST로 설정해서 Post : /menu/modify 와 post : /order/delete에 적용된다
    public String modifyAndDelete(Model model){
        model.addAttribute("message","post 방식의 주문 정보 수정과 주문 정보 삭제 공통 처리용 핸들러");
        return "mappingResult";
    }
    /* 3. path Variable
    * @PathVariable 어노테이션을 이용해 요청을 path로부터 변수를 받아올 수 있다.
    * path variable로 전달 되는 {변수명} 값은 반드시 매개변수명과 동일해야 한다.
    * 만약 동일하지 않으면 @PathVariable("이름")을 설정해주어야 한다,
    * 이는 rest형 웹 서비스를 설계할 때 유용하게 사용된다.
    *
    * 인텔리제이의 builder설정을 intellij로 했을 경우에는 @PathVariable에 이름을 지정해주지 않으면 찾지 못한다.
    * */

    // 3 = orderNo(어떤게 넘어올지 몰라서 orderNo로 했음) orderNo로 넘겨주면
    @GetMapping("/detail/{orderNo}")
    public String selectOrderDetail(Model model, @PathVariable("orderNo") int orderNo){
        model.addAttribute("message",orderNo + "번 주문 상세 내용 조회용 핸들러 메소드 호출");
        return "mappingResult";
    }

    @RequestMapping
    public String otherRequest(Model model){
        model.addAttribute("message","order 요청이긴 하지만 다른 기능이 준비되지 않음");
        return "mappingResult";
    }
}
