package com.jcerdar.gestorfutbol.service.util;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.ILeafElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.jcerdar.gestorfutbol.service.model.PatrocinadorDTO;

public class PdfUtil {

    public static String generatePdf(PatrocinadorDTO data, String titolCampanya) {
        try {
            // Obtener el templatePath desde resources
            ClassPathResource resource = new ClassPathResource("rebut.jasper");

            // Compilar el archivo .jrxml
            InputStream inputStream = resource.getInputStream();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

            // Crear la fuente de datos con un único DTO
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(data));

            // Parámetros adicionales (si los hay)
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("campanyaTitol", titolCampanya);

            // Llenar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Exportar el informe a PDF en un ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);

            // Convertir el ByteArrayOutputStream a Base64
            byte[] pdfBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(pdfBytes);
        } catch (JRException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String fillTemplate(PatrocinadorDTO patrocinador) {
        try {
            ClassPathResource resource = new ClassPathResource("plantilla.pdf");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfDocument pdfDoc = new PdfDocument(new PdfReader(resource.getInputStream()), new PdfWriter(baos));
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

            Map<String, String> fieldValues = new HashMap<>();
            fieldValues.put("nom", patrocinador.getNom());
            fieldValues.put("nif", patrocinador.getCif());
            fieldValues.put("donatiu", String.valueOf(patrocinador.getDonacio()));
            fieldValues.put("data", patrocinador.getDataDonacio().toString());

            for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
                PdfFormField field = form.getField(entry.getKey());
                if (field != null) {
                    field.setValue(entry.getValue());
                }
            }

            form.flattenFields();
            pdfDoc.close();

            byte[] pdfBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(pdfBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generatePdfBase64(PatrocinadorDTO patrocinador) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Cargar logo desde resources
            ClassPathResource resource = new ClassPathResource("logo.jpg");
            InputStream inputStream = resource.getInputStream();
            ImageData imageData = ImageDataFactory.create(inputStream.readAllBytes());
            Image logo = new Image(imageData);
            logo.setFixedPosition(50, 750); // Ajusta la posición según sea necesario
            document.add(logo);

            document.add(new Paragraph("Athletic Club de Montuïri"));
            document.add(new Paragraph("Rebut de donatiu"));

            // Crear campos de formulario
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
            PdfFormField dateField = PdfTextFormField.createText(pdfDoc, new Rectangle(100, 750, 200, 15), "date", new SimpleDateFormat("yyyy-MM-dd").format(patrocinador.getDataDonacio()));
            PdfFormField nameField = PdfTextFormField.createText(pdfDoc, new Rectangle(100, 730, 200, 15), "name", patrocinador.getNom());
            PdfFormField dniField = PdfTextFormField.createText(pdfDoc, new Rectangle(100, 710, 200, 15), "dni", patrocinador.getCif());
            PdfFormField amountField = PdfTextFormField.createText(pdfDoc, new Rectangle(100, 690, 200, 15), "amount", patrocinador.getDonacio().toString());
            PdfFormField seasonField = PdfTextFormField.createText(pdfDoc, new Rectangle(100, 670, 200, 15), "season", patrocinador.getCampanya().toString());

            // Añadir campos al formulario
            form.addField(dateField);
            form.addField(nameField);
            form.addField(dniField);
            form.addField(amountField);
            form.addField(seasonField);

            // Agregar texto al documento
            document.add(new Paragraph("Montuïri, a ").add(dateField.getValueAsString()));
            document.add(new Paragraph("L’Athletic Club de Montuïri confirma que va rebre de ").add(nameField.getValueAsString()));
            document.add(new Paragraph(", amb DNI/NIF ").add(dniField.getValueAsString()));
            document.add(new Paragraph(", la quantitat de ").add(amountField.getValueAsString()));
            document.add(new Paragraph("€, en concepte de donatiu per la temporada ").add(seasonField.getValueAsString()));
            document.add(new Paragraph("El club i president:"));
            document.add(new Paragraph("Donador:"));
            document.add(new Paragraph("Athletic Montuïri"));
            document.add(new Paragraph("Nom i Firma"));


            form.flattenFields();

            document.close();

            // Convertir a Base64
            byte[] pdfBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(pdfBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
