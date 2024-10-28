package Logica.Venta;

import java.util.Date;

public class Venta {
    private String idVenta;
    private double precioTotal;
    private Date fechaVenta;
    private String matricula;
    private Date fechaEscritura;
    private String idInmueble;
    private String numDocumentoCliente;
    private String cedAsesor;

    // Getters y Setters
    public String getIdVenta() { return idVenta; }
    public void setIdVenta(String idVenta) { this.idVenta = idVenta; }

    public double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }

    public Date getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public Date getFechaEscritura() { return fechaEscritura; }
    public void setFechaEscritura(Date fechaEscritura) { this.fechaEscritura = fechaEscritura; }

    public String getIdInmueble() { return idInmueble; }
    public void setIdInmueble(String idInmueble) { this.idInmueble = idInmueble; }

    public String getNumDocumentoCliente() { return numDocumentoCliente; }
    public void setNumDocumentoCliente(String numDocumentoCliente) { this.numDocumentoCliente = numDocumentoCliente; }

    public String getCedAsesor() { return cedAsesor; }
    public void setCedAsesor(String cedAsesor) { this.cedAsesor = cedAsesor; }
}
