package powder.springskeleton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import powder.springskeleton.domain.Member;
import powder.springskeleton.repository.MemberRepository;
import powder.springskeleton.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// @Service annotation을 붙이면 스프링이 뜰 때 스프링 컨테이너에 MemberService 를 등록해준다
@Service
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        // MemberService 는 직접 new 하지 않고, 외부에서 memberRepository 를 넣어준다.
        // = DI (Dependency Injection)
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름은 중복 가입 불가

        /*
        // null 일 가능성이 있으면, optional 로 감싸서 반환한다.
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            // optional 로 감쌌기 때문에 가능. result.get(); 로 바로 꺼내도 되지만, 권장하지 않는다.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
