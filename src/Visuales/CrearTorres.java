package Visuales;
import Logica.Torre.GestionTorres;
import Logica.Torre.Torre;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearTorres extends JFrame {//La clase CrearTorres permite crear torres asociadas a un proyecto específico
    private JTextField NumTorres;
    private JTextField NumPisos;
    private String codProyecto;
    private GestionTorres gestionTorres;

    public CrearTorres(String codProyecto) {//Constructor que inicializa la ventana y sus componentes
        this.codProyecto = codProyecto;
        this.gestionTorres = new GestionTorres();

        setTitle("Crear Torres");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear componentes
        JLabel lblNumTorres = new JLabel("Número de Torres:");
        NumTorres = new JTextField(10);
        JLabel lblNumPisos = new JLabel("Número de Pisos:");
        NumPisos = new JTextField(10);
        JButton btnCrear = new JButton("Crear");

        // Configurar panel
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(lblNumTorres);
        panel.add(NumTorres);
        panel.add(lblNumPisos);
        panel.add(NumPisos);
        panel.add(new JLabel());
        panel.add(btnCrear);

        add(panel);

        // Acción del botón Crear
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numTorres = Integer.parseInt(NumTorres.getText());
                    int numPisos = Integer.parseInt(NumPisos.getText());

                    for (int i = 1; i <= numTorres; i++) {
                        Torre torre = new Torre();
                        torre.setNumTorre(String.valueOf(i));
                        torre.setNumPisos(String.valueOf(numPisos));
                        torre.setCodProyecto(codProyecto);

                        gestionTorres.guardarTorre(torre);
                    }

                    JOptionPane.showMessageDialog(CrearTorres.this, "Torres creadas correctamente.");
                    dispose(); // Cerrar la ventana después de crear las torres
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CrearTorres.this, "Ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
