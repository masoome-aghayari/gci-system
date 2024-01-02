package com.gci.agent.certificate.issuance.controller;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import com.gci.agent.certificate.issuance.model.dto.LoginDto;
import com.gci.agent.certificate.issuance.model.dto.LogoutDto;
import com.gci.agent.certificate.issuance.model.dto.RefreshTokenDto;
import com.gci.agent.certificate.issuance.model.dto.WorkshopAgentDto;
import com.gci.agent.certificate.issuance.service.impl.WorkshopAgentService;
import feign.FeignException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor
public class WorkshopAgentController {
    private final WorkshopAgentService workshopAgentService;

    @PostMapping()
    public ResponseEntity register(@RequestBody WorkshopAgentDto workshopAgentDto) {
        workshopAgentService.register(workshopAgentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AccessTokenResponse login(@NotNull @RequestBody LoginDto loginDto) throws FeignException {
        return workshopAgentService.login(loginDto);
    }

    @PostMapping("/refresh-token")
    public AccessTokenResponse refreshToken(@NotNull @RequestBody RefreshTokenDto refreshTokenDto) throws FeignException {
        return workshopAgentService.refreshToken(refreshTokenDto);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(LogoutDto logoutDto) {
        workshopAgentService.logout(logoutDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}