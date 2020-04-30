/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller.view;

import com.smartsoft.uat.entity.Eventos;
import java.util.List;

/**
 *
 * @author hp
 */
public class EventosView {
    
    private Eventos entity;
    private List<Eventos> listaEntity;
    private Eventos evento;


    public Eventos getEntity() {
        return entity;
    }

    public void setEntity(Eventos entity) {
        this.entity = entity;
    }

    public List<Eventos> getListaEntity() {
        return listaEntity;
    }

    public void setListaEntity(List<Eventos> listaEntity) {
        this.listaEntity = listaEntity;
    }
     public Eventos getEvento() {
        return evento;
    }

    public void setEvento(Eventos usuario) {
        this.evento = usuario;
    }
    
}
