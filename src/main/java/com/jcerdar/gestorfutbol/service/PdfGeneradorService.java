package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.service.model.PlantillaDocumentDTO;

import java.util.List;
import java.util.Map;

public interface PdfGeneradorService {

    List<PlantillaDocumentDTO> getAllPlantillesDocuments(Filtre filtre);

    PlantillaDocumentDTO getPlantillaDocumentByCodi(Filtre filtre);

    Long savePlantillaDocument(PlantillaDocumentDTO plantillaDocumentDTO);

    String generarPdfDesdeHtml(String codigoPlantilla, Long tenantId, String lang, Map<String, String> data);


}
