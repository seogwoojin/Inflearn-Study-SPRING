package com.example.datajpa.repository;

import com.example.datajpa.dto.MemberDto;
import com.example.datajpa.entity.Member;
import com.example.datajpa.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.sql.Array;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.ARRAY;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    void save() {
        System.out.println("memberRepository.getClass() = " + memberRepository.getClass());
        Member member =  new Member("memberA");
        Member member1 = memberRepository.save(member);

        Member member2 = memberRepository.findById(member1.getId()).get();

        assertThat(member2.getId()).isEqualTo(member.getId());
        assertThat(member2).isEqualTo(member);

    }

    @Test
    void find() {
        Member member =  new Member("member1");
        Member member2 =  new Member("member2");
        Member member1 = memberRepository.save(member);
        memberRepository.save(member2);

        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        memberRepository.delete(member1);

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(1);

    }

    @Test
    void find2(){
        Member member = new Member("member1");
        memberRepository.save(member);
        memberRepository.flush();

        Member referenceById = memberRepository.getReferenceById(1L);
        System.out.println("referenceById.getUsername() = " + referenceById.getUsername());
    }

    @Test
    void finduser(){
        Member m1= new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> res = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(res.get(0).getAge()).isEqualTo(20);

        memberRepository.findTop5By();
    }

    @Test
    void namedQuery(){
        Member m1= new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> aaa = memberRepository.findListByUsername("AAA");

        assertThat(aaa.size()).isEqualTo(2);

    }

    @Test
    void dataQuery(){
        Member m1= new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> aaa = memberRepository.findUser("AAA", 15);

        assertThat(aaa.size()).isEqualTo(1);

        List<String> usernameList = memberRepository.findUsernameList();
        for(String s : usernameList){
            System.out.println("s = " + s);
        }
    }

    @Test
    public void memberDto(){
        Team team = new Team("teamA");

        Member m1 = new Member("AAA", 10);

        teamRepository.save(team);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();

        for(MemberDto m : memberDto){
            System.out.println("m = " + m);
        }

    }


    @Test
    public void findByNames(){

        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 10);

        memberRepository.save(m2);
        memberRepository.save(m1);

        List<Member> memberDto = memberRepository.findByNames(List.of("AAA"));

        for(Member m : memberDto){
            System.out.println("m = " + m);
        }
    }

    @Test
    public void findByNames2(){

        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 10);

        memberRepository.save(m2);
        memberRepository.save(m1);

        List<Member> memberDto = memberRepository.findByNames(List.of("AAAB"));

        for(Member m : memberDto){
            System.out.println("m = " + m);
        }

        Member member = memberRepository.findMByUsername("AAA");
        System.out.println("member = " + member);

        Optional<Member> optional = memberRepository.findOptionByUsername("AAAc");

        System.out.println("optional = " + optional);
    }

    @Test
    public void pageTest(){

        Team team1 = teamRepository.save(new Team("team1"));
        Member member1 = memberRepository.save(new Member("Member1", 10));
        member1.setTeam(team1);
        memberRepository.save(new Member("Member2", 10));
        memberRepository.save(new Member("Member3", 10));
        memberRepository.save(new Member("Member4", 10));
        memberRepository.save(new Member("Member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(1, 3, Sort.by(Sort.Direction.DESC, "username"));
        Page<Member> byPage = memberRepository.findByAge(age, pageRequest);
        Page<MemberDto> memberDtos = byPage.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
        //then
        assertThat(byPage.getTotalPages()).isEqualTo(2);
        assertThat(byPage.hasNext()).isEqualTo(true);
        assertThat(byPage.hasPrevious()).isEqualTo(false);
    }

    @Test
    public void bulk(){

        Team team1 = teamRepository.save(new Team("team1"));
        Member member1 = memberRepository.save(new Member("Member1", 10));
        member1.setTeam(team1);
        memberRepository.save(new Member("Member2", 10));
        memberRepository.save(new Member("Member3", 20));
        memberRepository.save(new Member("Member4", 40));
        memberRepository.save(new Member("Member5", 50));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(1, 3, Sort.by(Sort.Direction.DESC, "username"));
        Page<Member> byPage = memberRepository.findByAge(age, pageRequest);
        Page<MemberDto> memberDtos = byPage.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
        //then
        assertThat(byPage.getTotalPages()).isEqualTo(2);
        assertThat(byPage.hasNext()).isEqualTo(true);
        assertThat(byPage.hasPrevious()).isEqualTo(false);
    }

    @Test
    public void findMemberLazy(){
        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.saveAll(List.of(teamA, teamB));
        Member member = new Member("member1", 20, teamA);
        Member member2 = new Member("member2", 21, teamB);
        memberRepository.saveAll(List.of(member2, member));
        em.flush();
        em.clear();

        List<Member> memberFetchJoin = memberRepository.findAll();
        for (Member mem : memberFetchJoin){
            System.out.println("member1.getUsername() = " + mem.getUsername());
            System.out.println("member1.getTeam().getClass() = " + mem.getTeam().getClass());
            System.out.println("member.getTeam().getName() = " + mem.getTeam().getName());
            System.out.println("member.getTeam().getClass() = " + mem.getTeam().getClass());
        }
    }

    @Test
    void queryHint(){
        memberRepository.save(new Member("member1",10));
        em.flush();
        em.clear();

        //when
        Member member = memberRepository.findReadByUsername("member1");
        member.setUsername("member2");
        System.out.println("member.getUsername() = " + member.getUsername());

        em.flush();

    }

    @Test
    void lock(){
        memberRepository.save(new Member("member1",10));
        em.flush();
        em.clear();

        //when
        Member member = memberRepository.findLockByUsername("member1").get(0);
        member.setUsername("member2");
        System.out.println("member.getUsername() = " + member.getUsername());

        em.flush();

    }

    @Test
    void callCustom(){
        List<Member> memberCustom = memberRepository.findMemberCustom();
    }
}