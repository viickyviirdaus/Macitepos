//package com.macitepos.macitepos.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.mail.internet.MimeMessage;
//
//@RestController
//public class EmailController {
//
//    @Autowired
//    private JavaMailSender sender;
//
//    @RequestMapping(value = "/api/sendMail", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
////    @ResponseBody
//    void home(){
//        try {
//            sendEmail();
////            return "Email Sent!";
//        }catch(Exception ex) {
////            return "Error in sending email: "+ex;
//        }
//    }
//
//    private void sendEmail() throws Exception{
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setTo("vicky.virdus@gmail.com");
//        helper.setText("cekmaneh se?\njaran");
//        helper.setSubject("IKI NGGAE API");
//
//        sender.send(message);
//    }
//
//}
