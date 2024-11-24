package Visuales;
import Logica.Inmuebles.GestionInmuebles;
import Logica.Inmuebles.Inmueble;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MostrarInmuebles extends JFrame {
    private String idTorre;
    private JTable table;
    private GestionInmuebles gestionInmuebles;

    public MostrarInmuebles(String idTorre) {
        this.idTorre = idTorre;
        this.gestionInmuebles = new GestionInmuebles();
        setTitle("Inmuebles de la Torre");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Obtener los inmuebles de la torre seleccionada
        List<Inmueble> inmuebles = gestionInmuebles.obtenerInmueblesPorTorre(idTorre);

        // Crear la tabla para mostrar los inmuebles
        String[] columnNames = {"ID", "NumInmueble", "TipoUnidad", "Valor", "Area"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Llenar la tabla con los datos de los inmuebles
        for (Inmueble inmueble : inmuebles) {
            Object[] rowData = {
                inmueble.getId(),
                inmueble.getNumInmueble(),
                inmueble.getTipoUnidad(),
                inmueble.getValorInmueble(),
                inmueble.getArea()
            };
            tableModel.addRow(rowData);
        }

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }
}
