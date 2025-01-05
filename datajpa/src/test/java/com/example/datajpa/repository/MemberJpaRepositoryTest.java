package com.example.datajpa.repository;

import com.example.datajpa.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository memberJpaRepository;


    @Test
    void save() {
        Member member =  new Member("memberA");
        Member member1 = memberJpaRepository.save(member);

        Member member2 = memberJpaRepository.find(member1.getId());

        assertThat(member2.getId()).isEqualTo(member.getId());


    }

    @Test
    void find() {
        Member member1 =  new Member("member1");
        Member member2=  new Member("member2");

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        Member member = memberJpaRepository.findById(member1.getId()).get();

        assertThat(member).isEqualTo(member1);

        List<Member> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        memberJpaRepository.delete(member);

        List<Member> all1 = memberJpaRepository.findAll();
        assertThat(all1.size()).isEqualTo(1);

    }

    @Test
    public void findByUsername(){
        Member m1= new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> res = memberJpaRepository.findByUsernameAndAgeGreaterThen("AAA", 15);

        assertThat(res.get(0).getAge()).isEqualTo(20);
    }

    @Test
    public void pageTest(){

        memberJpaRepository.save(new Member("Member1", 10));
        memberJpaRepository.save(new Member("Member2", 10));
        memberJpaRepository.save(new Member("Member3", 10));
        memberJpaRepository.save(new Member("Member4", 10));
        memberJpaRepository.save(new Member("Member5", 10));

        int age = 10;
        int offset = 3;
        int limit = 3;
        List<Member> byPage = memberJpaRepository.findByPage(age, offset, limit);
        long l = memberJpaRepository.totalCount(age);

        //then
        assertThat(byPage.size()).isEqualTo(2);
        assertThat(l).isEqualTo(5);
    }

    @Test
    public void bulkTest(){
        memberJpaRepository.save(new Member("Member1", 10));
        memberJpaRepository.save(new Member("Member2", 12));
        memberJpaRepository.save(new Member("Member3", 145));
        memberJpaRepository.save(new Member("Member4", 199));
        memberJpaRepository.save(new Member("Member5", 20));

        int i = memberJpaRepository.bulkAgePlus(20);
        assertThat(i).isEqualTo(3);
    }
}