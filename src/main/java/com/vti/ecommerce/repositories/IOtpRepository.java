package com.vti.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.Otp;

@Repository
public interface IOtpRepository extends JpaRepository<Otp, Long>{
    Otp findByOtp(String otp);

    @Query("SELECT o FROM Otp o WHERE o.user.email = :email")
    Otp findByEmail(String email);

}
