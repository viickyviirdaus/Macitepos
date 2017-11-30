package com.macitepos.macitepos.api;

import com.macitepos.macitepos.dto.MemberDTO;
import com.macitepos.macitepos.services.MemberService;
import com.macitepos.macitepos.services.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberAPI {

    @Autowired
    MembersService membersService;

    @RequestMapping(path="/api/member/create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void ProdukCreate(@RequestBody MemberDTO memberDTO){
        MemberDTO mDTO = membersService.saveOrUpdated(memberDTO);
    }

    @RequestMapping(path = "/api/member/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<MemberDTO> getDiskon(@PathVariable int id){
        System.out.println("Id di member/diskon = " + id);
        return membersService.findByID(id);
    }
}
