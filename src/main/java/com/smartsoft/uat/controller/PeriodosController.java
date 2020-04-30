/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller;

import java.io.Serializable;
import com.smartsoft.uat.business.PeriodosBusiness;
import com.smartsoft.uat.controller.view.PeriodosView;
import com.smartsoft.uat.entity.Periodos;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author chave
 */
@Named(value = "PeriodosController")
@ViewScoped
public class PeriodosController implements Serializable{
//    @Inject
//    private SesionController sesion;
    @Inject
    private PeriodosBusiness business;
    
    private PeriodosView view;
    
    @PostConstruct
    public void init() {
        view = new PeriodosView();
        mostrarLista();
    }
    
    public void mostrarLista(){
        //view.setEntity(null);
        view.setListaEntity(business.obtenerListaActivos());
    }
    
    public PeriodosView getView() {
        return view;
    }
}
