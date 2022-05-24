package com.jb.couponsysbhp2.jobs;

import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DailyRemoval {

    private final CouponRepository couponRepository;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void removeExpiredCoupons() {
        List<Coupon> expCoupons = couponRepository.findByEndDateBefore(Date.valueOf(LocalDate.now()));
        expCoupons.forEach(coupon -> couponRepository.deleteCustomerVsCouponByCouponId(coupon.getId()));
        couponRepository.deleteByEndDateBefore(Date.valueOf(LocalDate.now()));
    }

}