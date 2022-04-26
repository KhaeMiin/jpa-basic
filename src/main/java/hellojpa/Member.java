package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //JPA가 사용하는 클래스라는 것을 인식,
//@Table(name = "USER") //insert될 테이블 이름을 직접 지정해 줄 수 있다.
public class Member {

    @Id //pk 설정해줘야함
    private Long id;

//    @Column(name = "userName") 컬럼명이 userName으로 들어간다.
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
