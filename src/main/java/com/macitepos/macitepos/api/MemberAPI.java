package com.macitepos.macitepos.api;

import com.macitepos.macitepos.dto.MemberDTO;
import com.macitepos.macitepos.services.MemberService;
import com.macitepos.macitepos.services.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAPI {

    @Autowired
    MembersService membersService;

    @RequestMapping(path="/api/produk/create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void ProdukCreate(@RequestBody MemberDTO memberDTO){
        MemberDTO mDTO = membersService.saveOrUpdated(memberDTO);

    }

}
