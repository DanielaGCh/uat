/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller;

import com.smartsoft.uat.business.EnrolarBusiness;
import com.smartsoft.uat.business.HorariosBusiness;
import com.smartsoft.uat.business.PeriodosBusiness;
//import com.smartsoft.uat.business.SemestresBusiness;
import com.smartsoft.uat.business.UnidadaprendizajeBusiness;
import com.smartsoft.uat.controller.view.DesenrolarView;
import com.smartsoft.uat.entity.Enrolar;
import com.smartsoft.uat.entity.Horarios;
//import com.smartsoft.uat.controller.view.EnrolarView;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author chave
 */
@Named(value = "desenrolarController")
@ViewScoped
public class DesenrolarController implements Serializable{
    
    @Inject
    private SesionController sesion;
    @Inject
    private EnrolarBusiness business;
    @Inject
    private PeriodosBusiness periodosBusiness;
    @Inject
    private HorariosBusiness businesshorarios;
    @Inject
    private UnidadaprendizajeBusiness unidadBusiness; 
    
    private DesenrolarView view;
     
    @PostConstruct
    public void init() {
        view = new DesenrolarView();
        obtenerListaPeriodos();
        obtenerListaUnidades();
        //obtenerListaDeEnrolados();
    }
    
     public void obtenerListaPeriodos(){
        view.setListaPeriodos(periodosBusiness.obtenerListaActivos());
    }
     
    public void obtenerListaUnidades(){
        view.setListaUnidad(unidadBusiness.obtenerUnidadesPorDoc(sesion.getView().getUsuario().getMatricula()));
    }
       
   public void obtenerFolioMateria(){
       view.setHorario(new Horarios());
        view.setHorario(businesshorarios.obtenerFolioMateria(view.getPeriodo().getNombreperiodo(),view.getUnidad().getDescripcion(), view.getGrupo()));
        String folio =view.getHorario().getFolio();
        view.setListaEntity(business.obtenerListaDeEnrolados(folio));
    }
   
    public void desenrolarAlumno(Enrolar entity){
    entity.setAutorizacion(false);
    sesion.MessageInfo("Alumno desenrolado");
    }
   
       public DesenrolarView getView() {
        return view;
    }
}
