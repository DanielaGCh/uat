package com.smartsoft.uat.controller;

import com.smartsoft.uat.business.UsuariosBusiness;
import com.smartsoft.uat.controller.view.SesionView;
import com.smartsoft.uat.entity.Usuarios;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "sesionController")
@SessionScoped
public class SesionController implements Serializable {

    @Inject
    private UsuariosBusiness usuariosBusiness;

    private SesionView view;

    @PostConstruct
    public void init() {
        view = new SesionView();
        view.setUsuario(new Usuarios());
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        view = null;
        return "/login.xhtml?faces-redirect=true";
    }

   
     public String iniciarSesion() {
        view.setUsuario(usuariosBusiness.verificarAccesos(view.getUsuario().getCorreo(), view.getUsuario().getContrasena()));
        if (view.getUsuario() != null) {
            if(view.getUsuario().getAutorizacion()==null&&view.getUsuario().getFechaElimino()==null){
                if(view.getUsuario().getRol().equals("Docente")){
                    view.setUsuario(new Usuarios());
                    MessageError("Espere la validacion del coordinador");
                }else{
                     view.setUsuario(new Usuarios());
                MessageError("Espere la validacion del alumno");
                }
               
            }else{
                return "/webapp/catalogos/inicioUsuario.xhtml?faces-redirect=true";
            }
        }else {
            view.setUsuario(new Usuarios());
            MessageError("Verifique sus datos");
        }
        return "";
    }
    
    
    public String registrarse (){
        return "/webapp/catalogos/registrarse.xhtml?faces-redirect=true";
    } 

    public SesionView getView() {
        return view;
    }

    public void MessageInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    public void MessageError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

}
