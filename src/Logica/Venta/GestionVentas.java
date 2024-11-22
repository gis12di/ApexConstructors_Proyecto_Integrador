package Logica.Venta;

import Logica.FactoryMethod.CreadorCrudVenta;
import Logica.Interfaz.Cruds;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;

public class GestionVentas {
    private final Cruds<Venta> crudVenta;

    public GestionVentas() {
        CreadorCrudVenta creador = new CreadorCrudVenta();
        this.crudVenta = creador.crearCrud();
    }

    public List<Venta> obtenerVentas() {
        return crudVenta.obtener("");
    }

    public boolean guardarVenta(Venta venta, JFrame frame) {
        if (venta.getIdVenta() == null || venta.getIdVenta().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El ID de la venta no puede estar vacío.");
            return false;
        }
        return crudVenta.guardar(venta);
    }

    public boolean actualizarVenta(Venta venta) {
        return crudVenta.actualizar(venta);
    }

    public boolean eliminarVenta(String idVenta) {
        return crudVenta.eliminar(idVenta);
    }
}
