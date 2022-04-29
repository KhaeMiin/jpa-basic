package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity //JPA가 사용하는 클래스라는 것을 인식,
//@Table(name = "USER") //insert될 테이블 이름을 직접 지정해 줄 수 있다.
public class Member {

/*    @Id //pk 설정해줘야함
    private Long id;

//    @Column(name = "userName") 컬럼명이 userName으로 들어간다.
    private String name;*/

    @Id //pk로 맵핑
    private Long id;

    @Column(name = "name") //DB컬럼명은 name
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) //eum타입 맵핑시
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //날짜 맵핑
    private Date createdDate; //생성 시간

    @Temporal(TemporalType.TIMESTAMP) // 날짜 맵핑
    private Date lastModifiedDate; //최종 수정 시간

    private LocalDate testLocalDate; //자바8 부터 나옴 최신 하이버네이트 지원원
   private LocalDateTime testLocalDateTime; //자바8 부터 나옴


    @Lob //varchar 넘어선 큰 데이터 실행시
    private String description;

    @Transient//메모리에서만 사용, DB생성 안함
    private int memoryData;

    public Member() {
    }

}
