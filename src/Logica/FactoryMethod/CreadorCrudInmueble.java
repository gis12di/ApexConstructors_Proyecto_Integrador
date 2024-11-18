/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import Logica.FactoryMethod.CreadorCrud;
import Logica.Inmuebles.Inmueble;
import Logica.Interfaz.Cruds;

/**
 *
 * @author giset
 */
public class CreadorCrudInmueble extends CreadorCrud<Inmueble> {

    @Override
    public Cruds<Inmueble> crearCrud() {
        return new CrudInmuebles();
    }
}
