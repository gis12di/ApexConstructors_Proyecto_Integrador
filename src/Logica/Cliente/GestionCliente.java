package Logica.Cliente;

import Logica.FactoryMethod.CreadorCrudCliente;
import Logica.Interfaz.Cruds;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;

public class GestionCliente {
    private final Cruds<Cliente> crudCliente;

    public GestionCliente() {
        CreadorCrudCliente creador = new CreadorCrudCliente();
        this.crudCliente = creador.crearCrud();
    }

    public List<Cliente> obtenerClientes(String criterio) {
        return crudCliente.obtener(criterio);
    }

    public boolean guardarCliente(Cliente cliente, JFrame frame) {
        if (cliente.getNumDocumento() == null || cliente.getNumDocumento().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El documento no puede estar vacío.");
            return false;
        }
        return crudCliente.guardar(cliente);
    }

    public boolean actualizarCliente(Cliente cliente) {
        return crudCliente.actualizar(cliente);
    }

    public boolean eliminarCliente(String numDocumento) {
        return crudCliente.eliminar(numDocumento);
    }
}
