package com.jb.couponsysbhp2.clr;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import com.jb.couponsysbhp2.exceptions.CouponSystemException;
import com.jb.couponsysbhp2.repos.CompanyRepository;
import com.jb.couponsysbhp2.repos.CouponRepository;
import com.jb.couponsysbhp2.services.AdminService;
import com.jb.couponsysbhp2.services.CustomerService;
import com.jb.couponsysbhp2.services.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@Order(4)
@RequiredArgsConstructor
public class UseCaseCustomerService implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final CustomerService customerService;
    private final CouponRepository couponRepository;
    private final AdminService adminService;

    @Override
    public void run(String... args) {

        System.out.println("@@@@@@@@@@@@@@@@@@@ START CUSTOMER SERVICE @@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@ START LOGIN TEST @@@@@@@@@@@@@@@@@@@@@@@@");

        Customer cust = Customer.builder()
                .firstName("ben")
                .lastName("dod")
                .password("1234")
                .email("ben-dod@gmail.com")
                .coupons(Arrays.asList(couponRepository.getById(4), couponRepository.getById(5), couponRepository.getById(6)))
                .build();
        try {
            adminService.addCustomer(cust);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //((CustomerServiceImpl) customerService).setCustomerId(6);
        try {
            if (((CustomerServiceImpl) customerService).login("ben-dod@gmail.com", "1234")) {
                System.out.println(customerService.getCustomerDetails().getFirstName() + " is logged on");
            }
        } catch (Exception e) {
            System.out.println("failed to logged on" + e.getMessage());
        }


        try {
            System.out.println("@@@@@@@@@@@@@@@@@@@ GET CUSTOMER DETAILS @@@@@@@@@@@@@@@@");
            System.out.println(customerService.getCustomerDetails());
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Coupon coup = Coupon.builder()
                .title("sport kit 1+1+1")
                .category(Category.SPORT)
                .amount(30)
                .price(20.99)
                .image("http://somthing.com")
                .description("best kit 3+3")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(14)))
                .company(companyRepository.getById(4))
                .build();
        couponRepository.save(coup);

        System.out.println("@@@@@@@@@@@@@@@@@@@ PURCHASE COUPON TEST @@@@@@@@@@@@@@@@");
        try {
            customerService.purchaseCoupon(couponRepository.getById(6));
            System.out.println("@@@@@@@@@@@@@@@@@@@ PURCHASE COUPON SUCCEEDED @@@@@@@@@@@@@@@@");

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@ PURCHASE COUPON TEST2 @@@@@@@@@@@@@@@@");
        try {
            customerService.purchaseCoupon(couponRepository.getById(8));
            System.out.println("@@@@@@@@@@@@@@@@@@@ PURCHASE COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@ PURCHASE COUPON TEST3 @@@@@@@@@@@@@@@@");

        Coupon coupToUpdate = couponRepository.getById(8);
        coupToUpdate.setEndDate(Date.valueOf(LocalDate.now().plusDays(14)));
        coupToUpdate.setAmount(0);
        ;
        couponRepository.save(coupToUpdate);
        try {
            customerService.purchaseCoupon(couponRepository.getById(8));
            System.out.println("@@@@@@@@@@@@@@@@@@@ PURCHASE COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@ PURCHASE COUPON TEST4 @@@@@@@@@@@@@@@@");
        try {
            customerService.purchaseCoupon(couponRepository.getById(7));
            System.out.println("@@@@@@@@@@@@@@@@@@@ PURCHASE COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("@@@@@@@@@@@@@@@@@@@ GET_ALL CUSTOMER COUPONS @@@@@@@@@@@@@@@@@@@");
            customerService.getCustomerCoupon().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("@@@@@@@@@@@@@@@@@@@ GET ALL CUSTOMER COUPONS BY CATEGORY @@@@@@@@@@@@@@@@@@@");
            customerService.getCustomerCoupon(Category.ART).forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("@@@@@@@@@@@@@@@@@@@ GET ALL CUSTOMER COUPONS BY MAX PRICE @@@@@@@@@@@@@@@@@@@");
            customerService.getCustomerCoupon(300.0).forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Coupon c1 = couponRepository.getById(7);
        c1.setEndDate(Date.valueOf(LocalDate.now().minusDays(14)));
        couponRepository.saveAndFlush(c1);

    }

}