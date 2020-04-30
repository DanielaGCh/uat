/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.business;

import com.smartsoft.uat.dao.UnidadaprendizajeDAO;
import com.smartsoft.uat.entity.Unidadaprendizaje;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author chave
 */
@Stateless
public class UnidadaprendizajeBusiness {
    @Inject
    private UnidadaprendizajeDAO dao;
   
    
        public List<Unidadaprendizaje> obtenerListaActivos() {
        return dao.listaActivos();
    }
        
    public List<Unidadaprendizaje> obtenerUnidadesPorDoc(String matricula) {
        return dao.obtenerUnidadesPorDoc(matricula);
    }
}
