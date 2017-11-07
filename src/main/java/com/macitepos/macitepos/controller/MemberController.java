package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
    private MemberDao memberDao;

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping("/customer")
    public String customer(Model model){
        model.addAttribute("member", memberDao.listMember());
        return "m_customer"   ;
    }
}
