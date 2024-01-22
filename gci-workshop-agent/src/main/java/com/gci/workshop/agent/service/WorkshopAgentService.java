package com.gci.workshop.agent.service;

/*
 * @author masoome.aghayari
 * @since 1/1/24
 */

import com.gci.workshop.agent.model.dto.LoginDto;
import com.gci.workshop.agent.model.dto.LogoutDto;
import com.gci.workshop.agent.model.dto.RefreshTokenDto;
import com.gci.workshop.agent.model.dto.WorkshopAgentDto;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

@Service
public interface WorkshopAgentService {

    WorkshopAgentDto register(WorkshopAgentDto workshopAgentDto);

    AccessTokenResponse login(LoginDto loginDto);

    void logout(LogoutDto logoutDto);

    AccessTokenResponse refreshToken(RefreshTokenDto refreshTokenDto);
}
