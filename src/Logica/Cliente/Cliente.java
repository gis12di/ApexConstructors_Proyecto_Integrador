package Logica.Cliente;

public class Cliente {
    private String numDocumento;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String subsidioMinisterio;
    private String sisben;

    // Getters y Setters
    public String getNumDocumento() { return numDocumento; }
    public void setNumDocumento(String numDocumento) { this.numDocumento = numDocumento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubsidioMinisterio() { return subsidioMinisterio; }
    public void setSubsidioMinisterio(String subsidioMinisterio) { this.subsidioMinisterio = subsidioMinisterio; }

    public String getSisben() { return sisben; }
    public void setSisben(String sisben) { this.sisben = sisben; }
}
