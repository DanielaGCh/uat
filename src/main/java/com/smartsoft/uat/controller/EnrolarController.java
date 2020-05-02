/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller;

import com.smartsoft.uat.business.EnrolarBusiness;
import com.smartsoft.uat.business.HorariosBusiness;
import com.smartsoft.uat.business.PeriodosBusiness;
import com.smartsoft.uat.business.SemestresBusiness;
import com.smartsoft.uat.controller.view.EnrolarView;
import com.smartsoft.uat.entity.Enrolar;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hp
 */
@Named(value = "enrolarController")
@ViewScoped
public class EnrolarController implements Serializable{
    
    @Inject
    private SesionController sesion;
    @Inject
    private EnrolarBusiness business;
    @Inject
    private HorariosBusiness businesshorarios;
    @Inject
    private PeriodosBusiness periodosBusiness;
    @Inject
    private SemestresBusiness semestresBusiness;

    
    private EnrolarView view;
    
    
    @PostConstruct
    public void init() {
        view = new EnrolarView();
        obtenerListaPeriodos();
        obtenerListaSemestres();
    }
    
    public void obtenerListaPeriodos(){
        view.setListaPeriodos(periodosBusiness.obtenerListaActivos());
    }
    
    public void obtenerListaSemestres(){
        view.setListaSemestres(semestresBusiness.obtenerListaActivos());
    }
    
    public void obtenerListaUniApren(){
        view.setListaHorarios(businesshorarios.obtenerListaUniApren(view.getPeriodo().getNombreperiodo(), view.getSemestre().getNombresem()));
    }
    
    public void mostrarLista(){
        view.setListaEntity(business.obtenerListaActivos(sesion.getView().getUsuario().getMatricula()));
    }
    
    public void nuevo(String folio) {
        view.setEntity(new Enrolar());
        view.setListaEntity(null);
        view.getEntity().setFolioHorario(folio);
        view.getEntity().setMatriculaUsu(sesion.getView().getUsuario().getMatricula());
        view.getEntity().setId(0);
        view.getEntity().setActivo(true);
        view.getEntity().setAutorizacion(true);
        view.getEntity().setIdRegistro(sesion.getView().getUsuario().getId());
        view.getEntity().setFechaRegistro(new Date());
        mostrarLista();
        guardar();
    }

    public void validardocen(Enrolar entity){
        entity.setAutorizacion(!entity.getAutorizacion());
        business.guardar(entity);
        sesion.MessageInfo(
                entity.getAutorizacion()? "Enrolado a la unidad de Aprendizaje":"Alumno Desenrolado"
        );
    }
    
    public void editar(Enrolar entity) {
        view.setEntity(entity);
        view.setListaEntity(null);
    }

    public void guardar() {
         
        business.guardar(view.getEntity());
        sesion.MessageInfo("Enrolado a la unidad de aprendizaje");
    }
   

    public EnrolarView getView() {
        return view;
    }
}
