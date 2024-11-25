package Logica.Pago;

import Logica.FactoryMethod.CreadorCrudPago;
import Logica.Interfaz.Cruds;
import Persistencia.CrudPago;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.stream.Collectors;

public class GestionPago {
    private final Cruds<Pago> crudPago;

    public GestionPago() {
        CreadorCrudPago creador = new CreadorCrudPago();
        this.crudPago = creador.crearCrud();
    }

    public List<Pago> obtenerPagos(String criterio) {
        return crudPago.obtener(criterio);
    }

    public boolean guardarPagos(List<Pago> pagos) {
        CrudPago crudPago = new CrudPago();
        
        // Iterar sobre los pagos y llamar al CRUD para insertarlos
        for (Pago pago : pagos) {
            boolean resultado = crudPago.guardar(pago);
            if (!resultado) {
                return false;  // Si no se pudo insertar algún pago, devolvemos false
            }
        }
        
        return true;  // Si todos los pagos se insertaron correctamente
    }

    public boolean actualizarPago(Pago pago) {
        return crudPago.actualizar(pago);
    }

    public boolean eliminarPago(String idPago) {
        return crudPago.eliminar(idPago);
    }
    
    public List<Pago> obtenerPagosRealizados() {
        return crudPago.obtener("").stream()
                .filter(p -> "Pagado".equalsIgnoreCase(p.getEstadoPago()))
                .collect(Collectors.toList());
    }

    public List<Pago> obtenerPagosVencidos() {
        LocalDate fechaActual = LocalDate.now(ZoneId.of("America/Bogota"));
        return crudPago.obtener("").stream()
            .filter(p -> {
                LocalDate fechaPago = p.getFechaPago().toInstant()
                                         .atZone(ZoneId.systemDefault())
                                         .toLocalDate();
                return fechaPago.isBefore(fechaActual) && !"Pagado".equalsIgnoreCase(p.getEstadoPago());
            })
            .collect(Collectors.toList());
    }

    public List<Pago> obtenerPagosPendientes() {
        LocalDate fechaActual = LocalDate.now(ZoneId.of("America/Bogota"));
        return crudPago.obtener("").stream()
            .filter(p -> {
                LocalDate fechaPago = p.getFechaPago().toInstant()
                                         .atZone(ZoneId.systemDefault())
                                         .toLocalDate();
                return fechaPago.isAfter(fechaActual) && !"Pagado".equalsIgnoreCase(p.getEstadoPago());
            })
            .collect(Collectors.toList());
    }
    
}
