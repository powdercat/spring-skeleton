package powder.springskeleton.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import powder.springskeleton.domain.Member;
import powder.springskeleton.repository.MemberRepository;
import powder.springskeleton.repository.MemoryMemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // clear 를 하고 싶은데, MemberService 밖에 없어서 가져와야 함
    // MemberService 의 new MemoryMemberRepository() 랑 다른 인스턴스이다. 같은 걸로 테스트 하는 것이 좋다.(지금은 static 이라 문제는 없다.)

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // 테스트가 실행할 때마다 각각 만든 것을 넣어서 만들기 때문에, 같은 MemoryMemberRepository 를 사용하게 된다.
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

       /* try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}