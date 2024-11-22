/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.FactoryMethod;

import Persistencia.CrudProyectos;
import Logica.Interfaz.Cruds;
import Logica.Proyecto.Proyecto;

/**
 *
 * @author giset
 */
public class CreadorCrudProyectos extends CreadorCrud<Proyecto> {

    @Override
    public Cruds<Proyecto> crearCrud() {
        return new CrudProyectos();
    }
    
}
