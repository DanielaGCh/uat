/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.business;

import com.smartsoft.uat.controller.CorreoController;
import com.smartsoft.uat.controller.SesionController;
import com.smartsoft.uat.dao.RecuperarContraseñaDAO;

import com.smartsoft.uat.entity.Recuperarcontraseña;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Smart
 */
@Stateless
public class RecuperarContraseñaBussines {

    @Inject
    private RecuperarContraseñaDAO dao;
    @Inject
    private CorreoController correo;

    private static final Logger LOG = Logger.getLogger(RecuperarContraseñaBussines.class.getName());

    public List<Recuperarcontraseña> obtenerLista() {
        return dao.buscarTodos();
    }

    public void enviarCorreo( String destinatario) {
        try {
            if(dao.verificarCorreos(destinatario)==null){
                MessageError("Introduce un correo valido");
               
            }else{
            Properties props = obtenerPropiedadesServidor();
            Authenticator auth = obtenerAutentificacion(props);

            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setDataHandler(null);
            msg.setFrom(new InternetAddress(props.getProperty("config.from.email"), props.getProperty("config.from.name")));
            msg.setSubject("Recuperar contreaseña");
            msg.setText("Tu contraseña es:  " + dao.verificarCorreos(destinatario).getContrasena());
//            msg.setSentDate(ManejadorFechas.obtenerFechaActualGTM());
            msg.setRecipients(Message.RecipientType.TO, destinatario);
//           msg = obtenerRemitentes(msg, correo.getListDireccionDestinatario());
            
              Transport.send(msg);
            LOG.info("Mensaje enviado"); 
            MessageInfo("correo enviado");
            
            }
        } catch (MessagingException | UnsupportedEncodingException ex) {
            Logger.getLogger(RecuperarContraseñaBussines.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void MessageInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    public void MessageError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    private Authenticator obtenerAutentificacion(Properties props) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("config.username"), props.getProperty("config.password"));
            }
        };
    }

    private Properties obtenerPropiedadesServidor() {
        Properties props = new Properties();
        for (Recuperarcontraseña entity : obtenerLista()) {
            props.put(entity.getParametro(), entity.getValor());
        }
        return props;
    }

    private MimeMessage obtenerRemitentes(MimeMessage msg, List<String> listaDestinatarios) {
        for (String destinatario : listaDestinatarios) {
            try {
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario, false));
            } catch (MessagingException ex) {
                Logger.getLogger(RecuperarContraseñaBussines.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return msg;
    }

    //private Multipart obtenerCuerpo(Correo correo) throws MessagingException {
      //  MimeMultipart multiParte = new MimeMultipart();
        //multiParte.addBodyPart(obtenerArchivo(correo, "txt"));
//        if (correo.getCuerpoHTML() != null) {
//            multiParte.addBodyPart(obtenerArchivo(correo, "html"));
//        }
//
//        if (correo.getCuerpoTXT() != null) {
//            multiParte.addBodyPart(obtenerArchivo(correo, "txt"));
//        }
//
//        if (correo.getListaDocumentos() != null) {
//            for (Documento documento : correo.getListaDocumentos()) {
//                multiParte.addBodyPart(adjuntarArchivo(documento));
//            }
//        }
       // return multiParte;
    //}

    /*public BodyPart adjuntarArchivo(Documento documento) throws MessagingException {
        MimeBodyPart cuerpo = new MimeBodyPart();
        cuerpo.setDataHandler(new DataHandler(documento.getDocumento()));
        cuerpo.setFileName(documento.getNombre());
        return cuerpo;
    }

    private BodyPart obtenerArchivo(Correo correo, String tipo) throws MessagingException {
        MimeBodyPart cuerpo = new MimeBodyPart();
        switch (tipo) {
            case "html":
                cuerpo.setContent(correo.getCuerpoHTML(), "text/html");
                return cuerpo;
            case "txt":
                cuerpo.setText(correo.getCuerpoTXT(), "UTF-8");
                return cuerpo;
        }
        return cuerpo;
    }
*/
}

