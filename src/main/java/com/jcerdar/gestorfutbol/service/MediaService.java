package com.jcerdar.gestorfutbol.service;

import java.io.IOException;

public interface MediaService {

    String guardarLogoBase64(String base64Data) throws IOException;

    String guardarDespesaB64(String base64Data) throws IOException;

}
