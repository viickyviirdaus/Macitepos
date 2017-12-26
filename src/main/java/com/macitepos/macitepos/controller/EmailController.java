package com.macitepos.macitepos.controller;

//import com.macitepos.macitepos.dto.EmailDTO;
//import com.macitepos.macitepos.services.EmailService;
import com.macitepos.macitepos.dto.Detil_PenjualanDTO;
import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;
import com.macitepos.macitepos.model.Transaksi_penjualan;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.DetilPenjualanService;
import com.macitepos.macitepos.services.OrdersService;
import com.macitepos.macitepos.services.ProdukService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;


import org.slf4j.Logger;


import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private DetilPenjualanService detilPenjualanService;

    @Autowired
    private AkunService akunService;

//    @RequestMapping(value = "/api/sendMail", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
    public void send(String email){
        try {
            System.out.println("sending to  "+email);
            sendEmail(email);
            System.out.println("Email Sent!");
        }catch(Exception ex) {
            System.out.println("failed cause "+ex);
        }
    }

    private void sendEmail(String email) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        int subTotal =0, discount , total =0, amount =0, cash = 0, id_lastOrder=0, diskonPersen =0;
        Date d = new Date();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nama_pengguna = akunService.findByUsername(authentication.getName()).getNama_pengguna();
        List<Transaksi_penjualanDTO> lastOrder = ordersService.findLastOrder();
        for (Transaksi_penjualanDTO dto:lastOrder) {
            id_lastOrder = dto.getId_penjualan();
        }
        List<Detil_PenjualanDTO> dpdto= detilPenjualanService.findByIdOrder(id_lastOrder);
        for (Detil_PenjualanDTO dto:dpdto) {
            subTotal += dto.getProduk().getHarga_penjualan() * dto.getJumlah_penjualan();
            diskonPersen = dto.getTransaksi_penjualan().getDiskon();
            total = dto.getTransaksi_penjualan().getTotal_penjualan();
            amount = dto.getTransaksi_penjualan().getPembayaran_penjualan();
            cash = dto.getTransaksi_penjualan().getKembalian_penjualan();
            d = dto.getTransaksi_penjualan().getCreated_at();
        }
        discount = subTotal * diskonPersen /100;


        context.setVariable("cashier", nama_pengguna);
        context.setVariable("subTotal",subTotal);
        context.setVariable("discount",discount);
        context.setVariable("total",total);
        context.setVariable("amount",amount);
        context.setVariable("cash",cash);
        context.setVariable("data", dpdto);
        context.setVariable("date",d);
        helper.addAttachment("logo.png", new ClassPathResource("/static/assets/image/logo.png"));
        helper.setTo(email);
        helper.setText(templateEngine.process("email",context),true);
        helper.setSubject("Invoice macitePos.com");

        sender.send(message);
    }

//    @Autowired
//    private MailClient mailClient;
//
//    public void shouldSendMail() throws Exception {
//        //given
//        String recipient = "name@dolszewski.com";
//        String message = "Test message content";
//        //when
//        mailClient.prepareAndSend(recipient, message);
//        //then
//        assertReceivedMessageContains(message);
//    }
//
//    private void assertReceivedMessageContains(String expected) throws IOException, MessagingException {
//        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
//        assertEquals(1, receivedMessages.length);
//        String content = (String) receivedMessages[0].getContent();
//        assertTrue(content.contains(expected));
//    }



//    private static Logger log = LoggerFactory.getLogger(Application.class);

//    @Autowired
//    private EmailService emailService;
//
//    public void sendEmail(String email) throws Exception {
////        log.info("Sending Email with Thymeleaf HTML Template Example");
//
//        EmailDTO mail = new EmailDTO();
//        mail.setFrom("macitepos@gmail.com");
//        mail.setTo(email);
//        mail.setSubject("Sending Email with Thymeleaf HTML Template Example");
//
//        Map model = new HashMap();
//        model.put("name", "Memorynotfound.com");
//        model.put("location", "Belgium");
//        model.put("signature", "https://memorynotfound.com");
//        mail.setModel(model);
//
//        emailService.sendSimpleMessage(mail);
//    }
//
}
