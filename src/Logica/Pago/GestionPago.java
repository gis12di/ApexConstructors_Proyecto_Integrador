package Logica.Pago;

import Persistencia.CrudPago;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;

public class GestionPago {
    private final CrudPago crudPago;

    public GestionPago() {
        this.crudPago = new CrudPago();
    }

    public List<Pago> obtenerPagos(String criterio) {
        return crudPago.obtener(criterio);
    }

    public boolean guardarPago(Pago pago, JFrame frame) {
        if (pago.getIdPago() == null || pago.getIdPago().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El ID del pago no puede estar vacío.");
            return false;
        }
        return crudPago.guardar(pago);
    }

    public boolean actualizarPago(Pago pago) {
        return crudPago.actualizar(pago);
    }

    public boolean eliminarPago(String idPago) {
        return crudPago.eliminar(idPago);
    }
}
