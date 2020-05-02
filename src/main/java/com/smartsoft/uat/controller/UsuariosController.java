/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller;

import com.smartsoft.uat.business.UsuariosBusiness;
//import com.smartsoft.uat.business.AlumnosBusiness;
import com.smartsoft.uat.controller.view.UsuariosView;
//import com.smartsoft.uat.controller.view.AlumnosView;
import com.smartsoft.uat.entity.Usuarios;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hp
 */
@Named(value = "usuariosController")
@ViewScoped
public class UsuariosController implements Serializable{
    
    @Inject
    private SesionController sesion;
    @Inject
    private UsuariosBusiness business;
    
    private UsuariosView view;
    
    
    @PostConstruct
    public void init() {
        view = new UsuariosView();
        mostrarLista();
    }
    
    public void mostrarLista(){
        view.setEntity(null);
        view.setListaEntity(business.obtenerListaActivos());
        view.setListaEntityDocentesNoValidados(business.obtenerListaDocentesNoValidados());
        view.setListaEntityPadresNoValidados(business.obtenerListaPadresNoValidados());
    }
    
    
//    public void mostrarListaDocentesNoValidados(){
//        view.setListaEntityDocentesNoValidados(business.obtenerListaDocentesNoValidados());
//    }
//    
//    public void mostrarListaPadresNoValidados(){
//        view.setListaEntityPadresNoValidados(business.obtenerListaPadresNoValidados());
//    }
    
    
     public void nuevo() {
        view.setEntity(new Usuarios());
        view.setListaEntity(null);
        view.getEntity().setId(0);
        //view.getEntity().setAutorizacion(true);
        view.getEntity().setFechaRegistro(new Date());
    }
    public void validardocen(Usuarios docen){
        docen.setActivo(!docen.getActivo());
        business.guardar(docen);
        sesion.MessageInfo(
                docen.getActivo()? "Docente validado":"Docente invalidado"
        );
        mostrarLista();
    }
    
    public void validarpadre(Usuarios padre){
        padre.setActivo(!padre.getActivo());
        business.guardar(padre);
        sesion.MessageInfo(
                padre.getActivo()? "Padre o Tutor validado":"Padre o Tutor invalidado"
        );
        mostrarLista();
    }

    public void editar(Usuarios entity) {
        view.setEntity(entity);
        view.setListaEntity(null);
    }

    
     public void eliminar(Usuarios entity) {
        entity.setActivo(false);
        entity.setCorreo(null);
        entity.setContrasena(null);
        //entity.setAutorizacion(false);
        entity.setIdElimino(sesion.getView().getUsuario().getId());
        entity.setFechaElimino(new Date());
        business.eliminar(entity, null);
        sesion.MessageInfo("Registro eliminado");
        mostrarLista();
    }

public void guardar() {
        if(camposVacios()){
            sesion.MessageInfo("Ingresa todos los datos e intenta nuevamente");   
            mostrarLista();
        } 
    
        else if(view.getEntity().getRol().equals("Alumno")&&existeAlum()==true){
           view.getEntity().setActivo(true);
           view.getEntity().setAutorizacion(true);
           business.guardar(view.getEntity());
           MessageInfo("Se registro exitosamente");
           mostrarLista();
           
        }
        else if(view.getEntity().getRol().equals("Alumno")&&existeAlum()==false){
            sesion.MessageError("La matricula no es valida");
            mostrarLista();
        }
        else if(view.getEntity().getRol().equals("Docente")){
            view.getEntity().setActivo(Boolean.TRUE);
           business.guardar(view.getEntity());
           sesion.MessageInfo("Se registro exitosamente");
            mostrarLista();
        }
        else if(view.getEntity().getRol().equals("Padre o Tutor")){
            if(existeAlum()==true){
                view.getEntity().setActivo(true);
                view.getEntity().setAutorizacion(null);
                business.guardar(view.getEntity());
                sesion.MessageInfo("Se registro exitosamente");
                mostrarLista();
            }else{
                sesion.MessageError("La matricula no es valida");
                mostrarLista();
            }
        }   
    }
public boolean camposVacios() {
        return view.getEntity().getApellidoMaterno().isEmpty()
                ||view.getEntity().getApellidoPaterno().isEmpty()
                ||view.getEntity().getNombre().isEmpty()
                ||view.getEntity().getContrasena().isEmpty()
                ||view.getEntity().getConfirmarcontra().isEmpty()
                ||view.getEntity().getCorreo().isEmpty()
                ||view.getEntity().getMatricula()==null;
                

    }

    public boolean existeUsuario() {
        if (business.existe(view.getEntity().getCorreo()) != null) {
            return true;
        }
        return false;
    }
    
    public boolean existeAlum() {
        if (business.existeAlum(view.getEntity().getMatricula()) != null) {
            return true;
        }
        return false;
    }
    
    
    
    public UsuariosView getView() {
        return view;
    }
    
    public void MessageInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    public void MessageError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }
 
}
