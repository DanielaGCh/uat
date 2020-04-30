/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.controller;

import com.smartsoft.uat.business.AreaBusiness;
import com.smartsoft.uat.business.PuntosBusiness;
import com.smartsoft.uat.controller.view.AreaView;
import com.smartsoft.uat.controller.view.PuntosView;
import com.smartsoft.uat.entity.Area;
import com.smartsoft.uat.entity.Puntos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

/**
 *
 * @author hp
 */
@Named(value = "puntosController")
@ViewScoped
public class PuntosController implements Serializable{
    
    @Inject
    private SesionController sesion;
    @Inject
    private AreaBusiness business;
    @Inject
    private PuntosBusiness puntosBusiness;
    private AreaView view;
    
    @PostConstruct
    public void init() {
        
        view = new AreaView();
        view.setListaLatitudes(new ArrayList<LatLng>());
        view.setMapa(new DefaultMapModel());
        
        mostrarLista();
    }
    
    public void mostrarLista(){
        view.setEntity(null);
        view.setListaEntity(business.obtenerListaArea());
    }
    
    public void nuevo() {
        view.setEntity(new Area());
        view.setListaEntity(null);
        view.getEntity().setIdArea(0);
        //view.getEntity().setActivo(true);
        //view.getEntity().setIdRegistro(sesion.getView().getUsuario().getId());
        //view.getEntity().setFechaRegistro(new Date());
    }

    public void editar(Area entity) {
        view.setEntity(entity);
        view.setListaEntity(null);
        //view.setListaLatitudes(listaLatitudes);//construir objetos traer puntos de cada una de la entidad convertir a objetos latlng 
    }

    public void eliminar(Area entity) {
        //entity.setActivo(false);
        //entity.setIdElimino(sesion.getView().getUsuario().getId());
        //entity.setFechaElimino(new Date());
        business.eliminar(entity, null);
        sesion.MessageInfo("Registro eliminado");
        mostrarLista();
    }

    public void guardar() {
        Area area = business.guardar(view.getEntity());
        view.getListaLatitudes().forEach(latitud->{
            Puntos puntos = new Puntos();
            puntos.setIdPuntos(0);
            puntos.setIdArea(area.getIdArea());
            puntos.setLatitud(latitud.getLat()+"");
            puntos.setLongitud(latitud.getLng()+"");
            puntosBusiness.guardar(puntos);
                                
         });
        sesion.MessageInfo("Registro exitoso");
        mostrarLista();
    }

    /*public boolean existeUsuario() {
        if (business.existe(view.getEntity().getCorreo()) != null) {
            return true;
        }
        return false;
    }*/
    public void onPointSelect(PointSelectEvent event) {System.err.println("Hola"+event.getLatLng());
        //  view.getMapa().getMarkers().clear();
        view.getListaLatitudes().add(event.getLatLng());
        view.getMapa().addOverlay(new Marker(event.getLatLng(), "Salida, Patio: " ));//getConfiguracionPoligonoVo().getPoligonoDTO().getNombre()));
         Polygon polygon = new Polygon();
         view.getListaLatitudes().forEach(latitud->{
             polygon.getPaths().add(latitud);
         });
         
    
  
        polygon.setStrokeColor("#FF9900");
        polygon.setFillColor("#FF9900");
        polygon.setStrokeOpacity(0.7);
        polygon.setFillOpacity(0.7);
          
        view.getMapa().addOverlay(polygon);
    }
    public AreaView getView() {
        return view;
    }
}
