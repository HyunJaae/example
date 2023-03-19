package spring.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import spring.practice.domain.Member;
import spring.practice.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public void test() {
        Member member = new Member("현재");

        memberService.join(member);

        memberService.update(member.getId(), "인혁");

        Member findMember = memberService.find(member.getId());

        System.out.println("업데이트 전 이름 : " + member.getName());
        System.out.println("업데이트 후 이름 : " + findMember.getName());
    }
}
