/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.business;

import com.smartsoft.uat.dao.JustificantesDAO;
import com.smartsoft.uat.dao.UsuariosDAO;
import com.smartsoft.uat.entity.Justificantes;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author hp
 */
@Stateless
public class JustificantesBusiness {
    
    @Inject
    private JustificantesDAO dao;
    @Inject
    private UsuariosDAO daousu;
    
    public Justificantes guardar(Justificantes entity) {
        return dao.saveOrEdit(entity, entity.getId());
    }

    public Justificantes consultar(Integer id) {
        return dao.search(id);
    }

    public void eliminar(Justificantes entity, Integer idJustificantes) {
        entity.setActivo(false);
        dao.deleteLogically(entity);
    }

    public List<Justificantes> obtenerListaActivos() {
        return dao.listaActivos();
    }
    
    
}
