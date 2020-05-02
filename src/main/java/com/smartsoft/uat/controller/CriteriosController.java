/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller;

import com.smartsoft.uat.business.CriteriosBusiness;
import com.smartsoft.uat.controller.view.CriteriosView;
import com.smartsoft.uat.dao.CriteriosDAO;
import com.smartsoft.uat.entity.Criteriosevaluacion;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author andre
 */
@Named(value = "criteriosController")
@ViewScoped
public class CriteriosController implements Serializable{
    

    @Inject
    private SesionController sesion;
    @Inject
    private CriteriosBusiness business;
    @Inject
    private CriteriosDAO dao;
    

    private CriteriosView view;

    @PostConstruct
    public void init() {
        view = new CriteriosView();
        mostrarLista();
    }

    public void mostrarLista() {
        view.setEntity(null);
        view.setListaEntity(business.obtenerListaActivos());
        //view.setListaEntityDocentesNoValidados(business.obtenerListaDocentesNoValidados());
       // view.setListaEntityPadresNoValidados(business.obtenerListaPadresNoValidados());
    }


    public void nuevo() {
        view.setEntity(new Criteriosevaluacion());
        view.setListaEntity(null);
        view.getEntity().setIdCriterio(0);
        //view.getEntity().setAutorizacion(true);
        //view.getEntity().setIdRegistro(sesion.getView().getUsuario().getId());
        //view.getEntity().setFechaRegistro(new Date());
    }
    

   

    public void editar(Criteriosevaluacion entity) {
        view.setEntity(entity);
        view.setListaEntity(null);
    }

    public void eliminar(Criteriosevaluacion entity) {
        entity.setActivo(false);
        business.eliminar(entity, null);
        sesion.MessageInfo("Registro eliminado");
        mostrarLista();
    }

    public void guardar() {
        if(camposVacios()==false){
           view.getEntity().setActivo(true);
           view.getEntity().setMatriculaDocente(sesion.getView().getUsuario().getMatricula());
           business.guardar(view.getEntity());
           sesion.MessageInfo("Se registro exitosamente");
           mostrarLista();
        }else{
            sesion.MessageInfo("Introduce todos los datos");
        }
    }
   
    public boolean camposVacios() {
        if( view.getEntity().getNombre().isEmpty()||view.getEntity().getPorcentaje()==null){
            return true;
        }else{
            return false;
        }
    }
    
   
    public CriteriosView getView() {
        return view;
    }
    
}
