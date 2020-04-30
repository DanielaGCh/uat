/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller;

import com.smartsoft.uat.business.EventosBusiness;
import com.smartsoft.uat.controller.view.EventosView;
import com.smartsoft.uat.entity.Eventos;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hp
 */
@Named(value = "eventosController")
@ViewScoped
public class EventosController implements Serializable{
    
    @Inject
    private SesionController sesion;
    @Inject
    private EventosBusiness business;
    
    private EventosView view;
    
    @PostConstruct
    public void init() {
        view = new EventosView();
        mostrarLista();
        nuevo();
    }
    
    public void mostrarLista(){
        view.setListaEntity(business.obtenerListaActivos());
    }
    
    public void nuevo() {
        view.setEntity(new Eventos());
        view.getEntity().setId(0);
        view.getEntity().setActivo(true);
    }
    

    public void editar(Eventos entity) {
        view.setEntity(entity);
        view.setListaEntity(null);
    }

    public void eliminar(Eventos entity) {
        entity.setActivo(false);
        business.eliminar(entity, null);
        sesion.MessageInfo("Registro eliminado");
        mostrarLista();
    }

    public void guardar() {

        if(view.getEntity() == null){
                sesion.MessageError("Introduce los datos correctamente");
                 return;
            }
            
        business.guardar(view.getEntity());
        sesion.MessageInfo("Registro exitoso");
        //mostrarLista();
    }

    public boolean existeEvento() {
        if (business.existe(view.getEntity().getAsunto()) != null) {
            return true;
        }
        return false;
    }
    
    public EventosView getView() {
        return view;
    }
    
}
