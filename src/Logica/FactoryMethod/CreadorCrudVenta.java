/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.FactoryMethod;

import Logica.Interfaz.Cruds;
import Logica.Venta.Venta;
import Persistencia.CrudVenta;

/**
 *
 * @author giset
 */
public class CreadorCrudVenta extends CreadorCrud<Venta> {

    @Override
    public Cruds<Venta> crearCrud() {
       return new CrudVenta();
    }
    
}
