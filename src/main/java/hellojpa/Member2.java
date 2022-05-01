package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity //JPA가 사용하는 클래스라는 것을 인식,
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq") //SEQUENCE 전략
//@Table(name = "USER") //insert될 테이블 이름을 직접 지정해 줄 수 있다.
public class Member2 {

    /**
     * 권장하는 식별자 전략
     * Long형 + 대체키 + 키 생성전략 사용
     * @GeneratedValue(strategy = GenerationType.)
     */
    @Id //pk로 맵핑
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //int는 0때문에 안됨. Integer은 숫자가 커지면(10억 넘거나) 힘듬 그러므로 거의 Long으로 사용한다.

    @Column(name = "name") //DB컬럼명은 name
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Member2() {
    }

}
