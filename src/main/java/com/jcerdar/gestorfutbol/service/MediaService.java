package com.jcerdar.gestorfutbol.service;

import java.io.IOException;

public interface MediaService {

    Boolean checkBase64(String base64Data);

    String guardarLogoBase64(String base64Data) throws IOException;

    String guardarDespesaB64(String base64Data) throws IOException;

}
