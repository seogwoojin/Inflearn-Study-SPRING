package com.example.datajpa.controller;

import com.example.datajpa.dto.MemberDto;
import com.example.datajpa.entity.Member;
import com.example.datajpa.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member){
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(sort = "username") Pageable pageable){
        Page<Member> all = memberRepository.findAll(pageable);
        Page<MemberDto> memberDtos = all.map(it -> new MemberDto(it.getId(), it.getUsername(), it.getUsername()));
        return memberDtos;
    }

//    @PostConstruct
    public void insert(){
        for(int i = 1; i<=30 ; i++){
            memberRepository.save(new Member("member"+i));
        }

    }
}
