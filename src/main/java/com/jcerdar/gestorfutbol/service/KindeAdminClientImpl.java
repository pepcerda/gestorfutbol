package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.service.model.ProveidorResponseDTO;
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
    public ProveidorResponseDTO crearUsuari(CreacioUsuariRequestDTO creacioUsuariRequestDTO) {
       String token = oAuthClientService.getAccessToken();

        return webClient.post()
                .uri(audience + "/v1/user")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creacioUsuariRequestDTO)
                .retrieve()
                // Aquí especificamos que queremos el cuerpo mapeado a nuestra clase
                .bodyToMono(ProveidorResponseDTO.class)
                .block(); // Bloqueamos para obtener el resultado de forma síncrona
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

    @Override
    public void eliminarUsuari(String userId) {
        String token = oAuthClientService.getAccessToken();

        webClient.delete()
                .uri(audience + "/v1/user?id=" + userId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

}
