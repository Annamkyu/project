package com.example.project.controller;

import com.example.project.domain.members.Member;
import com.example.project.dto.MemberFormDto;
import com.example.project.service.MemberService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "/memberForm";
    }

    @PostMapping(value = "new")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "/memberForm";
        }
        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "/loginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "/loginForm";
    }
//    @PostMapping("/login")
//    public String signup(@Valid MemberFormDto memberFormDto,
//                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "loginForm";
//        }
//        if (!memberFormDto.getPassword().equals(memberFormDto.getPassword())) {
//            bindingResult.rejectValue("password", "passwordInCorrect",
//                    "2개의 패스워드가 일치하지 않습니다.");
//            return "loginForm";
//        }
//// 중복회원 가입 처리
//        try{
//            memberService.createUser(memberFormDto.getEmail(), memberFormDto.getPassword(),
//                    memberFormDto.getAddress(), memberFormDto.getName());
//        }catch (DataIntegrityViolationException e){
//            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
//            return "loginForm";
//        }
//        return "loginForm";
//    }

}
