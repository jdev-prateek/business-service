package com.jdev.business_service.repository;

import com.jdev.business_service.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
    @Override
    Optional<Business> findById(UUID uuid);

    @Query("select b from Business b where gstin = :gstin")
    Optional<Business> findByGstin(String gstin);
}
