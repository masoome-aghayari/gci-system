package com.gci.workshop.agent.service.impl;

/*
 * @author masoome.aghayari
 * @since 1/1/24
 */

import com.gci.workshop.agent.mapper.WorkshopAgentMapper;
import com.gci.workshop.agent.model.dto.*;
import com.gci.workshop.agent.repository.WorkshopAgentRepository;
import com.gci.workshop.agent.service.WorkshopAgentService;
import com.gci.workshop.agent.service.feign.AuthenticationFeignClient;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class WorkshopAgentServiceImpl implements WorkshopAgentService {
    private final AuthenticationFeignClient authenticationFeignClient;
    private final WorkshopAgentRepository workshopAgentRepository;
    private final WorkshopAgentMapper workshopAgentMapper;

    @Override
    public WorkshopAgentDto register(WorkshopAgentDto workshopAgentDto) {
        authenticationFeignClient.registerUser(getUserDto(workshopAgentDto));
        var workshopAgent = workshopAgentMapper.dtoToEntity(workshopAgentDto);
        workshopAgent = workshopAgentRepository.save(workshopAgent);
        return workshopAgentMapper.entityToDto(workshopAgent);
    }

    @Override
    public AccessTokenResponse login(LoginDto loginDto) {
        return authenticationFeignClient.getToken(loginDto);
    }

    @Override
    public AccessTokenResponse refreshToken(RefreshTokenDto refreshTokenDto) {
        return authenticationFeignClient.refreshToken(refreshTokenDto);
    }

    @Override
    public void logout(LogoutDto logoutDto) {
        var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        var httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(request.getHeader("Authorization"));
        authenticationFeignClient.logout(httpHeaders, logoutDto);
    }

    private UserDto getUserDto(WorkshopAgentDto agentDto) {
        return UserDto.builder().firstname(agentDto.getName())
                .lastname(agentDto.getSurname())
                .password(agentDto.getPassword())
                .username(agentDto.getUsername())
                .credentials(Set.of(getCredential(agentDto)))
                .enabled(true)
                .build();
    }

    private CredentialRepresentation getCredential(WorkshopAgentDto agentDto) {
        var credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(false);
        credential.setValue(agentDto.getPassword());
        return credential;
    }
}
