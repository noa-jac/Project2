package com.jb.couponsysbhp2.clr;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import com.jb.couponsysbhp2.repos.CompanyRepository;
import com.jb.couponsysbhp2.repos.CouponRepository;
import com.jb.couponsysbhp2.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@Order(1)
@RequiredArgsConstructor

public class Init implements CommandLineRunner {
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {

        System.out.println("@@@@@@@@@@@@@@@@@@@ START INIT @@@@@@@@@@@@@@@@@@@@@@@@");


        System.out.println("@@@@@@@@@@@@@@@@@@@ COMPANIES_TABLE @@@@@@@@@@@@@@@@@@@@@@@@");

        Company comp1 = Company.builder()
                .name("nike")
                .email("nike@gmail.com")
                .password("1234")
                .build();
        Company comp2 = Company.builder()
                .name("dell")
                .email("dell@gmail.com")
                .password("1234")
                .build();
        Company comp3 = Company.builder()
                .name("my art")
                .email("my-art@gmail.com")
                .password("1234")
                .build();

        System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT COMPANIES_TABLE @@@@@@@@@@@@@@@@@@@");
        companyRepository.saveAll(Arrays.asList(comp1, comp2, comp3));
        companyRepository.findAll().forEach(System.out::println);

        Company compToUpdate = companyRepository.getById(2);
        compToUpdate.setEmail("alex@gmil.com");
        companyRepository.saveAndFlush(compToUpdate);
        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE - COMPANIES_TABLE @@@@@@@@@@@@@@@@@@@");
        companyRepository.findAll().forEach(System.out::println);

        companyRepository.deleteById(2);
        System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE - COMPANIES_TABLE @@@@@@@@@@@@@@@@@@@");
        companyRepository.findAll().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ GET SINGLE - COMPANIES_TABLE @@@@@@@@@@@@@@@@@");
        System.out.println(companyRepository.getById(1));

        System.out.println("@@@@@@@@@@@@@@@@@@@ GET ALL - COMPANIES_TABLE @@@@@@@@@@@@@@@@@");
        companyRepository.findAll().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ CUSTOMERS_TABLE @@@@@@@@@@@@@@@@@@@@@@@@");

        Customer cust1 = Customer.builder()
                .firstName("adam")
                .lastName("lev")
                .email("adam-lev@gmail.com")
                .password("1234")
                .build();
        Customer cust2 = Customer.builder()
                .firstName("mor")
                .lastName("tal")
                .email("mor-tal@gmail.com")
                .password("1234")
                .build();
        Customer cust3 = Customer.builder()
                .firstName("miri")
                .lastName("cohen")
                .email("miri-cohon@gmail.com")
                .password("1234")
                .build();

        System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT CUSTOMERS_TABLE @@@@@@@@@@@@@@@@@@@");
        customerRepository.saveAll(Arrays.asList(cust1, cust2, cust3));
        customerRepository.findAll().forEach(System.out::println);

        Customer custToUpdate = customerRepository.getById(2);
        custToUpdate.setEmail("aaa@gmil.com");
        customerRepository.saveAndFlush(custToUpdate);

        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE - CUSTOMERS_TABLE @@@@@@@@@@@@@@@@@@@");
        customerRepository.findAll().forEach(System.out::println);

        customerRepository.deleteById(3);

        System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE - CUSTOMERS_TABLE @@@@@@@@@@@@@@@@@@@");
        customerRepository.findAll().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ GET SINGLE - CUSTOMERS_TABLE @@@@@@@@@@@@@@@@@@@");
        System.out.println(customerRepository.getById(1));

        System.out.println("@@@@@@@@@@@@@@@@@@@ GET ALL - CUSTOMERS_TABLE @@@@@@@@@@@@@@@@@@@");
        customerRepository.findAll().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ COUPONS_TABLE @@@@@@@@@@@@@@@@@@@@@@@@");

        Coupon coup1 = Coupon.builder()
                .title("sport kit")
                .category(Category.SPORT)
                .amount(5)
                .price(40.99)
                .image("http://somthing.com")
                .description("best kit ever")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .company(companyRepository.getById(1))
                .build();
        Coupon coup2 = Coupon.builder()
                .title("art kit")
                .category(Category.ART)
                .amount(10)
                .price(10.99)
                .image("http://somthing.com")
                .description("best kit ever")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .company(companyRepository.getById(3))
                .build();
        Coupon coup3 = Coupon.builder()
                .title("art kit 1+1")
                .category(Category.ART)
                .amount(30)
                .price(4.99)
                .image("http://somthing.com")
                .description("best kit ever")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .company(companyRepository.getById(3))
                .build();

        System.out.println("@@@@@@@@@@@@@@@@@@@ INSERT - COUPONS_TABLE @@@@@@@@@@@@@@@@@@@");
        couponRepository.saveAll(Arrays.asList(coup1, coup2, coup3));
        couponRepository.findAll().forEach(System.out::println);

        Coupon coupToUpdate = couponRepository.getById(3);
        coupToUpdate.setDescription("eeeeeeeee");
        couponRepository.saveAndFlush(coupToUpdate);
        System.out.println("@@@@@@@@@@@@@@@@@@@ UPDATE - COUPONS_TABLE @@@@@@@@@@@@@@@@@@@");
        couponRepository.findAll().forEach(System.out::println);

        couponRepository.deleteById(3);
        System.out.println("@@@@@@@@@@@@@@@@@@@ DELETE - COUPONS_TABLE @@@@@@@@@@@@@@@@@@@");
        couponRepository.findAll().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@ GET SINGLE - COUPONS_TABLE @@@@@@@@@@@@@@@@@@@");
        System.out.println(couponRepository.getById(1));

        System.out.println("@@@@@@@@@@@@@@@@@@@ GET ALL - COUPONS_TABLE @@@@@@@@@@@@@@@@@@@");
        couponRepository.findAll().forEach(System.out::println);

    }
}
