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
