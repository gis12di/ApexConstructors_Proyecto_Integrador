package Logica.Cliente;

import Persistencia.CrudCliente;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;

public class GestionCliente {
    private final CrudCliente crudCliente;

    public GestionCliente() {
        this.crudCliente = new CrudCliente();
    }

    public List<Cliente> obtenerClientes() {
        return crudCliente.obtenerClientes();
    }

    public boolean guardarCliente(Cliente cliente, JFrame frame) {
        if (cliente.getNumDocumento() == null || cliente.getNumDocumento().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El documento no puede estar vacío.");
            return false;
        }
        return crudCliente.guardarCliente(cliente);
    }

    public boolean actualizarCliente(Cliente cliente) {
        return crudCliente.actualizarCliente(cliente);
    }

    public boolean eliminarCliente(String numDocumento) {
        return crudCliente.eliminarCliente(numDocumento);
    }
}
