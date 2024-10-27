import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class MostrarTorres extends JFrame {
    private String codProyecto;
    private JTable table;
    private GestionTorres gestionTorres;

    public MostrarTorres(String codProyecto) {
        this.codProyecto = codProyecto;
        this.gestionTorres = new GestionTorres();
        
        setTitle("Torres del Proyecto");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Obtener las torres del proyecto
        List<Torre> torres = gestionTorres.obtenerTorresPorCodProyecto(codProyecto);

        // Crear la tabla para mostrar las torres
        String[] columnNames = {"ID", "Número de Torre", "Número de Pisos"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Llenar la tabla con los datos de las torres
        for (Torre torre : torres) {
            Object[] rowData = {torre.getId(), torre.getNumTorre(), torre.getNumPisos()};
            tableModel.addRow(rowData);
        }

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        configurarDobleClicTabla(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    public boolean existenTorres() {
        return !gestionTorres.obtenerTorresPorCodProyecto(codProyecto).isEmpty();
    }

    // Método para verificar si existen inmuebles en una torre específica
    private boolean existenInmuebles(String idTorre) {
        GestionInmuebles gestionInmuebles = new GestionInmuebles(); // Asumiendo que existe una clase similar para inmuebles
        return !gestionInmuebles.obtenerInmueblesPorTorre(idTorre).isEmpty();
    }

    public void configurarDobleClicTabla(JTable table) {
        this.table = table;
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1 && SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    String idTorre = table.getValueAt(row, 0).toString();

                    // Verifica si ya existen inmuebles en la torre seleccionada
                    if (existenInmuebles(idTorre)) {
                        // Si existen inmuebles, muestra la ventana con los inmuebles
                        new MostrarInmuebles(idTorre).setVisible(true);
                    } else {
                        // Si no existen inmuebles, solicita al usuario crear nuevos inmuebles
                        String inputNumInmuebles = JOptionPane.showInputDialog(MostrarTorres.this, "Ingrese el número de inmuebles a crear:", "Número de Inmuebles", JOptionPane.QUESTION_MESSAGE);
                        if (inputNumInmuebles != null) {
                            try {
                                int totalInmuebles = Integer.parseInt(inputNumInmuebles.trim());
                                if (totalInmuebles > 0) {
                                    new CrearInmuebles(idTorre, totalInmuebles).setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(MostrarTorres.this, "El número de inmuebles debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(MostrarTorres.this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });
    }
}
