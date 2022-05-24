package com.jb.couponsysbhp2.services;

import com.jb.couponsysbhp2.beans.Company;
import com.jb.couponsysbhp2.beans.Customer;
import com.jb.couponsysbhp2.exceptions.CouponSystemException;

import java.util.List;

public interface AdminService {

    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(int companyId, Company company) throws CouponSystemException;
    void deleteCompany(int companyId) throws CouponSystemException;
    Company getSingleCompany(int companyId) throws CouponSystemException;
    List<Company> getAllCompanies();

    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(int customerId, Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerId) throws CouponSystemException;
    Customer getSingleCustomer(int customerId) throws CouponSystemException;
    List<Customer> getAllCustomers();
}
