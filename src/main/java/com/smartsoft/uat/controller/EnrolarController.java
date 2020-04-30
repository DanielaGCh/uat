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
import com.smartsoft.uat.business.UnidadaprendizajeBusiness;
import com.smartsoft.uat.controller.view.EnrolarView;
import com.smartsoft.uat.entity.Enrolar;
//import com.smartsoft.uat.entity.Horarios;
//import com.smartsoft.uat.entity.Semestres;
import java.io.Serializable;
//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;
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
    @Inject
    private UnidadaprendizajeBusiness unidadBusiness;
    
    private EnrolarView view;
    
    
    @PostConstruct
    public void init() {
        view = new EnrolarView();
        obtenerListaPeriodos();
        obtenerListaSemestres();
        obtenerListaUnidades();
        obtenerListaDeEnrolados();
    }
    
    public void obtenerListaPeriodos(){
        view.setListaPeriodos(periodosBusiness.obtenerListaActivos());
    }
    
    public void obtenerListaSemestres(){
        view.setListaSemestres(semestresBusiness.obtenerListaActivos());
    }
    
    
    public void obtenerListaUnidades(){
        view.setListaUnidad(unidadBusiness.obtenerUnidadesPorDoc(sesion.getView().getUsuario().getMatricula()));
    }
    
    public void obtenerListaUniApren(){
        view.setListaHorarios(businesshorarios.obtenerListaUniApren(view.getPeriodo().getNombreperiodo(), view.getSemestre().getNombresem()));
    }
    
    public void obtenerListaDeEnrolados(){
        view.setListaEntity(business.obtenerListaDeEnrolados(view.getFolio()));
    }
       
   public void obtenerFolioMateria(){
        view.setHorario(businesshorarios.obtenerFolioMateria(view.getHorario().getPeriodo(),view.getHorario().getUnidadAprendizaje(), view.getHorario().getGrupo()));
        view.setFolio(view.getHorario().getFolio());
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
        view.getEntity().setIdRegistro(sesion.getView().getUsuario().getId());
        view.getEntity().setFechaRegistro(new Date());
        guardar();
    }

    public void editar(Enrolar entity) {
        view.setEntity(entity);
        view.setListaEntity(null);
    }

//    public void eliminar(Enrolar entity) {
//        entity.setActivo(false);
//        entity.setIdElimino(sesion.getView().getUsuario().getId());
//        entity.setFechaElimino(new Date());
//        business.eliminar(entity, null);
//        sesion.MessageInfo("Registro eliminado");
//        mostrarLista();
//    }
//
    public void guardar() {
         
        business.guardar(view.getEntity());
        sesion.MessageInfo("Registro exitoso");
    }
   
//    public void Enrolarse(Enrolar unidadA){
//        unidadA.setActivo(!unidadA.getActivo());
//        business.guardar(unidadA);
//        sesion.MessageInfo(
//                unidadA.getActivo()? "se enrolo en la Unidad de Aprendizaje":"Se desenrolo en la Unidad de Aprendizaje"
//        );
//        guardar();
//        mostrarLista();
//    }
//    
//    public ArrayList<String> listaFolios(){
//        List<Horarios>  horarios = businesshorarios.obtenerListafolios(sesion.getView().getUsuario().getMatricula());
//        ArrayList<String> folios  = new  ArrayList();
//        
//        
//        for (int i=0; i< horarios.size(); i++){
//            String folio;
//           Horarios horarioFolio = new Horarios();
//            horarioFolio= horarios.get(i);
//            folio = horarioFolio.getFolio();
//            folios.add(folio);
//           
//        }
//            
//        return folios;
//   } 

    public EnrolarView getView() {
        return view;
    }
}
