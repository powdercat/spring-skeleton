package powder.springskeleton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import powder.springskeleton.domain.Member;

import java.util.Optional;

// SpringDataJpa 가 JpaRepository 를 가지고 있으면 SpringDataJpaMemberRepository 가 자동으로 구현체를 만들어서 스프링 빈에 등록한다.
// JpaRepository 에 기본적인 함수들이 다 있음
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
    // 이런건 비즈니스별로 다 다를 수 있기(name, user_name..) 때문에 공통으로 제공할 수 없어서 만들어쓰면 된다.
    // 규칙: findByName -> JPQL select m from Member m where m.name = ?
    //            And Or 등등
    // 구현 클래스를 작성할 필요 없이, 인터페이스 이름만으로도 끝남
}
