package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //JPA가 사용하는 클래스라는 것을 인식,
public class Member3 {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID") //FK 외래 키가 있는 곳이 주인(수정, 등록 가능) 외래키가 있는 쪽이 Many가 된다.
    private Team team;

    //일대일 (다대일과 유사하다.)
    //다대일처럼 외래키가 있는 쪽이 주인(양방향의 경우) 직관적으로! 외래키가 있는 쪽을 주인으로 잡으면 된다.(쉽게 생각하자)
    //현재는 Member3이 주인이다. 이러나 추후 한명의 회원이 여러가지 락커를 가질 수 있다는 요구조건이 추가될 수 있으므로
    //락커를 주인으로 하는 것이 좋다. (락커가 주인으로 다대일 맵핑 수정이 가능하다. > uni만 제외하면 됨
    //사실 이런 단순한 부분은 걍 양방향 거는게 좋을 거 같다.
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    //다대다
    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();


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

    public Team getTeam() {
        return team;
    }

    //방법2
    public void setTeam(Team team) {
        this.team = team;
    }

    //방법1
/*    public void changeTeam(Team team) {
        this.team = team;

        //연관관계 편의 메서드
        //하지만 set을 잘 안씀.
        team.getMembers().add(this); //this: 필드 team(즉, 자신)
        //javaMain.java 에서 team.getMembers().add(member);를 생략할 수 있음
    }*/
}
