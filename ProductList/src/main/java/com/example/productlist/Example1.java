package com.example.productlist;

/*
    @author: Dinh Quang Anh
    Date   : 6/21/2023
    Project: ProductList
*/
public class Example1 {

    EmailService emailService = new EmailService();

    public void sendMessage(String message){
        emailService.sendMail(message);
        // send thông điệp đi đâu đó
        // class Example1 phụ thuộc vào EmailService
    }

    public static class SmtpServer{

        public void sendSmtpService(String message){
            System.err.println(message);
        }
    }

    public static class EmailService{
        SmtpServer smtpServer;

        public EmailService(SmtpServer smtpServer){
            this.smtpServer = smtpServer;
        }

        public EmailService() {

        }

        public void sendMail(String message){
            System.err.println(message);
        }
    }
}
