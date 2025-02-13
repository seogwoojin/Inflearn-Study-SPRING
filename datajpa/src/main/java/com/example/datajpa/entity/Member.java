package com.example.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@ToString(of = {"id","username","age"})//순환참조
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.username =:username"
)
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }

    public Member(String aaa, int i) {
        this.username = aaa;
        this.age = i;

    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
