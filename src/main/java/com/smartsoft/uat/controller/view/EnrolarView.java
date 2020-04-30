/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller.view;

import com.smartsoft.uat.entity.Enrolar;
import com.smartsoft.uat.entity.Horarios;
import com.smartsoft.uat.entity.Periodos;
import com.smartsoft.uat.entity.Semestres;
import com.smartsoft.uat.entity.Unidadaprendizaje;
//import com.smartsoft.uat.entity.Horarios;
//import java.util.ArrayList;
import java.util.List;
//import javax.faces.model.SelectItem;

/**
 *
 * @author hp
 */
public class EnrolarView {
    
    private Enrolar entity;
    private List<Enrolar> listaEntity;
    
    private Periodos periodo;
    private List<Periodos> listaPeriodos;
    
    private Semestres semestre;
    private List<Semestres> listaSemestres;
    
    private Unidadaprendizaje unidad;
    private List<Unidadaprendizaje> listaUnidad;
    
    private Horarios horario;
    private List<Horarios> listaHorarios;
    
    private String folio;
    //private List<SelectItem> listafolios;

    public Enrolar getEntity() {
        return entity;
    }

    public void setEntity(Enrolar entity) {
        this.entity = entity;
    }

    public List<Enrolar> getListaEntity() {
        return listaEntity;
    }

    public void setListaEntity(List<Enrolar> listaEntityfolios) {
        this.listaEntity = listaEntityfolios;
    }
    
    public Periodos getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodos  periodo ) {
        this.periodo  = periodo ;
    }

    public List<Periodos> getListaPeriodos () {
        return listaPeriodos ;
    }

    public void setListaPeriodos (List<Periodos > listaPeriodos ) {
        this.listaPeriodos  = listaPeriodos ;
    }
    
    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario ) {
        this.horario  = horario ;
    }
    
    public List<Horarios> getListaHorarios () {
        return listaHorarios ;
    }

    public void setListaHorarios (List<Horarios> listaHorarios ) {
        this.listaHorarios  = listaHorarios ;
    }
    
    public Semestres getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestres semestre) {
        this.semestre = semestre;
    }
        
     public List<Semestres> getListaSemestres() {
        return listaSemestres;
    }
     
    public void setListaSemestres(List<Semestres> listaSemestres) {
        this.listaSemestres = listaSemestres;
    }

   public Unidadaprendizaje getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidadaprendizaje unidad) {
        this.unidad = unidad;
    }
        
     public List<Unidadaprendizaje> getListaUnidad() {
        return listaUnidad;
    }
     
    public void setListaUnidad(List<Unidadaprendizaje> listaUnidad) {
        this.listaUnidad = listaUnidad;
    }
    
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
    
//    public void setListafolios (List<Horarios> listaEntityfolios) {
//        this.listaEntityfolios = listaEntityfolios;
//    }
    
}
