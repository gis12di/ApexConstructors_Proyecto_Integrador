package Logica.Inmuebles;

public class Inmueble {
    private String id;
    private String numInmueble;
    private String tipoUnidad;
    private double valorInmueble;
    private double area;
    private String codTorre;

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumInmueble() {
        return numInmueble;
    }

    public void setNumInmueble(String numInmueble) {
        this.numInmueble = numInmueble;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public double getValorInmueble() {
        return valorInmueble;
    }

    public void setValorInmueble(double valorInmueble) {
        this.valorInmueble = valorInmueble;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getCodTorre() {
        return codTorre;
    }

    public void setCodTorre(String codTorre) {
        this.codTorre = codTorre;
    }
}