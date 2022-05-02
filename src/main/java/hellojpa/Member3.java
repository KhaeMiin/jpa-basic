package hellojpa;

import javax.persistence.*;

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
