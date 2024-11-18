import Logica.Pago.Pago;
import Persistencia.CrudPago;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.ZoneId;

public class GestionPagos {
    private CrudPago crudPago;

    public GestionPagos() {
        crudPago = new CrudPago();
    }

    // Pagos realizados
    public List<Pago> obtenerPagosRealizados() {
        return crudPago.obtenerPagos().stream()
                .filter(p -> "Pagado".equalsIgnoreCase(p.getEstadoPago()))
                .collect(Collectors.toList());
    }

    // Pagos vencidos
    public List<Pago> obtenerPagosVencidos() {
        LocalDate fechaActual = LocalDate.now(ZoneId.of("America/Bogota"));
        return crudPago.obtenerPagos().stream()
                .filter(p -> {
                    LocalDate fechaPago = p.getFechaPago().toInstant()
                                             .atZone(ZoneId.systemDefault())
                                             .toLocalDate();
                    return fechaPago.isBefore(fechaActual) && !"Pagado".equalsIgnoreCase(p.getEstadoPago());
                })
                .collect(Collectors.toList());
    }


    // Pagos pendientes
    public List<Pago> obtenerPagosPendientes() {
        LocalDate fechaActual = LocalDate.now(ZoneId.of("America/Bogota"));
        return crudPago.obtenerPagos().stream()
                .filter(p -> {
                    LocalDate fechaPago = p.getFechaPago().toInstant()
                                             .atZone(ZoneId.systemDefault())
                                             .toLocalDate();
                    return fechaPago.isAfter(fechaActual) && !"Pagado".equalsIgnoreCase(p.getEstadoPago());
                })
                .collect(Collectors.toList());
    }

}
