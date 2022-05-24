package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Customer;
import com.jb.couponsysbhp2.exceptions.CouponSystemException;
import com.jb.couponsysbhp2.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServicesImpl extends ClientService implements AdminService {

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (this.companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_UPDATE_ATTEMPT);
        }
        if (this.companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_EXIST);
        }
        this.companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        if (!this.companyRepository.getById(companyId).getName().equals(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_UPDATE_ATTEMPT);
        }
        this.companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        if (this.companyRepository.findById(companyId).isEmpty()) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        this.companyRepository.getById(companyId).getCoupons().forEach(coupon -> this.couponRepository.deleteCustomerVsCouponByCouponId(coupon.getId()));
        this.companyRepository.getById(companyId).getCoupons().forEach(coupon -> this.couponRepository.deleteById(coupon.getId()));
        this.companyRepository.deleteById(companyId);
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        return this.companyRepository.getById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if (this.customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_EXIST);
        }
        this.customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }
        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }
        this.customerRepository.getById(customerId).getCoupons()
                .forEach(coupon -> this.couponRepository.deleteCustomerVsCouponByCustomerIdAndCouponId(customerId, coupon.getId()));
        this.customerRepository.deleteById(customerId);
    }

    @Override
    public Customer getSingleCustomer(int customerId) throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        return this.customerRepository.getById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

}