/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.FactoryMethod;

import Logica.Interfaz.Cruds;
import Logica.Torre.Torre;
import Persistencia.CrudTorres;

/**
 *
 * @author giset
 */
public class CreadorCrudTorres extends CreadorCrud<Torre> {

    @Override
    public Cruds<Torre> crearCrud() {
        return new CrudTorres();
    }
    
}
