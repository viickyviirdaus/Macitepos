package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dto.MemberDTO;
import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.MemberService;
import com.macitepos.macitepos.services.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MembersService membersService;


    @RequestMapping(path="/api/customer", method = RequestMethod.GET)
    public Iterable<MemberDTO> customer(){
    return membersService.showAll();
    }

    @RequestMapping(path="/api/pengguna", method = RequestMethod.GET)
    public Iterable<Pengguna> user(){
        Iterable<Pengguna> test = akunService.listPengguna();
        return test;
    }
}
