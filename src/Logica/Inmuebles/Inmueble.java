public class Inmueble {
    private int id;
    private int numInmueble;
    private String tipoUnidad;
    private double valorInmueble;
    private double area;
    private int codTorre;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNumInmueble() { return numInmueble; }
    public void setNumInmueble(int numInmueble) { this.numInmueble = numInmueble; }

    public String getTipoUnidad() { return tipoUnidad; }
    public void setTipoUnidad(String tipoUnidad) { this.tipoUnidad = tipoUnidad; }

    public double getValorInmueble() { return valorInmueble; }
    public void setValorInmueble(double valorInmueble) { this.valorInmueble = valorInmueble; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public int getCodTorre() { return codTorre; }
    public void setCodTorre(int codTorre) { this.codTorre = codTorre; }
}
