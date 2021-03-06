package com.macitepos.macitepos.api;

import com.google.gson.Gson;
import com.macitepos.macitepos.controller.EmailController;
import com.macitepos.macitepos.dto.MemberDTO;
import com.macitepos.macitepos.dto.RecieverTransaksiPenjualanDTO;
import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;
import com.macitepos.macitepos.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
public class TransaksiPenjualanAPI {

    @Autowired
    DetilPenjualanService detilPenjualanService;
    @Autowired
    ProdukService produkService;
    @Autowired
    private AkunService akunService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private MembersService membersService;
    @Autowired
    private EmailController emailController;

    @RequestMapping(value = "/api/create/transaksiPenjualan", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void create (@RequestBody String recievedJson) throws Exception {
        Gson gson = new Gson();
        RecieverTransaksiPenjualanDTO recieverTransaksiPenjualanDTO[] = gson.fromJson(recievedJson,RecieverTransaksiPenjualanDTO[].class);
        System.out.println("Data Transaksi Penjualan");
        System.out.println("id member di transaksi penjualan api = "+ recieverTransaksiPenjualanDTO[0].getId_member());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int id_pengguna = akunService.findByUsername(authentication.getName()).getId_pengguna();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if(recieverTransaksiPenjualanDTO[0].getId_member()==1){
            List<MemberDTO> memberDTO = membersService.findCustomer();
            int idMember = 0;
            for (MemberDTO m:
                    memberDTO) {
                idMember = m.getId_member();
            }
            for (int i =0; i<recieverTransaksiPenjualanDTO.length;i++){
                recieverTransaksiPenjualanDTO[0].setId_member(idMember);
            }
        }

        System.out.println("id member fix = "+recieverTransaksiPenjualanDTO[0].getId_member());

        int totalBarangDijual = 0;
        for (int i = 0; i<recieverTransaksiPenjualanDTO.length; i++){
            totalBarangDijual += recieverTransaksiPenjualanDTO[i].getCount_product();
        }

        //Save Penjualan
        Transaksi_penjualanDTO t = ordersService.saveOrUpdated(recieverTransaksiPenjualanDTO[0], id_pengguna,totalBarangDijual, timestamp);

        //Save Detil Penjualan
        for (int i=0; i<recieverTransaksiPenjualanDTO.length;i++){
            detilPenjualanService.saveOrUpdated(t, recieverTransaksiPenjualanDTO[i]);
        }

        //Update Jumlah Berkunjung
        membersService.updateJumlahBerkunjung(recieverTransaksiPenjualanDTO[0].getId_member(),recieverTransaksiPenjualanDTO[0].getVisit_count(),timestamp);

        //Update Stok Toko dan Stok Total
        for (int i = 0; i<recieverTransaksiPenjualanDTO.length;i++){
            produkService.updateStokTokodanTotal(recieverTransaksiPenjualanDTO[i].getId_produk(), recieverTransaksiPenjualanDTO[i].getCount_product());
        }


        //Update Jumlah Terjual
        for (int i = 0; i<recieverTransaksiPenjualanDTO.length;i++){
            produkService.updateTerjual(recieverTransaksiPenjualanDTO[i].getId_produk(), recieverTransaksiPenjualanDTO[i].getCount_product());
        }

        //Send Email
        String email = recieverTransaksiPenjualanDTO[0].getEmail();
        int idUser = recieverTransaksiPenjualanDTO[0].getId_member();
        if(!email.equalsIgnoreCase("")){
            System.out.println("sending email to "+email);
            emailController.send(email);
        } else if(idUser>1){
            System.out.println("sending email to member ID " + idUser);
            List<MemberDTO> mdto = membersService.findByID(idUser);
            for (MemberDTO m: mdto) {
                emailController.send(m.getEmail());
            }
        }
    }

    @RequestMapping(path="/api/order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaksi_penjualanDTO> order()
    {
        return ordersService.showOrder();
    }

    @RequestMapping(path = "/api/order/day", method =RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaksi_penjualanDTO> orderDay(){
        return  ordersService.showOrderDay();
    }

    @RequestMapping(path = "/api/generalLedgerWeek", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaksi_penjualanDTO> generalLedgerWeek(){
        return ordersService.generalLedger("week","desc");
    }

    @RequestMapping(path = "/api/generalLedgerMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaksi_penjualanDTO> generalLedgerMonth(){
        return ordersService.generalLedger("month","desc");
    }

    @RequestMapping(path = "/api/generalLedgerMonthASC", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaksi_penjualanDTO> generalLedgerMonthASC(){
        return ordersService.generalLedger("month","asc");
    }

    @RequestMapping(path = "/api/generalLedgerYear", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaksi_penjualanDTO> generalLedgerYear(){
        return ordersService.generalLedger("year","desc");
    }

    @RequestMapping(path = "/api/email", method =RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void o(){
        emailController.send("habridio88@gmail.com");
    }
}
