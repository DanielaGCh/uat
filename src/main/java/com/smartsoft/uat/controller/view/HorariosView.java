package com.smartsoft.uat.controller.view;

import com.smartsoft.uat.entity.Horarios;
import com.smartsoft.uat.entity.Periodos;
import com.smartsoft.uat.entity.Semestres;
import com.smartsoft.uat.entity.Unidadaprendizaje;

import java.util.List;

/**
 *
 * @author hp
 */
public class HorariosView {
    
    private Horarios entity;
    private List<Horarios> listaEntity;
    private Periodos entityPeriodos;
    private List<Periodos> listaEntityPeriodos;
    private Semestres entitySemestre;
    private List<Semestres> listaEntitySemestres;
    private String periodo;
    private List<Unidadaprendizaje> listaEntityUnidad;
    private Unidadaprendizaje entityUnidad;
    
    public Horarios getEntity() {
        return entity;
    }

    public void setEntity(Horarios entity) {
        this.entity = entity;
    }
    
    public List<Horarios> getListaEntity() {
        return listaEntity;
    }

    public void setListaEntity(List<Horarios> listaEntity) {
        this.listaEntity = listaEntity;
    }
     
     public Periodos getEntityPeriodos() {
     return entityPeriodos;
    }
     
    public void setEntityPeriodos(Periodos entityPeriodos) {
        this.entityPeriodos = entityPeriodos;
    }
    
     public List<Periodos> getListaEntityPeriodos() {
        return listaEntityPeriodos;
    }
     
        public void setListaEntityPeriodos(List<Periodos> listaEntityPeriodos) {
        this.listaEntityPeriodos = listaEntityPeriodos;
    }
       
    public Semestres getEntitySemestre() {
        return entitySemestre;
    }

    public void setEntitySemestre(Semestres entitySemestre) {
        this.entitySemestre = entitySemestre;
    }
        
     public List<Semestres> getListaEntitySemestres() {
        return listaEntitySemestres;
    }
     
    public void setListaEntitySemestres(List<Semestres> listaEntitySemestres) {
        this.listaEntitySemestres = listaEntitySemestres;
    }

    public String getPeriodo (){
        return periodo;
    }

    public void setPeriodo (String periodo){
        this.periodo = periodo;
    }
    
    public List<Unidadaprendizaje> getListaEntityUnidad() {
        return listaEntityUnidad;
    }
     
    public void setListaEntityUnidad(List<Unidadaprendizaje> listaEntityUnidad) {
        this.listaEntityUnidad = listaEntityUnidad;
    }

    public Unidadaprendizaje getEntityUnidad (){
        return entityUnidad;
    }

    public void setEntityUnidad (Unidadaprendizaje entityUnidad){
        this.entityUnidad = entityUnidad;
    }


}