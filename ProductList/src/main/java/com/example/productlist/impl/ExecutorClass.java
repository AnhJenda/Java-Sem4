package com.example.productlist.impl;

/*
    @author: Dinh Quang Anh
    Date   : 6/21/2023
    Project: ProductList
*/
public class ExecutorClass {
    public static void main(String[] args) {
        MessageService emailService = new EmailService();
        UserService userService = new UserService(emailService);
        userService.registerUser();

        // Cách này là constructor injection (ngoài ra còn có getter, setter, interface, service locator)
        // sự phụ thuộc của userService vào MessageService chuyển sang ExecutorService
        /*
        * Ưu điểm là mối quan hệ giữa user-service và message-Service dễ thay thế hơn
        * Nhược điểm là khởi tạo ở quá nhiều nơi
        * */
    }
}
