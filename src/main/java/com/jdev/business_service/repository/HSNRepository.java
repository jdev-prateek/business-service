package com.jdev.business_service.repository;

import com.jdev.business_service.entity.HSN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface HSNRepository extends JpaRepository<HSN, UUID> {
    @Query("select h from HSN h where h.code = :code")
    Optional<HSN> findByCode(String code);
}

