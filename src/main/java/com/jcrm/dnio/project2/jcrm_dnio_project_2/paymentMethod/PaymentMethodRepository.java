package com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

//    @Query(value = "SELECT s FROM PaymentMethod s WHERE s.code = ?1", nativeQuery = true)
    Optional<PaymentMethod> findPaymentMethodByCode(String code);

}
