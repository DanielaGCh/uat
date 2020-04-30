/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller;


import com.smartsoft.uat.business.RecuperarContraseñaBussines;
import com.smartsoft.uat.controller.view.CorreoView;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * 
 */
@Named(value = "correoController")
@ViewScoped
public class CorreoController implements Serializable{
    @Inject
    RecuperarContraseñaBussines business;
    private CorreoView view;

    @PostConstruct
    public void init() {
        view = new CorreoView();
    }

    public CorreoView getView() {
        return view;
    }
    
    
    public void enviarCorreo(){
        business.enviarCorreo(view.getDestinatario());
    }
    
    
    public void MessageInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
}
