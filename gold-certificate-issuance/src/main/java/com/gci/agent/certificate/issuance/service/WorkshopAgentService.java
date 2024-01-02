package com.gci.agent.certificate.issuance.service;

/*
 * @author masoome.aghayari
 * @since 1/1/24
 */

import com.gci.agent.certificate.issuance.model.dto.LoginDto;
import com.gci.agent.certificate.issuance.model.dto.LogoutDto;
import com.gci.agent.certificate.issuance.model.dto.RefreshTokenDto;
import com.gci.agent.certificate.issuance.model.dto.WorkshopAgentDto;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

@Service
public interface WorkshopAgentService {

    void register(WorkshopAgentDto workshopAgentDto);

    AccessTokenResponse login(LoginDto loginDto);

    void logout(LogoutDto logoutDto);

    AccessTokenResponse refreshToken(RefreshTokenDto refreshTokenDto);
}
