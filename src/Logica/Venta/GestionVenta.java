package Logica.Venta;

import Persistencia.CrudVenta;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;

public class GestionVenta {
    private final CrudVenta crudVenta;

    public GestionVenta() {
        this.crudVenta = new CrudVenta();
    }

    public List<Venta> obtenerVentas(String criterio) {
        return crudVenta.obtener(criterio);
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
