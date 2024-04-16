package com.example.demo.IMAP;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.search.SubjectTerm;

public class GestorEmail {

    private Properties propiedades;
    private Session sesion;
    public static String correo;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    public static String contra;
    public String asunto =  "Mensaje enviado el: " + dateFormat.format(date);

    private void setPropiedadesServidorSMTP() {
        propiedades = System.getProperties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.starttls.enable", "true");
        sesion = Session.getInstance(propiedades, null);
    }

    private Transport conectarServidorSMTP(String direccionEmail, String password)
            throws NoSuchProviderException, MessagingException {
        Transport t = (Transport) sesion.getTransport("smtp");
        t.connect(propiedades.getProperty("mail.smtp.host"), direccionEmail, password);
        return t;
    }

    private Message crearNucleoMensaje(String emisor, String destinatario, String asunto)
            throws AddressException, MessagingException {
        Message mensaje = new MimeMessage(sesion);
        mensaje.setFrom(new InternetAddress(emisor));
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensaje.setSubject(asunto);
        return mensaje;
    }

    private Message crearMensajeTexto(String textoMensaje)
            throws MessagingException, AddressException, IOException {
        Message mensaje = crearNucleoMensaje(correo, correo, asunto);
        mensaje.setText(textoMensaje);
        return mensaje;
    }

    public void enviarMensajeTexto(String textoMensaje)
            throws AddressException, MessagingException, IOException {
        setPropiedadesServidorSMTP();
        Message mensaje = crearMensajeTexto(textoMensaje);
        Transport t = conectarServidorSMTP(correo, contra);

        t.sendMessage(mensaje, mensaje.getAllRecipients());
        t.close();
    }

}

