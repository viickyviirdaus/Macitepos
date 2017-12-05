package com.macitepos.macitepos.api;

import com.macitepos.macitepos.dto.MemberDTO;
import com.macitepos.macitepos.dto.PenggunaDTO;
import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.MembersService;
import com.macitepos.macitepos.services.OrdersService;
import com.macitepos.macitepos.services.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManajerAPI {

    @Autowired
    private PenggunaService penggunaService;
    @Autowired
    private AkunService akunService;
    @Autowired
    private MembersService membersService;
    @Autowired
    private OrdersService ordersService;


    @RequestMapping(path="/api/order", method = RequestMethod.GET)
    public List<Transaksi_penjualanDTO> order()
    {
        return ordersService.showOrder();
    }

    @RequestMapping(path="/api/customer", method = RequestMethod.GET)
    public Iterable<MemberDTO> customer(){
    return membersService.showAll();
    }

    @RequestMapping(path="/api/pengguna", method = RequestMethod.GET)
    public List<PenggunaDTO> user(){
        return penggunaService.showAll();


//        Iterable<Pengguna> test = akunService.listPengguna();
//        return test;
    }

}
