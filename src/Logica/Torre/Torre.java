package Logica.Torre;

public class Torre {
    private String id;
    private String numTorre;
    private String numPisos;
    private String codProyecto;

    // Constructor vacío
    public Torre() {}

    // Constructor con parámetros
    public Torre(String id, String numTorre) {
        this.id = id;
        this.numTorre = numTorre;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNumTorre() { return numTorre; }
    public void setNumTorre(String numTorre) { this.numTorre = numTorre; }

    public String getNumPisos() { return numPisos; }
    public void setNumPisos(String numPisos) { this.numPisos = numPisos; }

    public String getCodProyecto() { return codProyecto; }
    public void setCodProyecto(String codProyecto) { this.codProyecto = codProyecto; }

    @Override
    public String toString() {
        return "Torre #" + numTorre;
    }
}