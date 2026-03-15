package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.service.model.CreacioUsuariRequestDTO;
import com.jcerdar.gestorfutbol.service.model.ProveidorResponseDTO;

public interface ProveidorUsuarisAdminClient {

    ProveidorResponseDTO crearUsuari(CreacioUsuariRequestDTO creacioUsuariRequestDTO);

    String listUsuaris();

    void eliminarUsuari(String userId);

}
