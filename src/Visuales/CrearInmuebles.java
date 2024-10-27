import Logica.Inmuebles.Inmueble;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearInmuebles extends JFrame {
    private JComboBox<String> tipoUnidadCombo;
    private JTextField valor;
    private JTextField area;
    private String codTorre;
    private int contador = 0;
    private int totalInmuebles;
    private GestionInmuebles gestionInmuebles;

    public CrearInmuebles(String codTorre, int totalInmuebles) {
        this.codTorre = codTorre;
        this.totalInmuebles = totalInmuebles;
        this.gestionInmuebles = new GestionInmuebles();

        setTitle("Crear Inmuebles");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2)); // Ajustar el diseño

        // Inicializar el combo box para seleccionar el tipo de unidad
        tipoUnidadCombo = new JComboBox<>(new String[] {"Local", "Apartamento", "Garaje"});
        valor = new JTextField(10);
        area = new JTextField(10);

        // Etiquetas y campos
        add(new JLabel("Tipo de Unidad:"));
        add(tipoUnidadCombo);
        add(new JLabel("Valor:"));
        add(valor);
        add(new JLabel("Área:"));
        add(area);

        // Botón para crear inmueble
        JButton btnCrear = new JButton("Crear Inmueble");
        add(btnCrear);

        // Listener para el botón Crear
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Verificar si se han creado todos los inmuebles
                    if (contador >= totalInmuebles) {
                        JOptionPane.showMessageDialog(CrearInmuebles.this, "Se han creado todos los inmuebles. Finalizando...");
                        dispose(); // Cerrar la ventana automáticamente
                        return;
                    }

                    // Validar los campos de entrada
                    String tipoUnidadInput = tipoUnidadCombo.getSelectedItem().toString();
                    String valorInput = valor.getText().trim();
                    String areaInput = area.getText().trim();

                    if (valorInput.isEmpty() || areaInput.isEmpty()) {
                        JOptionPane.showMessageDialog(CrearInmuebles.this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double valorInmueble = Double.parseDouble(valorInput);
                    double areaInmueble = Double.parseDouble(areaInput);

                    // Crear el inmueble
                    Inmueble inmueble = new Inmueble();
                    inmueble.setNumInmueble(String.valueOf(contador + 1)); // Establecer el número del inmueble
                    inmueble.setTipoUnidad(tipoUnidadInput);
                    inmueble.setValorInmueble(valorInmueble);
                    inmueble.setArea(areaInmueble);
                    inmueble.setCodTorre(codTorre);

                    // Guardar el inmueble usando GestionInmuebles
                    boolean guardado = gestionInmuebles.guardarInmueble(inmueble);

                    if (guardado) {
                        // Incrementar el contador y limpiar los campos
                        contador++;
                        valor.setText("");
                        area.setText("");

                        // Mostrar mensaje de confirmación
                        JOptionPane.showMessageDialog(CrearInmuebles.this, "Inmueble " + contador + " de " + totalInmuebles + " creado correctamente.");

                        // Cerrar la ventana si se ha alcanzado el límite
                        if (contador >= totalInmuebles) {
                            JOptionPane.showMessageDialog(CrearInmuebles.this, "Se han creado todos los inmuebles. Finalizando...");
                            dispose(); // Cerrar la ventana
                        }
                    } else {
                        JOptionPane.showMessageDialog(CrearInmuebles.this, "Error al guardar el inmueble. Verifique los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CrearInmuebles.this, "Ingrese valores numéricos válidos para el valor y el área.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(CrearInmuebles.this, "Ocurrió un error al crear el inmueble.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        SwingUtilities.invokeLater(() -> new CrearInmuebles("Torre1", 5).setVisible(true)); // Prueba con un número de inmuebles
    }
}
