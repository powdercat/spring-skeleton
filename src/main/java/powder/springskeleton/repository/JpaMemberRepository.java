package powder.springskeleton.repository;

import powder.springskeleton.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // jpa 는 EntityManager 라는 것으로 모든 동작을 한다.
    // spring boot 가 자동으로 EntityManager 를 만든다.(디비 통신 등 모든 것) 만든 것을 injection 받으면 된다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // pk 기반 조회가 아닌 것들은 jpql(select m from Member m ...) 로 작성해야한다.
        // -> JPA 기술을 스프링에서 감싸서 제공하는 "스프링 데이터 JPA" 라는 것을 이용하면 얘네도 jpql 로 짤 필요 없다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 엔티티 객체(Member)를 대상으로 쿼리를 날린다. 결과를 바로 엔티티 객체로 받음
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
