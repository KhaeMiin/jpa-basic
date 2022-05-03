package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

    @Id @GeneratedValue
    private Long id;

    private String name;

    //일대일에서 양방향을 할 경우(읽기전용)
    @OneToOne(mappedBy = "locker")
    private Member3 member;
}
