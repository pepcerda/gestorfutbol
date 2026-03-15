package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.persistence.model.Tenant;
import com.jcerdar.gestorfutbol.persistence.model.Usuari;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    @Async
    public void enviarAvisBenvinguda(Usuari usuari, Tenant tenant) {
        try {
            // El segundo parámetro 'true' es vital para indicar que es un mensaje multipart (con adjuntos)
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // ... Configurar variables anteriores (Context) ...
            Context context = new Context();
            context.setVariable("nom", usuari.getNom());
            context.setVariable("tenantNom", tenant.getNom());
            context.setVariable("email", usuari.getEmail());
            context.setVariable("urlLogin", "https://teva-app.com/login");

            // Generar el HTML
            String htmlContent = templateEngine.process("benvinguda_ca", context);

            helper.setTo(usuari.getEmail());
            helper.setSubject("Benvingut a " + tenant.getNom());
            // Importante: primero establecer el texto HTML
            helper.setText(htmlContent, true);

            // --- Cargar e Incluir el Logo ---
            // Cargamos la imagen desde resources/static/images/gesclub.png
            Resource resource = resourceLoader.getResource("classpath:static/images/gesclub.png");

            // Adjuntamos la imagen con el ID 'logoImage' usado en el HTML (src="cid:logoImage")
            helper.addInline("logoImage", resource);
            // ---------------------------------

            mailSender.send(message);
        } catch (MessagingException e) {
            // Manejo de errores
            System.err.println("Error enviant email amb logo: " + e.getMessage());
        }
    }
}
