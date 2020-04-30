/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller.view;

import com.smartsoft.uat.entity.Periodos;
import java.util.List;

/**
 *
 * @author chave
 */
public class PeriodosView {
    private Periodos entity;
    private List<Periodos> listaEntity;
    private List<String> nomPeriodo;
    
        public Periodos getEntity() {
        return entity;
    }

    public void setEntity(Periodos entity) {
        this.entity = entity;
    }

    public List<Periodos> getListaEntity() {
        return listaEntity;
    }

    public void setListaEntity(List<Periodos> listaEntity) {
        this.listaEntity = listaEntity;
    }
    
//        public  List<String> getNomPeriodo() {
//        return nomPeriodo;
//    }
//
//    public void setNomperiodo( List<String> nomPeriodo) {
//        this.nomPeriodo = nomPeriodo;
//    }
}
