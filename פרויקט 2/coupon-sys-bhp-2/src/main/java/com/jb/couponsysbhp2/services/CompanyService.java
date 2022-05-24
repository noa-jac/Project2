package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    void addCoupon(Coupon coupon) throws CouponSystemException;
    void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException;
    void deleteCoupon(int couponId) throws CouponSystemException;
    List<Coupon> getCompanyCoupons() throws CouponSystemException;
    List<Coupon> getCompanyCoupons(Category category) throws CouponSystemException;
    List<Coupon> getCompanyCoupons(Double maxPrise) throws CouponSystemException;
    Company getCompanyDetails() throws CouponSystemException;
}