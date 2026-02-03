package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.persistence.dao.PlantillaDocumentDao;
import com.jcerdar.gestorfutbol.persistence.model.PlantillaDocument;
import com.jcerdar.gestorfutbol.service.model.PlantillaDocumentDTO;
import com.jcerdar.gestorfutbol.service.util.HtmlCleaner;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class PdfGeneradorServiceImpl implements PdfGeneradorService {

    @Autowired
    private PlantillaDocumentDao plantillaDocumentDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<PlantillaDocumentDTO> getAllPlantillesDocuments() {
        List<PlantillaDocument> plantilles = plantillaDocumentDao.findAll();
        return plantilles.stream()
                .map(plantilla -> modelMapper.map(plantilla, PlantillaDocumentDTO.class))
                .toList();
    }

    @Override
    public PlantillaDocumentDTO getPlantillaDocumentByCodi(String codi) {
        PlantillaDocument plantillaDocument = plantillaDocumentDao
                .findByCodi(codi)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe plantilla activa para el código: " + codi
                        )
                );
        return modelMapper.map(plantillaDocument, PlantillaDocumentDTO.class);
    }

    @Override
    public Long savePlantillaDocument(PlantillaDocumentDTO plantillaDocumentDTO) {
        PlantillaDocument plantillaDocument = plantillaDocumentDao.findByCodi(plantillaDocumentDTO.getCodi()).orElse(new PlantillaDocument());
        plantillaDocument.setContingutHtml(plantillaDocumentDTO.getContingutHtml());
        plantillaDocument = plantillaDocumentDao.save(plantillaDocument);
        return plantillaDocument.getId();
    }

    @Override
    public String generarPdfDesdeHtml(String codi, String lang, Map<String, String> data) {
        PlantillaDocument plantilla = plantillaDocumentDao
                .findByCodi(codi)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe plantilla activa para el código: " + codi
                        )
                );

        String htmlFinal = buildHtml(plantilla, data);

        byte[] pdfBytes = generatePdf(htmlFinal);

        return Base64.getEncoder().encodeToString(pdfBytes);
    }

    private String buildHtml(PlantillaDocument plantilla, Map<String, String> data) {

        String html = plantilla.getContingutHtml();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            html = html.replace(
                    "{{" + entry.getKey() + "}}",
                    entry.getValue() != null ? entry.getValue() : ""
            );
        }

        return """
                <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
                                 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
                <html xmlns="http://www.w3.org/1999/xhtml" lang="ca">
                  <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                    <style type="text/css">
                      %s
                    </style>
                  </head>
                  <body>
                    %s
                  </body>
                </html>
                """.formatted(
                plantilla.getContingutCss() != null ? plantilla.getContingutCss() : "",
                html
        );
    }

    private byte[] generatePdf(String htmlContent) {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            String cleanHtml = HtmlCleaner.cleanHtmlForPdf(htmlContent);

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(cleanHtml, null);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            builder.toStream(os);
            builder.run();

            return os.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF con OpenHTMLToPDF", e);
        }
    }
}
