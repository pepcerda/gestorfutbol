package com.jcerdar.gestorfutbol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jcerdar.gestorfutbol.service.model.CreacioUsuariRequestDTO;

@Service
public class KindeAdminClientImpl implements ProveidorUsuarisAdminClient {

    @Autowired
    private OAuthClientService oAuthClientService;

    private final WebClient webClient;

    @Value("${kinde.admin.audience}")
    private String audience;

    public KindeAdminClientImpl(WebClient.Builder builder) {
        this.webClient = builder.build();
    }



    @Override
    public void crearUsuari(CreacioUsuariRequestDTO creacioUsuariRequestDTO) {
       String token = oAuthClientService.getAccessToken();

        webClient.post()
                .uri(audience + "/v1/users")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creacioUsuariRequestDTO)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    @Override
    public String listUsuaris() {
        String token = oAuthClientService.getAccessToken();

        return webClient.get()
                .uri(audience + "/v1/users")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
