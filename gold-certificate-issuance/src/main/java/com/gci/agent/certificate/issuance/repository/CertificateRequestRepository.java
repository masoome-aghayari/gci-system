package com.gci.agent.certificate.issuance.repository;

import com.gci.agent.certificate.issuance.model.entity.CertificateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */
@Repository
public interface CertificateRequestRepository extends JpaRepository<CertificateRequest, String> {

    Optional<CertificateRequest> findByTrackingCode(String trackingCode);

    boolean existsByGold_Code(String code);
}
