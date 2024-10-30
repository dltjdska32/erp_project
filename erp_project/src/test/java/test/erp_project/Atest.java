package test.erp_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.erp_project.controller.UserController;
import test.erp_project.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
public class Atest {

    @Autowired
    UserController userController;

    @Autowired
    UserService userService;

    @Test
    void call(){
        System.out.println(userController.getClass().getName());
        System.out.println(userService.getClass().getName());
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
    }

}
