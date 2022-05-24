package com.jb.couponsysbhp2.repos;

import com.jb.couponsysbhp2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    Company findByEmailAndPassword(String email, String password);
}