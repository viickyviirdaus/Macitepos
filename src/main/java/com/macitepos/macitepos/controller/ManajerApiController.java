package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.model.Member;
import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManajerApiController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private AkunService akunService;


    @RequestMapping(path="/api/customer", method = RequestMethod.GET)
    public Iterable<Member> customer(){
        return memberService.listMember();
    }

    @RequestMapping(path="/api/pengguna", method = RequestMethod.GET)
    public Iterable<Pengguna> user(){
        return akunService.listPengguna();
    }
}
