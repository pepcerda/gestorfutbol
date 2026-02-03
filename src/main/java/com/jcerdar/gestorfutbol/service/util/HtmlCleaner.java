package com.jcerdar.gestorfutbol.service.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;

public class HtmlCleaner {

    /**
     * Limpia HTML generado por TinyMCE y lo convierte a XHTML compatible con OpenHTMLToPDF.
     *
     * @param html HTML bruto desde TinyMCE
     * @return HTML validado y autocerrado listo para PDF
     */
    public static String cleanHtmlForPdf(String html) {
        // Parseamos el HTML “loose”
        Document doc = Jsoup.parse(html);

        // Configuramos output como XHTML
        doc.outputSettings()
                .syntax(Document.OutputSettings.Syntax.xml)   // XHTML
                .escapeMode(Entities.EscapeMode.xhtml)        // Escapa caracteres especiales
                .prettyPrint(true);                            // Formato legible

        // Devuelve el HTML limpio
        return doc.html();
    }
}
