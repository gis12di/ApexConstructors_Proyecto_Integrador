package Logica.Torre;


// GestionTorres.java
import Logica.FactoryMethod.CreadorCrudTorres;
import Logica.Interfaz.Cruds; 
import Persistencia.CrudTorres;
import java.util.List; // Importa la clase List para manejar colecciones de objetos

public class GestionTorres {
    private final Cruds<Torre> crudTorres;// Instancia de CrudTorres para manejar operaciones CRUD

    public GestionTorres() {
        CreadorCrudTorres creador = new CreadorCrudTorres();
        this.crudTorres = creador.crearCrud();//Constructor que inicializa la instancia de CrudTorres.
    }

    public void actualizarTorre(Torre torre) {// Actualiza una torre en la base de datos.
        crudTorres.actualizar(torre);
        System.out.println("Torre actualizada correctamente.");
    }

    public void eliminarTorre(String id) {//Elimina una torre de la base de datos.
        crudTorres.eliminar(id);
        System.out.println("Torre eliminada correctamente.");
    }

    public void guardarTorre(Torre torre) {
        if (crudTorres instanceof CrudTorres) { // Verifica que sea de tipo CrudTorres
            CrudTorres crudTorresEspecifico = (CrudTorres) crudTorres; // Realiza el casting
            if (crudTorresEspecifico.existeCodProyecto(torre.getCodProyecto())) {
                crudTorres.guardar(torre);
                System.out.println("Torre guardada correctamente.");
            } else {
                System.out.println("Error: El codProyecto no existe.");
            }
        } else {
            System.out.println("Error: La operación no es compatible con esta implementación.");
        }
    }


    public List<Torre> obtenerTorresPorCodProyecto(String codProyecto) {//Obtiene una lista de torres por código de proyecto.
        return crudTorres.obtener(codProyecto);
    }
}
