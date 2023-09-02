package powder.springskeleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import powder.springskeleton.repository.*;
import powder.springskeleton.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// spring 이 뜰 때, @Configuration 을 읽고 @Bean 의 로직을 호출해서 스프링 빈에 등록한다.
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    // spring container 가 만들어놓은 MemberRepository 를 찾는데 없음 -> 그러면 SpringDataJpa 가 만든 구현체가 등록된다.
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 컨트롤러에서는 어쩔 수 없이 @Controller, @Autowired 써야한다.
    // 스프링이 뜰 떼 MemberService, MemberRepository 를 스프링 빈에 등록하고, = @Service, @Repository
    // 스프링 빈에 등록되어있는 MemberRepository 를 MemberService 에 넣어준다. = Autowired
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
        // 인터페이스를 두고 구현체를 갈아끼기만 하면 된다.
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
