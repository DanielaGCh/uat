/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.business;

import com.smartsoft.uat.dao.EventosDAO;
import com.smartsoft.uat.entity.Eventos;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author hp
 */
@Stateless
public class EventosBusiness {
    
    @Inject
    private EventosDAO dao;
    
    public Eventos guardar(Eventos entity) {
        return dao.saveOrEdit(entity, entity.getId());
    }

    public Eventos consultar(Integer id) {
        return dao.search(id);
    }

    public void eliminar(Eventos entity, Integer idEvento) {
        entity.setActivo(false);
        dao.deleteLogically(entity);
    }


    public List<Eventos> obtenerListaActivos() {
        return dao.listaActivos();
    }
    
    public Eventos existe(String evento){
        return dao.existe(evento);
    }
}