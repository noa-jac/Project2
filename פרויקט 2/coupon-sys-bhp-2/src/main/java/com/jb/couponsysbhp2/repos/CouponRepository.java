package com.jb.couponsysbhp2.repos;

import com.jb.couponsysbhp2.beans.Category;
import com.jb.couponsysbhp2.beans.Coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Transactional
    @Modifying
    void deleteByEndDateBefore(Date expiryDate);

    List<Coupon> findByEndDateBefore(Date expiryDate);
    boolean existsByCompanyIdAndTitle(int companyId, String title);
    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);
    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, Double price);

    @Query(value =
            "SELECT * FROM couponsystem.coupons " +
                    "INNER JOIN couponsystem.customers_coupons " +
                    "ON coupons.id = customers_coupons.coupons_id " +
                    "AND customers_coupons.customer_id = :customerId " +
                    "AND coupons.price <= :price "
            , nativeQuery = true)
    List<Coupon> findByCustomerIdAndPriceLessThan(@Param("customerId") int customerId, @Param("price") Double price);

    @Query(value =
            "SELECT * FROM couponsystem.coupons " +
                    "INNER JOIN couponsystem.customers_coupons " +
                    "ON coupons.id = customers_coupons.coupons_id " +
                    "AND customers_coupons.customer_id = :customerId " +
                    "AND coupons.category = :categoryId "
            , nativeQuery = true)
    List<Coupon> findByCustomerIdAndCategory(@Param("customerId") int customerId, @Param("categoryId") int categoryId);

    @Query(value =
            "SELECT COUNT(*) FROM couponsystem.customers_coupons " +
                    "WHERE customers_coupons.customer_id = :customerId " +
                    "AND customers_coupons.coupons_id = :couponsId"
            , nativeQuery = true)
    int existsByCustomerIdAndCouponsId(@Param("customerId") int customerId, @Param("couponsId") int couponsId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM couponsystem.customers_coupons WHERE Coupons_Id = :CouponId", nativeQuery = true)
    void deleteCustomerVsCouponByCouponId(@Param("CouponId") int CouponId);

    @Modifying
    @Transactional
    @Query(value =
            "DELETE FROM couponsystem.customers_coupons " +
                    "WHERE customers_coupons.customer_id = :customerId " +
                    "AND customers_coupons.coupons_id = :couponsId"
            , nativeQuery = true)
    void deleteCustomerVsCouponByCustomerIdAndCouponId(@Param("customerId") int customerId, @Param("couponsId") int couponsId);

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO couponsystem.customers_coupons " +
                    "(customer_id, coupons_id) " +
                    "VALUES (:customerId , :couponsId)"
            , nativeQuery = true)
    void saveCouponForCustomer(@Param("customerId") int customerId, @Param("couponsId") int couponsId);
}