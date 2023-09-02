package powder.springskeleton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 원래 / 경로로 들어가면 /static/index.html를 갔었다.
    // 요청이 오면 먼저 스프링 컨테이너 안에 있는 관련 컨트롤러를 확인한다. 그리고 없으면 static 파일을 찾도록 되어있다.
    // 이제 /static/index.html 은 무시된다.
    @GetMapping("/")
    public String home() {
        return "home";
    }

}
