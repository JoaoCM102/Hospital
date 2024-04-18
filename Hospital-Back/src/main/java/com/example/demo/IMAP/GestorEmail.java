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

import com.example.demo.Entidades.Cita;
import com.example.demo.Entidades.User;

public class GestorEmail {

    private Properties propiedades;
    private Session sesion;
    public static String correoEmisor = "doncartcompany@gmail.com";
    public static String correoReceptor ;
    public static String contra = "mirqqasvinnerayo" ;
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    static Date date = new Date();
    public static String asunto =  "Mensaje enviado el: " + dateFormat.format(date);

   

    public void setCorreoReceptor(String correoReceptor) {
        GestorEmail.correoReceptor = correoReceptor;
    }


    private void setPropiedadesServidorSMTP() {
        propiedades = System.getProperties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.starttls.enable", "true");
        sesion = Session.getInstance(propiedades, null);
    }

    private Transport conectarServidorSMTP() throws NoSuchProviderException, MessagingException {
        Transport t = (Transport) sesion.getTransport("smtp");
        t.connect(propiedades.getProperty("mail.smtp.host"), GestorEmail.correoEmisor, GestorEmail.contra);
        return t;
    }

    private Message crearNucleoMensaje(String asunto)
            throws AddressException, MessagingException {
        Message mensaje = new MimeMessage(sesion);
        mensaje.setFrom(new InternetAddress(GestorEmail.correoEmisor));
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(GestorEmail.correoReceptor));
        mensaje.setSubject(asunto);
        return mensaje;
    }

    private Message crearMensajeTexto(String textoMensaje)
            throws MessagingException, AddressException, IOException {
        Message mensaje = crearNucleoMensaje(asunto);
        mensaje.setText(textoMensaje);
        return mensaje;
    }

    public void enviarMensajeTexto(String textoMensaje)
            throws AddressException, MessagingException, IOException {
        setPropiedadesServidorSMTP();
        Message mensaje = crearMensajeTexto(textoMensaje);
        Transport t = conectarServidorSMTP();

        t.sendMessage(mensaje, mensaje.getAllRecipients());
        t.close();
    }

    public String MensajeCita(Cita cita) {
        StringBuilder sb = new StringBuilder();
        sb.append("La cita para el dia: " + cita.getHorario().getDia() + " a las: " + cita.getHorario().getHoraInicio() + 
        "\n Con motivos de: " + cita.getMotivos() );
         return sb.toString();
        
    }

    public String MensajeContactanos(String texto) {
        StringBuilder sb = new StringBuilder();
        sb.append("El usuario ha dejado un comentario: " + texto);
        return sb.toString();
    }

    public String MensajeValidacion(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("Te has registrado , pon este codigo de validacion: " + user.getValidacion().getCodigoValidacion());
        return sb.toString();
    }

}


