/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.business;

import com.smartsoft.uat.dao.PuntosDAO;
import com.smartsoft.uat.entity.Puntos;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author hp
 */
@Stateless
public class PuntosBusiness {
    
    @Inject
    private PuntosDAO dao;
    
    public Puntos guardar(Puntos entity) {
        return dao.saveOrEdit(entity, entity.getIdPuntos());
    }

    public Puntos consultar(Integer id) {
        return dao.search(id);
    }

    public void eliminar(Puntos entity, Integer idUsuario) {
        //entity.setActivo(false);
        dao.deleteLogically(entity);
    }

    public List<Puntos> obtenerListaActivos() {
        return dao.listaActivos();
    }
    
    public Puntos existe(String usuario){
        return dao.existe(usuario);
    }
}
