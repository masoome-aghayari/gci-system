package com.gci.workshop.agent.controller;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import com.gci.workshop.agent.model.dto.LoginDto;
import com.gci.workshop.agent.model.dto.LogoutDto;
import com.gci.workshop.agent.model.dto.RefreshTokenDto;
import com.gci.workshop.agent.model.dto.WorkshopAgentDto;
import com.gci.workshop.agent.service.WorkshopAgentService;
import feign.FeignException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(WorkshopAgentController.BASE_URL)
@RequiredArgsConstructor
public class WorkshopAgentController {
    public static final String BASE_URL = "/agent";
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";
    private static final String REFRESH_TOKEN = "/token/refresh";

    private final WorkshopAgentService workshopAgentService;

    @PostMapping()
    public ResponseEntity<WorkshopAgentDto> register(@Valid @RequestBody WorkshopAgentDto workshopAgentDto) {
        return new ResponseEntity<>(workshopAgentService.register(workshopAgentDto), HttpStatus.CREATED);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<AccessTokenResponse> login(@Valid @RequestBody LoginDto loginDto) throws FeignException {
        return new ResponseEntity<>(workshopAgentService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping(REFRESH_TOKEN)
    public ResponseEntity<AccessTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenDto refreshTokenDto)
            throws FeignException {
        return new ResponseEntity<>(workshopAgentService.refreshToken(refreshTokenDto), HttpStatus.OK);
    }

    @PostMapping(LOGOUT)
    @PreAuthorize("hasRole('WORKSHOP_AGENT')")
    public ResponseEntity logout(@Valid @RequestBody LogoutDto logoutDto) {
        workshopAgentService.logout(logoutDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
