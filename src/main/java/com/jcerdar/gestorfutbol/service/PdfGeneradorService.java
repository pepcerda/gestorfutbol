package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.service.model.PlantillaDocumentDTO;

import java.util.List;
import java.util.Map;

public interface PdfGeneradorService {

    List<PlantillaDocumentDTO> getAllPlantillesDocuments();

    PlantillaDocumentDTO getPlantillaDocumentByCodi(String codi);

    Long savePlantillaDocument(PlantillaDocumentDTO plantillaDocumentDTO);

    String generarPdfDesdeHtml(String codigoPlantilla, String lang, Map<String, String> data);


}
