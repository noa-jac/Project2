package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Coupon;
import com.jb.couponsysbhp2.exceptions.CouponSystemException;
import com.jb.couponsysbhp2.exceptions.ErrMsg;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Getter
    @Setter
    private int companyId;

    @Override
    public boolean login(String email, String password) {
        setCompanyId(this.companyRepository.findByEmailAndPassword(email, password).getId());
        return companyId > 0;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        if (this.couponRepository.existsByCompanyIdAndTitle(companyId, coupon.getTitle())) {
            throw new CouponSystemException(ErrMsg.TITLE_ALREADY_EXIST_IN_COMPANY_DB);
        }
        if ((coupon.getCompany().getId() != companyId)) {
            throw new CouponSystemException(ErrMsg.CANNOT_ALTER_OTHER_COMPANIES_COUPONS);
        }
        this.couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        if (!this.couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.COUPON_ID_NOT_EXIST);
        }
        if (coupon.getCompany().getId() != companyId) {
            throw new CouponSystemException(ErrMsg.CANNOT_ALTER_OTHER_COMPANIES_COUPONS);
        }
        this.couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        if (!this.couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.COUPON_ID_NOT_EXIST);
        }
        if ((this.couponRepository.getById(couponId).getCompany().getId() != companyId)) {
            throw new CouponSystemException(ErrMsg.CANNOT_ALTER_OTHER_COMPANIES_COUPONS);
        }
        this.couponRepository.deleteCustomerVsCouponByCouponId(couponId);
        this.couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons() throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        return this.companyRepository.getById(companyId).getCoupons();
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category) throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        return this.couponRepository.findByCompanyIdAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Double maxPrise) throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        return this.couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrise);
    }

    @Override
    public Company getCompanyDetails() throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        return this.companyRepository.getById(companyId);
    }
}