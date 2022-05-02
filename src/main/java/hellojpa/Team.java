package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    /**
     * mappedBy: 조회만 가능
     * 외래키가 없는 쪽이 One이 된다(대부분)
     */
    //양방향 맵핑시 아래코드는 역방향 코드
    @OneToMany(mappedBy = "team") //어떤것과 연결되어있는지
    private List<Member3> members = new ArrayList<>();

    //방법2
    public void addMember(Member3 member) {
        member.setTeam(this);
        members.add(member);
    }

    public List<Member3> getMembers() {
        return members;
    }

    public void setMembers(List<Member3> members) {
        this.members = members;
    }

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
