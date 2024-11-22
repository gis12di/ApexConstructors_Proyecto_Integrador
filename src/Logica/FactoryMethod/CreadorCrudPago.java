/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.FactoryMethod;


import Logica.Interfaz.Cruds;
import Logica.Pago.Pago;
import Persistencia.CrudPago;

/**
 *
 * @author giset
 */
public class CreadorCrudPago extends CreadorCrud<Pago> {

    @Override
    public Cruds<Pago> crearCrud() {
        return new CrudPago();
    }
    
}
