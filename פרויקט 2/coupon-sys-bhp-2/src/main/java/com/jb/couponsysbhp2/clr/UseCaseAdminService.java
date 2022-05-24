package com.jb.couponsysbhp2.clr;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import com.jb.couponsysbhp2.exceptions.CouponSystemException;
import com.jb.couponsysbhp2.repos.CompanyRepository;
import com.jb.couponsysbhp2.repos.CouponRepository;
import com.jb.couponsysbhp2.services.AdminService;
import com.jb.couponsysbhp2.services.AdminServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;


@Component
@Order(2)
@RequiredArgsConstructor
public class UseCaseAdminService implements CommandLineRunner {

    private final AdminService adminService;
    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;


    @Override
    public void run(String... args) {
        System.out.println("@@@@@@@@@@@@@@@@@@@ START ADMIN SERVICE @@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@ START LOGIN TEST @@@@@@@@@@@@@@@@@@@@@@@@");

        if (((AdminServicesImpl) adminService).login("admin@admin.com", "admin")) {
            System.out.println("admin logged on");
        } else {
            System.out.println("admin failed to logged on");
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@ COMPANIES_TABLE @@@@@@@@@@@@@@@@@@@@@@@@");

        System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COUPON TEST @@@@@@@@@@@@@@@@");
        Coupon coup = Coupon.builder()
                .title("sport kit 1+1+1")
                .category(Category.SPORT)
                .amount(30)
                .price(20.99)
                .image("http://somthing.com")
                .description("best kit 3+3")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .company(companyRepository.getById(4))
                .build();
        try {
            couponRepository.save(coup);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Company comp1 = Company.builder()
                .name("nike")
                .email("puma@gmail.com")
                .password("1234")
                .coupon(coup)
                .build();
        try {
            adminService.addCompany(comp1);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COMPANY @@@@@@@@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COMPANY TEST @@@@@@@@@@@@@@@@");
        Company comp2 = Company.builder()
                .name("puma")
                .email("nike@gmail.com")
                .password("1234")
                .build();
        try {
            adminService.addCompany(comp2);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COMPANY SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COMPANY TEST2 @@@@@@@@@@@@@@@@");
        Company comp3 = Company.builder()
                .name("puma")
                .email("puma@gmail.com")
                .password("1234")
                .build();
        try {
            adminService.addCompany(comp3);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COMPANY SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COMPANY TEST @@@@@@@@@@@@@@@@");
        Company compToUpdate = null;
        try {
            compToUpdate = adminService.getSingleCompany(4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        compToUpdate.setName("nike");
        try {
            adminService.updateCompany(compToUpdate.getId(), compToUpdate);
            System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COMPANY SUCCEEDED @@@@@@@@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COMPANY TEST2 @@@@@@@@@@@@@@@@");
        try {
            compToUpdate = adminService.getSingleCompany(4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        compToUpdate.setId(20);
        try {
            adminService.updateCompany(compToUpdate.getId(), compToUpdate);
            System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COMPANY SUCCEEDED @@@@@@@@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COMPANY TEST3 @@@@@@@@@@@@@@@@");
        try {
            compToUpdate = adminService.getSingleCompany(4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        compToUpdate.setEmail("puma1@gmail.com");
        try {
            adminService.updateCompany(compToUpdate.getId(), compToUpdate);
            System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COMPANY SUCCEEDED @@@@@@@@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE COMPANY @@@@@@@@@@@@@@@@@@@@@@");
        try {
            adminService.deleteCompany(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCompanies().forEach(System.out::println);
        couponRepository.findAll().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ CUSTOMERS_TABLE @@@@@@@@@@@@@@@@@@@@@@@@");

        System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT CUSTOMER TEST1 @@@@@@@@@@@@@@@@");
        Customer cust1 = Customer.builder()
                .firstName("nir")
                .lastName("bar")
                .password("1234")
                .email("adam-lev@gmail.com")
                .coupon(couponRepository.getById(2))
                .build();
        try {
            adminService.addCustomer(cust1);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT CUSTOMER SUCCEEDED @@@@@@@@@@@@@@@@@@@@@@");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT CUSTOMER TEST2 @@@@@@@@@@@@@@@@");
        Customer cust2 = Customer.builder()
                .firstName("nir")
                .lastName("bar")
                .password("1234")
                .email("nir-bar@gmail.com")
                .coupon(couponRepository.getById(2))
                .build();
        try {
            adminService.addCustomer(cust2);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT CUSTOMER SUCCEEDED @@@@@@@@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE CUSTOMER TEST1 @@@@@@@@@@@@@@@@@@@@@@");
        Customer custToUpdate = null;
        try {
            custToUpdate = adminService.getSingleCustomer(4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        custToUpdate.setLastName("levi");
        custToUpdate.setId(20);
        try {
            adminService.updateCustomer(custToUpdate.getId(), custToUpdate);
            System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE CUSTOMER SUCCEEDED @@@@@@@@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE CUSTOMER TEST2 @@@@@@@@@@@@@@@@@@@@@@");
        try {
            custToUpdate = adminService.getSingleCustomer(4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        custToUpdate.setLastName("levi");
        try {
            adminService.updateCustomer(custToUpdate.getId(), custToUpdate);
            System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE CUSTOMER SUCCEEDED @@@@@@@@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE CUSTOMER @@@@@@@@@@@@@@@@@@@@@@");
        try {
            adminService.deleteCustomer(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminService.getAllCustomers().forEach(System.out::println);
    }
}