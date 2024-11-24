
package Visuales;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// CrearProyecto.java
public class CrearProyecto extends JFrame {
    private JTextField txtNombreProyecto;
    private JButton btnGuardar;
    private CrearProyectoController crearProyectoController;
    private String codigoProyectoRecienCreado;

    // Constructor modificado para recibir la instancia de Main_Admin
    public CrearProyecto(Main_Admin adminFrame) {
        crearProyectoController = new CrearProyectoController(adminFrame);

        setTitle("Crear Proyecto");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();

        // Acción al botón Guardar
        btnGuardar.addActionListener(getGuardarAction());
    }

    private void initComponents() {
        JLabel lblNombreProyecto = new JLabel("Nombre del Proyecto:");
        txtNombreProyecto = new JTextField(30);
        btnGuardar = new JButton("Guardar");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(lblNombreProyecto, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtNombreProyecto, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(btnGuardar, gbc);

        add(panel);
    }

    // Acción para guardar el proyecto
    private ActionListener getGuardarAction() {
        return e -> {
            String nombreProyecto = txtNombreProyecto.getText();
            // Llamar al controlador para guardar el proyecto y obtener el código generado
            codigoProyectoRecienCreado = crearProyectoController.guardarProyecto(nombreProyecto, this);
            if (codigoProyectoRecienCreado != null) {
                // Cerrar ventana si el proyecto fue creado correctamente
                this.dispose();
            }
        };
    }

    public String getCodigoProyecto() {
        return codigoProyectoRecienCreado;
    }
}

