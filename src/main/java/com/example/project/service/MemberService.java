package com.example.project.service;

import com.example.project.domain.members.Member;
import com.example.project.domain.members.MemberRole;
import com.example.project.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager em;

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    @Transactional
    public void createUser(String email, String password, String address, String name) {
    Member member = new Member();
    member.setName(name);
    member.setPassword(passwordEncoder.encode(password));
    member.setEmail(email);
    member.setAddress(address);
    if ("ADMIN".equals(name.toUpperCase())){
        member.setRole(MemberRole.ADMIN);
    } else {
        member.setRole(MemberRole.USER);
    }
    em.persist(member);
    }
}
