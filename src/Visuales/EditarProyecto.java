package Visuales;
import Logica.Proyecto.GestionProyectos;
import Logica.Proyecto.Proyecto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditarProyecto extends JFrame {//La clase EditarProyecto permite editar o eliminar un proyecto específico
    private final JTextField nombreField;
    private final JButton btnActualizar;
    private final JButton btnEliminar;
    private final String codigoProyecto;
    private final int row;
    private final JTable table;
    private final GestionProyectos gestionProyectos;

    public EditarProyecto(String idProyecto, String nombreProyecto, int row, JTable table) {//Constructor que inicializa la ventana y sus componentes
        this.codigoProyecto = idProyecto;
        this.row = row;
        this.table = table;
        this.gestionProyectos = new GestionProyectos();

        setTitle("Editar/Eliminar Proyecto");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblNombre = new JLabel("Nombre del Proyecto:");
        nombreField = new JTextField(nombreProyecto, 20);
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(btnActualizar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(btnEliminar, gbc);

        btnActualizar.addActionListener(e -> { // Acción del botón Actualizar
            String nuevoNombre = nombreField.getText();
            if (!nuevoNombre.isEmpty()) {
                try {
                    int codigo = Integer.parseInt(codigoProyecto); // Conversión de String a int si es necesario
                    Proyecto proyecto = new Proyecto(codigo, nuevoNombre); // Constructor que acepta int y String
                    if (gestionProyectos.actualizarProyecto(proyecto, this)) {
                        table.setValueAt(nuevoNombre, row, 1);
                        JOptionPane.showMessageDialog(this, "Proyecto actualizado correctamente.");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar el proyecto.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El código del proyecto no es válido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
            }
        });



        btnEliminar.addActionListener(e -> { // Acción del botón Eliminar
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este proyecto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (gestionProyectos.eliminarProyecto(codigoProyecto, this)) {
                    ((DefaultTableModel) table.getModel()).removeRow(row);
                    JOptionPane.showMessageDialog(this, "Proyecto eliminado correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el proyecto.");
                }
            }
        });


        add(panel);
    }

    public static void main(String[] args) {//Método principal para probar la edición de proyectos.
        String[] columnNames = {"codigo", "Nombre"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        tableModel.addRow(new Object[]{"1", "Proyecto A"});
        tableModel.addRow(new Object[]{"2", "Proyecto B"});

        JTable table = new JTable(tableModel);

        SwingUtilities.invokeLater(() -> {
            EditarProyecto ventana = new EditarProyecto("1", "Proyecto A", 0, table);
            ventana.setVisible(true);
        });
    }
}
