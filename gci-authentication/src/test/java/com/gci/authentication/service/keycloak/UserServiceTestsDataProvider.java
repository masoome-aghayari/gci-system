package com.gci.authentication.service.keycloak;

/*
 * @author masoome.aghayari
 * @since 12/31/23
 */

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class UserServiceTestsDataProvider {
   protected Map<String,?> getValidLoginRequestBodyMap(){
      return null;
   }

   protected AccessTokenResponse getAccessTokenResponse() {
      return null;
   }

   protected Map<String,?> getInvalidLoginRequestBody() {
      return null;
   }
}
