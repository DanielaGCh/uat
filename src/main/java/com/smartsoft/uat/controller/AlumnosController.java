package com.smartsoft.uat.controller;

import com.csvreader.CsvReader;
import com.smartsoft.uat.business.AlumnosBusiness;
import com.smartsoft.uat.controller.view.AlumnosView;
import com.smartsoft.uat.entity.Alumnos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author hp
 */
@Named(value = "alumnosController")
@ViewScoped
public class AlumnosController implements Serializable{
    
    @Inject
    private SesionController sesion;
    @Inject
    private AlumnosBusiness business;
    
    private AlumnosView view;
    
    @PostConstruct
    public void init() {
        view = new AlumnosView();
        mostrarLista();
    }
    
    public void mostrarLista(){
        view.setEntity(null);
        view.setListaEntity(business.obtenerListaActivos());
    }
    
    public void nuevo() {
        view.setEntity(new Alumnos());
        view.setListaEntity(null);
        view.getEntity().setId(0);
        view.getEntity().setActivo(true);
        view.getEntity().setIdRegistro(sesion.getView().getUsuario().getId());
        view.getEntity().setFechaRegistro(new Date());
    }

    public void editar(Alumnos entity) {
        view.setEntity(entity);
        view.setListaEntity(null);
    }

    public void eliminar(Alumnos entity) {
        entity.setActivo(false);
        entity.setIdElimino(sesion.getView().getUsuario().getId());
        entity.setFechaElimino(new Date());
        business.eliminar(entity, null);
        sesion.MessageInfo("Registro eliminado");
        mostrarLista();
    }

    public void guardar() {
      
        business.guardar(view.getEntity());
        sesion.MessageInfo("Registro exitoso");
        mostrarLista();
    }
    
    /*ublic void cargaralumnos(FileUploadEvent event) throws IOException {
        try {
        UploadedFile archivo = event.getFile();
        Reader br = new BufferedReader(new InputStreamReader(archivo.getInputstream()));
        CsvReader csv = new CsvReader(br);
        csv.readHeaders();
            while (csv.readRecord()) {
                Alumnos alum = new Alumnos();
                alum.setId(0);
                alum.setMatricula(csv.get("matricula"));
                alum.setNombre(csv.get("nombre"));
                alum.setApellidoPaterno(csv.get("apellido_paterno"));
                alum.setApellidoMaterno(csv.get("apellido_materno"));
                alum.setGrado(csv.get("grado"));
                alum.setProgramaEducativo(csv.get("programa_educativo"));
                //alum.setFechaRegistro(new Date());
                //alum.setIdRegistro(sesion.getView().getUsuario().getId());
                business.guardar(alum);
            }
            mostrarLista();
            sesion.MessageInfo("Carga exitosa");
        } catch (IOException e) {
            e.printStackTrace();
            sesion.MessageError("Verifique su archivo");
            mostrarLista();
        }
    }*/
    public void  cargaralumnos (FileUploadEvent event) throws IOException{
        UploadedFile archivo = event.getFile();
        
        Reader br = new BufferedReader (new InputStreamReader (archivo.getInputstream()));
        CsvReader csv = new CsvReader(br);
        csv.readHeaders();
        
        try{
            while(csv.readRecord()){
               Alumnos alum = new Alumnos();
               alum.setId(0);
               alum.setActivo(true);
               alum.setMatricula(csv.get("matricula"));
               alum.setNombre(csv.get("nombre"));
               alum.setApellidoPaterno(csv.get("apellido_paterno"));
               alum.setApellidoMaterno(csv.get("apellido_materno"));
               alum.setGrado(csv.get("grado"));
               alum.setProgramaEducativo(csv.get("programa_educativo"));
               alum.setFechaRegistro(new Date());
               alum.setIdRegistro(sesion.getView().getUsuario().getId());
               
               business.guardar(alum);
               sesion.MessageInfo("Registro exitoso");
            } 
        }catch(Exception e){
                    sesion.MessageError("Error en el registro");
            }
        finally{
            if(null!=br){
                br.close();
            }
        }
        
    }

    
    public AlumnosView getView(){
        return view;
        
        //Edicion de prueba
        //Otra Edicion jejeje
        //Otra prueba
        //Probando ando
    }
}
