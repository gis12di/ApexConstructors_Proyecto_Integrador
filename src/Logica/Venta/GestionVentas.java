import Logica.Venta.Venta;
import Persistencia.CrudVenta;
import java.util.List;

public class GestionVentas {
    private CrudVenta crudVenta;

    public GestionVentas() {
        crudVenta = new CrudVenta();
    }

    public List<Venta> obtenerVentas() {
        return crudVenta.obtenerVentas();
    }

    public boolean registrarVenta(Venta venta) {
        return crudVenta.guardarVenta(venta);
    }

    public boolean eliminarVenta(String idVenta) {
        return crudVenta.eliminarVenta(idVenta);
    }
}
