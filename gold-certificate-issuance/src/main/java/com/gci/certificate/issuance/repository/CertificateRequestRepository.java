package com.gci.certificate.issuance.repository;

import com.gci.certificate.issuance.model.entity.CertificateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */
@Repository
public interface CertificateRequestRepository extends JpaRepository<CertificateRequest, UUID> {

    Optional<CertificateRequest> findByTrackingCode(String trackingCode);

    boolean existsByGold_Code(String code);
}
