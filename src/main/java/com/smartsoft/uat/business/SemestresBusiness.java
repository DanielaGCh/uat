/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.business;

import com.smartsoft.uat.dao.SemestresDAO;
import com.smartsoft.uat.entity.Semestres;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author chave
 */
@Stateless
public class SemestresBusiness {
    @Inject
    private SemestresDAO dao;
   
    
        public List<Semestres> obtenerListaActivos() {
        return dao.listaActivos();
    }
}
