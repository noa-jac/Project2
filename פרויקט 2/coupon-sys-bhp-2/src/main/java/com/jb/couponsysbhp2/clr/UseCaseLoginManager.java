package com.jb.couponsysbhp2.clr;

import com.jb.couponsysbhp2.security.ClientType;
import com.jb.couponsysbhp2.security.LoginManager;
import com.jb.couponsysbhp2.services.ClientService;
import com.jb.couponsysbhp2.services.CompanyService;
import com.jb.couponsysbhp2.services.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@RequiredArgsConstructor
public class UseCaseLoginManager implements CommandLineRunner {

    private final LoginManager loginManager;

    @Override
    public void run(String... args) {

        System.out.println("@@@@@@@@@@@ LOGIN MANAGER @@@@@@@@@@@");

        System.out.println("@@@@@@@@@@@ LOGIN TEST @@@@@@@@@@@");

        try {
            ClientService adminService = loginManager.login("admin@admin.com", "admin", ClientType.ADMIN);
            System.out.println("admin is logged in");
        } catch (Exception e) {
            System.out.println("failed to log on");
        }

        System.out.println("@@@@@@@@@@@ LOGIN TEST @@@@@@@@@@@");

        try {
            ClientService companyService = loginManager.login("my-art@gmail.com", "1234", ClientType.COMPANY);
            System.out.println(((CompanyService) companyService).getCompanyDetails().getName() + " is logged in");
        } catch (Exception e) {
            System.out.println("failed to log on");
        }

        System.out.println("@@@@@@@@@@@ LOGIN TEST @@@@@@@@@@@");

        try {
            ClientService customerService = loginManager.login("david-bar@gmail.com", "1234", ClientType.CUSTOMER);
            System.out.println(((CustomerService) customerService).getCustomerDetails().getFirstName() + " is logged in");
        } catch (Exception e) {
            System.out.println("failed to log on");
        }


    }
}