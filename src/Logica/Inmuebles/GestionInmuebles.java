import Logica.Inmuebles.Inmueble;
import java.util.List;

public class GestionInmuebles {
    private CrudInmuebles crudInmuebles;

    public GestionInmuebles() {
        this.crudInmuebles = new CrudInmuebles();
    }

    // Método para guardar un inmueble
    public boolean guardarInmueble(Inmueble inmueble) {
        boolean guardado = crudInmuebles.guardar(inmueble);
        if (guardado) {
            System.out.println("Inmueble guardado correctamente.");
        } else {
            System.out.println("Error: No se pudo guardar el inmueble. Verifique que el ID de la torre sea válido.");
        }
        return guardado;
    }

    // Método para actualizar un inmueble
    public boolean actualizarInmueble(Inmueble inmueble) {
        boolean actualizado = crudInmuebles.actualizar(inmueble);
        if (actualizado) {
            System.out.println("Inmueble actualizado correctamente.");
        } else {
            System.out.println("Error: No se pudo actualizar el inmueble.");
        }
        return actualizado;
    }

    // Método para eliminar un inmueble
    public boolean eliminarInmueble(String id) {
        boolean eliminado = crudInmuebles.eliminar(id);
        if (eliminado) {
            System.out.println("Inmueble eliminado correctamente.");
        } else {
            System.out.println("Error: No se pudo eliminar el inmueble.");
        }
        return eliminado;
    }

    // Método para obtener una lista de inmuebles por ID de torre
    public List<Inmueble> obtenerInmueblesPorTorre(String idTorre) {
        return crudInmuebles.obtener(idTorre);
    }
}
