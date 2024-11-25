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
    private String estadoPago;
    private int numPago;        // Número de este pago
    private int numeroDePagos;  // Total de pagos

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

    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }

    

    // Getters y Setters para numPago
    public int getNumPago() { return numPago; }
    public void setNumPago(int numPago) { this.numPago = numPago; }

    // Getters y Setters para numeroDePagos
    public int getNumeroDePagos() { return numeroDePagos; }
    public void setNumeroDePagos(int numeroDePagos) { this.numeroDePagos = numeroDePagos; }

}

