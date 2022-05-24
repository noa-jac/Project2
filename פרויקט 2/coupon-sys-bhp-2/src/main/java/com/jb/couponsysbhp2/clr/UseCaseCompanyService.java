package com.jb.couponsysbhp2.clr;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import com.jb.couponsysbhp2.exceptions.CouponSystemException;
import com.jb.couponsysbhp2.repos.CouponRepository;
import com.jb.couponsysbhp2.services.AdminService;
import com.jb.couponsysbhp2.services.CompanyService;
import com.jb.couponsysbhp2.services.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;


@Component
@Order(3)
@RequiredArgsConstructor
public class UseCaseCompanyService implements CommandLineRunner {

    private final CompanyService companyService;
    private final AdminService adminService;
    private final CouponRepository couponRepository;

    @Override
    public void run(String... args) {

        System.out.println("@@@@@@@@@@@@@@@@@@@ START COMPANY SERVICE @@@@@@@@@@@@@@@@@@@@@@@@");

        System.out.println("@@@@@@@@@@@@@@@@@@@ START LOGIN TEST @@@@@@@@@@@@@@@@@@@@@@@@");
        ((CompanyServiceImpl) companyService).setCompanyId(3);
        try {
            if (((CompanyServiceImpl) companyService).login("my-art@gmail.com", "1234")) {
                System.out.println(companyService.getCompanyDetails().getName() + " is logged on");
            }
        } catch (Exception e) {
            System.out.println("failed to logged on" + e.getMessage());
        }


        System.out.println("@@@@@@@@@@@@@@@@@@@ COUPONS_TABLE @@@@@@@@@@@@@@@@@@@@@@@@");

        System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COUPON TESTS @@@@@@@@@@@@@@@@");

        Coupon coup = null;
        try {
            coup = Coupon.builder()
                    .title("art kit")
                    .category(Category.ART)
                    .amount(30)
                    .price(4.99)
                    .image("http://somthing.com")
                    .description("best kit ever")
                    .startDate(Date.valueOf(LocalDate.now()))
                    .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                    .company(companyService.getCompanyDetails())
                    .build();
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        try {
            companyService.addCoupon(coup);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            companyService.getCompanyCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        coup = null;
        Company comp1 = null;
        try {
            comp1 = companyService.getCompanyDetails();
            comp1.setId(70);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        coup = Coupon.builder()
                .title("art kit 1+1+1")
                .category(Category.ART)
                .amount(30)
                .price(4.99)
                .image("http://somthing.com")
                .description("best kit ever")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .company(comp1)
                .build();

        try {
            companyService.addCoupon(coup);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            companyService.getCompanyCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        coup = null;

        try {
            coup = Coupon.builder()
                    .title("pc kit 1+1")
                    .category(Category.PC)
                    .amount(30)
                    .price(24.99)
                    .image("http://somthing.com")
                    .description("pc kit")
                    .startDate(Date.valueOf(LocalDate.now()))
                    .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                    .company(companyService.getCompanyDetails())
                    .build();
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyService.addCoupon(coup);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        coup = null;

        try {
            coup = Coupon.builder()
                    .title("art kit 1+1+2")
                    .category(Category.ART)
                    .amount(2)
                    .price(204.99)
                    .image("http://somthing.com")
                    .description("best kit ever and ever and ever")
                    .startDate(Date.valueOf(LocalDate.now()))
                    .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                    .company(companyService.getCompanyDetails())
                    .build();
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyService.addCoupon(coup);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        coup = null;

        try {
            coup = Coupon.builder()
                    .title("art kit 1+1+5")
                    .category(Category.ART)
                    .amount(30_000)
                    .price(404.99)
                    .image("http://somthing.com")
                    .description("best kit ever")
                    .startDate(Date.valueOf(LocalDate.now()))
                    .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                    .company(companyService.getCompanyDetails())
                    .build();
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyService.addCoupon(coup);
            System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            companyService.getCompanyCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COUPON TEST1 @@@@@@@@@@@@@@@@");

        Coupon coupToUpdate = null;
        try {
            coupToUpdate = companyService.getCompanyCoupons().get(0);
            coupToUpdate.setDescription("best for ever and ever");
            coupToUpdate.setId(555);
            companyService.updateCoupon(coupToUpdate.getId(), coupToUpdate);
            System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        try {
            companyService.getCompanyCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COUPON TEST2 @@@@@@@@@@@@@@@@");

        coupToUpdate = null;
        try {
            coupToUpdate = companyService.getCompanyCoupons().get(0);
            coupToUpdate.setDescription("best for ever and ever");
            coupToUpdate.getCompany().setId(70);
            companyService.updateCoupon(coupToUpdate.getId(), coupToUpdate);
            System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        try {
            companyService.getCompanyCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COUPON TEST3 @@@@@@@@@@@@@@@@");

        coupToUpdate = null;
        try {
            coupToUpdate = companyService.getCompanyCoupons().get(0);
            coupToUpdate.setDescription("best for ever and ever");
            companyService.updateCoupon(coupToUpdate.getId(), coupToUpdate);
            System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        try {
            companyService.getCompanyCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE COUPON TEST1 @@@@@@@@@@@@@@@@");

        Customer cust = Customer.builder()
                .firstName("david")
                .lastName("bar")
                .password("1234")
                .email("david-bar@gmail.com")
                .coupon(couponRepository.getById(2))
                .build();
        try {
            adminService.addCustomer(cust);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            companyService.deleteCoupon(200);
            System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyService.getCompanyCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE COUPON TEST2 @@@@@@@@@@@@@@@@");

        try {
            companyService.deleteCoupon(4);
            System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyService.getCompanyCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE COUPON TEST3 @@@@@@@@@@@@@@@@");

        try {
            companyService.deleteCoupon(2);
            System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE COUPON SUCCEEDED @@@@@@@@@@@@@@@@");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyService.getCompanyCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }


        try {
            System.out.println("@@@@@@@@@@@@@@@@@@@ GET COMPANY DETAILS @@@@@@@@@@@@@@@@");
            System.out.println(companyService.getCompanyDetails());
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("@@@@@@@@@@@@@@@@@@@ GET COMPANY COUPONS @@@@@@@@@@@@@@@@");
            companyService.getCompanyCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("@@@@@@@@@@@@@@@@@@@ GET ALL COUPONS BY CATEGORY @@@@@@@@@@@@@@@@");
            companyService.getCompanyCoupons(Category.ART).forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("@@@@@@@@@@@@@@@@@@@ GET ALL COUPONS BY MAX PRICE @@@@@@@@@@@@@@@@");
            companyService.getCompanyCoupons(300.0).forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

   }
}