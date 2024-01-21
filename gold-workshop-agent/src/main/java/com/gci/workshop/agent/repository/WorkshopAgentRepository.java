package com.gci.workshop.agent.repository;

import com.gci.workshop.agent.model.entity.WorkshopAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/*
 * @author masoome.aghayari
 * @since 1/21/24
 */
@Repository
public interface WorkshopAgentRepository extends JpaRepository<WorkshopAgent, UUID> {
}
