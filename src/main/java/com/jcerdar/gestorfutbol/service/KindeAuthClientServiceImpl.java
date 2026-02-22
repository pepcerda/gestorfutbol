package com.jcerdar.gestorfutbol.service;

import java.time.Instant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.jcerdar.gestorfutbol.service.model.KindeTokenResponseDTO;

@Service
public class KindeAuthClientServiceImpl  implements OAuthClientService {

    Log log = LogFactory.getLog(KindeAuthClientServiceImpl.class);

    private final WebClient webClient;

    @Value("${jwt.uri}")
    private String domain;

    @Value("${kinde.admin.client-id}")
    private String clientId;

    @Value("${kinde.admin.client-secret}")
    private String clientSecret;

    @Value("${kinde.admin.audience}")
    private String audience;

    @Value("${kinde.scope}")
    private String scope;

    private String cachedToken;
    private Instant expiresAt;

    public KindeAuthClientServiceImpl(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    @Override
    public synchronized String getAccessToken() {

        if (cachedToken != null && expiresAt != null &&
            Instant.now().isBefore(expiresAt.minusSeconds(60))) {

            return cachedToken;
        }

        
        log.info("Requesting new access token from Kinde");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("audience", audience);
        body.add("scope", scope);

        KindeTokenResponseDTO response =
                webClient.post()
                        .uri(domain + "/oauth2/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(KindeTokenResponseDTO.class)
                        .block();

        this.cachedToken = response.getAccess_token();
        this.expiresAt = Instant.now().plusSeconds(response.getExpires_in());
        return cachedToken;
    }

}
