package com.gci.certificate.issuance.repository;

import com.gci.certificate.issuance.model.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/*
 * @author masoome.aghayari
 * @since 1/5/24
 */

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, UUID> {

    Optional<Workshop> findByIdAndAgent_Id(UUID workshopId, UUID agentId);

}
