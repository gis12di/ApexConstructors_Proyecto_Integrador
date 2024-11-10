package Logica.Proyecto;

public class Proyecto {
    private int codigo;
    private String nombre;

    // Constructor vacío
    public Proyecto() {}

    // Constructor con parámetros
    public Proyecto(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Sobrescribir el método toString para mostrar el nombre en el JComboBox
    @Override
    public String toString() {
        return nombre;
    }
}
