/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller.view;

import com.smartsoft.uat.entity.Criteriosevaluacion;
import java.util.List;

/**
 *
 * @author andre
 */
public class CriteriosView {
       private Criteriosevaluacion entity;
    private List<Criteriosevaluacion> listaEntity;

    public Criteriosevaluacion getEntity() {
        return entity;
    }

    public void setEntity(Criteriosevaluacion entity) {
        this.entity = entity;
    }

    public List<Criteriosevaluacion> getListaEntity() {
        return listaEntity;
    }

    public void setListaEntity(List<Criteriosevaluacion> listaEntity) {
        this.listaEntity = listaEntity;
    }
    
}
