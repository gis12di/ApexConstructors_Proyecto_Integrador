
import Logica.Interfaz.Cruds;
import Persistencia.CrudCliente;
import Persistencia.CrudInmuebles;
import Persistencia.CrudPago;
import Persistencia.CrudTorres;
import Persistencia.CrudUsuarios;
import Persistencia.CrudVenta;

public class CrudFactory {

    public static Cruds getCrud(CrudType type) {
        switch (type) {
            case CLIENTE:
                return new CrudCliente();
            case INMUEBLES:
                return new CrudInmuebles();
            case PAGO:
                return new CrudPago();
            case PROYECTOS:
                return new CrudProyectos();
            case TORRES:
                return new CrudTorres();
            case USUARIOS:
                return new CrudUsuarios();
            case VENTA:
                return new CrudVenta();
            default:
                throw new IllegalArgumentException("Unknown CrudType: " + type);
        }
    }
}
