package com.jcerdar.gestorfutbol.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements MediaService {

    @Value("${app.logo.upload-dir}")
    private String logoDir;

    @Value("${app.logo.access-url}")
    private String accessUrl;

    public String guardarLogoBase64(String base64Data) throws IOException {
        if (base64Data == null || base64Data.isEmpty())
            return null;

        // Quitar encabezado si viene con "data:image/png;base64,..."
        String[] parts = base64Data.split(",");
        String base64Image = parts.length > 1 ? parts[1] : parts[0];

        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        String filename = UUID.randomUUID().toString() + ".png";
        File outputFile = new File(logoDir + filename);

        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(imageBytes);
        }

        // Retornar la URL relativa o absoluta del logo
        return accessUrl + filename;
    }

}
