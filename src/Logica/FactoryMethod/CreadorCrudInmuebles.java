/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.FactoryMethod;


import Logica.Inmuebles.Inmueble;
import Logica.Interfaz.Cruds;
import Persistencia.CrudInmuebles;


/**
 *
 * @author giset
 */
public class CreadorCrudInmuebles extends CreadorCrud<Inmueble>{

    @Override
    public Cruds<Inmueble> crearCrud() {
        return new CrudInmuebles();
    }
    
}
