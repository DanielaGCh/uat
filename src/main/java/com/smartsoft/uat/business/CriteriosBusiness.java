/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.business;

import com.smartsoft.uat.dao.CriteriosDAO;
import com.smartsoft.uat.entity.Criteriosevaluacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author andre
 */
@Stateless
public class CriteriosBusiness {
       @Inject
    private CriteriosDAO dao;
    
    
    public Criteriosevaluacion guardar(Criteriosevaluacion entity) {
        return dao.saveOrEdit(entity, entity.getIdCriterio());
    }

    public Criteriosevaluacion consultar(Integer id) {
        return dao.search(id);
    }

    public void eliminar(Criteriosevaluacion entity, Integer idJustificantes) {
        entity.setActivo(false);
        dao.deleteLogically(entity);
    }

    public List<Criteriosevaluacion> obtenerListaActivos() {
        return dao.listaActivos();
    }
    
}
