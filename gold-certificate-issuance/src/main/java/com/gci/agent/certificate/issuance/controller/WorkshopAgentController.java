package com.gci.agent.certificate.issuance.controller;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import com.gci.agent.certificate.issuance.model.dto.LoginDto;
import com.gci.agent.certificate.issuance.model.dto.LogoutDto;
import com.gci.agent.certificate.issuance.model.dto.RefreshTokenDto;
import com.gci.agent.certificate.issuance.model.dto.WorkshopAgentDto;
import com.gci.agent.certificate.issuance.service.WorkshopAgentService;
import feign.FeignException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WorkshopAgentController.BASE_URL)
@RequiredArgsConstructor
public class WorkshopAgentController {
    public static final String BASE_URL = "/agent";
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";
    private static final String REFRESH_TOKEN = "/refresh-token";
    private final WorkshopAgentService workshopAgentService;

    @PostMapping()
    public ResponseEntity register(@RequestBody WorkshopAgentDto workshopAgentDto) {
        workshopAgentService.register(workshopAgentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(LOGIN)
    public AccessTokenResponse login(@NotNull @RequestBody LoginDto loginDto) throws FeignException {
        return workshopAgentService.login(loginDto);
    }

    @PostMapping(REFRESH_TOKEN)
    public AccessTokenResponse refreshToken(@NotNull @RequestBody RefreshTokenDto refreshTokenDto) throws FeignException {
        return workshopAgentService.refreshToken(refreshTokenDto);
    }

    @GetMapping(LOGOUT)
    public ResponseEntity logout(LogoutDto logoutDto) {
        workshopAgentService.logout(logoutDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
