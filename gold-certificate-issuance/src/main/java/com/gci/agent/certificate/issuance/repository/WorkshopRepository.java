package com.gci.agent.certificate.issuance.repository;

import com.gci.agent.certificate.issuance.model.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * @author masoome.aghayari
 * @since 1/5/24
 */

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, String> {

    Optional<Workshop> findByIdAndAgent_Id(String workshopId, String agentId);

}
