package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.beans.Customer;
import com.jb.couponsysbhp2.exceptions.CouponSystemException;
import com.jb.couponsysbhp2.exceptions.ErrMsg;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CustomerServiceImpl extends ClientService implements CustomerService {

    @Getter
    @Setter
    private int customerId;


    @Override
    public boolean login(String email, String password) {
        customerId = this.customerRepository.findByEmailAndPassword(email, password).getId();
        return customerId > 0;
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }
        if (!this.couponRepository.existsById(coupon.getId())) {
            throw new CouponSystemException(ErrMsg.COUPON_ID_NOT_EXIST);
        }
        if (this.couponRepository.existsByCustomerIdAndCouponsId(customerId, coupon.getId()) > 0) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ALREADY_PURCHASE_COUPON);
        }
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrMsg.OUT_OF_STOCK_COUPON);
        }
        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrMsg.EXPIRED_COUPON);
        }

        coupon.setAmount(coupon.getAmount() - 1);
        this.couponRepository.saveAndFlush(coupon);
        this.couponRepository.saveCouponForCustomer(customerId, coupon.getId());

    }

    @Override
    public List<Coupon> getCustomerCoupon() throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }
        return this.customerRepository.getById(customerId).getCoupons();
    }

    @Override
    public List<Coupon> getCustomerCoupon(Category category) throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }
        return this.couponRepository.findByCustomerIdAndCategory(customerId, category.ordinal());
    }

    @Override
    public List<Coupon> getCustomerCoupon(Double maxPrice) throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }
        return this.couponRepository.findByCustomerIdAndPriceLessThan(customerId, maxPrice);
    }

    @Override
    public Customer getCustomerDetails() throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }
        return this.customerRepository.getById(customerId);
    }
}