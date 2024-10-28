package Logica.Pago;

import java.util.Date;

public class Pago {
    private String idPago;
    private double valorPago;
    private Date fechaPago;
    private String idInmueble;
    private String cedCliente;
    private String cedAsesor;
    private String idVenta;

    // Getters y Setters
    public String getIdPago() { return idPago; }
    public void setIdPago(String idPago) { this.idPago = idPago; }

    public double getValorPago() { return valorPago; }
    public void setValorPago(double valorPago) { this.valorPago = valorPago; }

    public Date getFechaPago() { return fechaPago; }
    public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }

    public String getIdInmueble() { return idInmueble; }
    public void setIdInmueble(String idInmueble) { this.idInmueble = idInmueble; }

    public String getCedCliente() { return cedCliente; }
    public void setCedCliente(String cedCliente) { this.cedCliente = cedCliente; }

    public String getCedAsesor() { return cedAsesor; }
    public void setCedAsesor(String cedAsesor) { this.cedAsesor = cedAsesor; }

    public String getIdVenta() { return idVenta; }
    public void setIdVenta(String idVenta) { this.idVenta = idVenta; }
}
