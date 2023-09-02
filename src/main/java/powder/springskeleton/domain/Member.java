package powder.springskeleton.domain;

import javax.persistence.*;

// jpa 는 자바의 표준 인터페이스, orm 기술
// jpa 의 구현체로 hibernate, eclipse 등

// @Entity 를 붙이면 Member 는 java가 관리하는 entity 가 된다.
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // identity 전
    private Long id;

//    @Column(name = "username") // db 에서의 컬럼명 = username
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
