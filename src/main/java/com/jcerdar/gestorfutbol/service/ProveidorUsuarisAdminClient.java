package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.service.model.CreacioUsuariRequestDTO;

public interface ProveidorUsuarisAdminClient {

    void crearUsuari(CreacioUsuariRequestDTO creacioUsuariRequestDTO);

    String listUsuaris();

}
