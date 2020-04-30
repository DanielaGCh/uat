/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller.view;

import com.smartsoft.uat.entity.Area;

import java.util.List;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;

/**
 *
 * @author hp
 */
public class AreaView {
    
    private Area entity;
    private List<Area> listaEntity;
    private List<LatLng> listaLatitudes;
    

    public List<LatLng> getListaLatitudes() {
        return listaLatitudes;
    }

    public void setListaLatitudes(List<LatLng> listaLatitudes) {
        this.listaLatitudes = listaLatitudes;
    }

    public Area getEntity() {
        return entity;
    }

    public void setEntity(Area entity) {
        this.entity = entity;
    }

    public List<Area> getListaEntity() {
        return listaEntity;
    }

    public void setListaEntity(List<Area> listaEntity) {
        this.listaEntity = listaEntity;
    }
    
    
    private MapModel mapa;

    public MapModel getMapa() {
        return mapa;
    }

    public void setMapa(MapModel mapa) {
        this.mapa = mapa;
    }
    
}
