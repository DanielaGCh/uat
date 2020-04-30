/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.business;

import com.smartsoft.uat.dao.AreaDAO;
import com.smartsoft.uat.entity.Area;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author hp
 */
@Stateless
public class AreaBusiness {
    
    @Inject
    private AreaDAO dao;
    
    public Area guardar(Area entity) {
        return dao.saveOrEdit(entity, entity.getIdArea());
    }

    public Area consultar(Integer id) {
        return dao.search(id);
    }

    public void eliminar(Area entity, Integer idUsuario) {
        //entity.setActivo(false);
        dao.deleteLogically(entity);
    }

    public List<Area> obtenerListaArea() {
        return dao.listaArea();
    }
    
}
