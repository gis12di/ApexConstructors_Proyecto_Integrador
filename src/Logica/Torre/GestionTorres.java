
// GestionTorres.java
import Logica.Torre.Torre;
import Persistencia.CrudTorres;
import java.util.List;

public class GestionTorres {
    private CrudTorres crudTorres;

    public GestionTorres() {
        this.crudTorres = new CrudTorres();
    }

    public void actualizarTorre(Torre torre) {
        crudTorres.actualizar(torre);
        System.out.println("Torre actualizada correctamente.");
    }

    public void eliminarTorre(int id) {
        crudTorres.eliminar(id);
        System.out.println("Torre eliminada correctamente.");
    }

    public void guardarTorre(Torre torre) {
        if (crudTorres.existeCodProyecto(torre.getCodProyecto())) {
            crudTorres.guardar(torre);
            System.out.println("Torre guardada correctamente.");
        } else {
            System.out.println("Error: El codProyecto no existe.");
        }
    }

    public List<Torre> obtenerTorresPorCodProyecto(String codProyecto) {
        return crudTorres.obtenerPorCodProyecto(codProyecto);
    }
}
