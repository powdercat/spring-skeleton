package powder.springskeleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import powder.springskeleton.repository.MemberRepository;
import powder.springskeleton.repository.MemoryMemberRepository;
import powder.springskeleton.service.MemberService;

// spring 이 뜰 때, @Configuration 을 읽고 @Bean 의 로직을 호출해서 스프링 빈에 등록한다.
@Configuration
public class SpringConfig {

    // 컨트롤러에서는 어쩔 수 없이 @Controller, @Autowired 써야한다.
    // 스프링이 뜰 떼 MemberService, MemberRepository 를 스프링 빈에 등록하고, = @Service, @Repository
    // 스프링 빈에 등록되어있는 MemberRepository 를 MemberService 에 넣어준다. = Autowired
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
