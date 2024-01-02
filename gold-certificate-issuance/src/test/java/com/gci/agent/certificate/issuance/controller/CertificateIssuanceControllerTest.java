package com.gci.agent.certificate.issuance.controller;

import com.gci.agent.certificate.issuance.CertificateIssuanceTestsDataProvider;
import com.gci.agent.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.agent.certificate.issuance.model.entity.CertificateRequest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

@SpringBootTest
@AutoConfigureMockMvc
class CertificateIssuanceControllerTest extends CertificateIssuanceTestsDataProvider {

    @Test
    public void testRegisterCertificateIssuanceRequestSuccess() throws Exception {
        CertificateRequestDto requestDto = getCertificateRequestDto();
        CertificateRequest requestEntity = getCertificateRequest()/* create a corresponding entity for requestDto */;

        when(repository.existsByGold_Code(requestDto.getGold().getCode())).thenReturn(false);
        when(mapper.dtoToEntity(requestDto)).thenReturn(requestEntity);
        when(repository.save(any())).thenReturn(requestEntity);
        when(mapper.entityToDto(requestEntity)).thenReturn(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/registerCertificate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(/* convert requestDto to JSON */))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(/* expected response JSON */));
    }

    @Test
    public void testRegisterCertificateIssuanceRequestDuplicateGoldException() throws Exception {
        CertificateRequestDto requestDto = new CertificateRequestDto(/* provide necessary data */);

        when(repository.existsByGold_Code(requestDto.getGold().getCode())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/registerCertificate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(/* convert requestDto to JSON */))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(/* expected error response JSON */));
    }
}