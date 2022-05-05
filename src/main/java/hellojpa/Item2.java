package hellojpa;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) //조인전략 음..츄천! 장점: 제약조건을 아이템에 걸어서 맞출 수 있다.단점: 조인을 많이 사용해서 성능 저하 하지만 정석
//비즈니스 전략 복잡하다: 조인전략으로 사용하자
//조인전략: 객체와도 잘 맞음
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//단일 테이블 전략시(DType으로 ITEM, MOVIE등등 들어감) 츄천!
//확장 가능성이 없을 경우 사용하자
//장점: 조인이 필요없음 (성능향상)
//엔티티가 매핑한 컬럼은 모두 NULL 허용(단점)

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //비츄
@DiscriminatorColumn
public abstract class Item2 extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
