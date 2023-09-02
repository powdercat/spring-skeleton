package powder.springskeleton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import powder.springskeleton.domain.Member;
import powder.springskeleton.service.MemberService;

import java.util.List;

@Controller
public class MemberController {
    // spring 이 처음에 뜰 때 만들어지는 container 에 @Controller annotation 이 있으면, MemberController 객체를 생성해서 넣어두고 spring 이 관리한다.
    // spring 컨테이너에서 spring bean 이 관리된다고 표현한다.

//    private final MemberService memberService = new MemberService();
    // 다른 컨트롤러들(예를 들면 주문 컨트롤러)이 memberservice 를 쓸 수 있다.
    // new 로 여러 인스턴스를 생성할 필요가 없다. 하나만 생성하고 공용으로 사용하는 것이 좋다.

    // 대신 아래처럼 스프링 컨테이너에 딱 하나만 등록하고 사용한다. + 부가적인 효과가 있음
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 스프링 컨테이너가 뜰 때 MemberController 를 생성하는데, 그때 위 생성자를 호출한다.
    // 그 때, 생성자에 @Autowired 가 있으면 memberService 를 스프링 컨테이너에 있는(spring bean 에 등록 되어있는) MemberService 랑 연결을 해준다.
    // = Dependency Injection = 의존관계 주입
    // MemberService 에 @Service annotation 이 스프링 컨테이너에서 찾을 수 없기 때문에 에러가 난다. (이 상황에서 MemberService 는 순수한 자바코드이기 때문)


    @GetMapping("/members/new")
    public String createForm() {
        // /members/new 경로로 오면 templates 폴더에서 members/createMemberForm 경로를 찾아서 보여줌
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // members 리스트를 "members" 에 담아서 화면에 넘긴다.
        // html 에서 model ${members} 를 사용해서 안의 값을 꺼낸다.
        return "members/memberList";
    }
}
