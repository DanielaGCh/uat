













/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.business;

//import com.smartsoft.uat.bo.UsuarioBO;
import com.smartsoft.uat.dao.UsuariosDAO;
import com.smartsoft.uat.dao.AlumnosDAO;
import com.smartsoft.uat.entity.Usuarios;
import com.smartsoft.uat.entity.Alumnos;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author hp
 */
@Stateless
public class UsuariosBusiness {
    
    @Inject
    private UsuariosDAO dao;
    @Inject
    private AlumnosDAO daoAlum;
    
    public Usuarios guardar(Usuarios entity) {
        return dao.saveOrEdit(entity, entity.getId());
    }

    public Usuarios consultar(Integer id) {
        return dao.search(id);
    }

    public void eliminar(Usuarios entity, Integer idUsuario) {
        entity.setActivo(false);
        dao.deleteLogically(entity);
    }

    public Usuarios verificarAccesos(String usuario, String contrasena) {
        return  dao.verificarAccesos(usuario, contrasena);
    }

    public List<Usuarios> obtenerListaActivos() {
        return dao.listaActivos();
    }
    
    public Usuarios existe(String usuario){
        return dao.existe(usuario);
    }
    
    public Alumnos existeAlum(String matricula){
        return daoAlum.existeAlum(matricula);
    }
    
     /*public Usuarios esDocente(String usuario){
        return dao.esDocente(usuario);
    }*/
    
    public List<Usuarios> obtenerListaDocentesValidados(){
        return dao.obtenerListaDocentesValidados();
    }

    public List<Usuarios> obtenerListaDocentesNoValidados(){
        return dao.obtenerListaDocentesNoValidados();
    }
    
    public List<Usuarios> obtenerListaPadresNoValidados(){
        return dao.obtenerListaPadresNoValidados();
    }
}
    
    
    

