package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.model.Member;
import com.macitepos.macitepos.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    private MemberDao memberDao;

        @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @RequestMapping("/member")
    public String home(Model model){
        model.addAttribute("member", memberDao.listMember());
        return "home"   ;
    }

    @RequestMapping(value = "/member/create", method = RequestMethod.GET)
    public String tampilkanForm(Model model){
        model.addAttribute("member", new Member());
        return "formMember";
    }

    @RequestMapping(value = "/member/create", method = RequestMethod.POST)
    public String simpanDataMember(Model model, Member member){
        model.addAttribute("member", memberDao.saveOrUpdate(member));
        return "redirect:/member";
    }

    @RequestMapping(value = "/member/edit/{id}", method = RequestMethod.GET)
    public String editDataMember(@PathVariable Integer id, Model model){
        model.addAttribute("member", memberDao.getIdMember(id));
        return "formMember";
    }

    @RequestMapping(value = "/member/hapus/{id}")
    public String hapus(@PathVariable Integer id){
          memberDao.hapus(id);
        return "redirect:/member";
    }
}
