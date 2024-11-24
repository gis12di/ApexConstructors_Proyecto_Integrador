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
        boolean guardadoExitoso = crudVenta.guardar(venta);
        if (guardadoExitoso) {
            JOptionPane.showMessageDialog(frame, "Venta guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Error al guardar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return guardadoExitoso;
    }


    public boolean actualizarVenta(Venta venta) {
        return crudVenta.actualizar(venta);
    }

    public boolean eliminarVenta(String idVenta) {
        return crudVenta.eliminar(idVenta);
    }
}
