package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import com.jb.couponsysbhp2.exceptions.CouponSystemException;

import java.util.List;

public interface CustomerService {
    void purchaseCoupon(Coupon coupon) throws CouponSystemException;
    List<Coupon> getCustomerCoupon() throws CouponSystemException;
    List<Coupon> getCustomerCoupon(Category category) throws CouponSystemException;
    List<Coupon> getCustomerCoupon(Double maxPrice) throws CouponSystemException;
    Customer getCustomerDetails() throws CouponSystemException;
}