package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.model.Member;
import com.macitepos.macitepos.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private MemberService memberService;

    @Autowired
    public void setMemberDao(MemberService memberDao) {
        this.memberService = memberDao;
    }

    @RequestMapping(path="/api/customer", method = RequestMethod.GET)
    public Iterable<Member> customer(Model model){
//        model.addAttribute("member", memberService.listMember());
//        return "m_customer"   ;
         return memberService.listMember();
    }
}
