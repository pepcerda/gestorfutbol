package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.persistence.model.Tenant;
import com.jcerdar.gestorfutbol.persistence.model.Usuari;

public interface EmailService {

    void enviarAvisBenvinguda(Usuari usuari, Tenant tenant);
}
