package Logica.Usuarios;

public class Usuario {
    private String id;          // Identificador único
    private String nombre;      // Nombre del usuario
    private String apellido;    // Apellido del usuario
    private String usuario;     // Nombre de usuario
    private String contrasena;  // Contraseña del usuario
    private String rol;         // Rol del usuario

    // Constructor vacío
    public Usuario() {}

    // Constructor con todos los atributos
    public Usuario(String id, String nombre, String apellido, String usuario, String contrasena, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
