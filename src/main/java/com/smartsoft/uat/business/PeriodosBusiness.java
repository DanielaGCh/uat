/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.business;


import com.smartsoft.uat.dao.PeriodosDAO;
import com.smartsoft.uat.entity.Periodos;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author chave
 */
@Stateless
public class PeriodosBusiness {
    @Inject
    private PeriodosDAO dao;
   
    
        public List<Periodos> obtenerListaActivos() {
        return dao.listaActivos();
    }
}
