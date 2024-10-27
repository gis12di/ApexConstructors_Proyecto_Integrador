package Logica.Usuarios;

public class Autenticacion {
    private GestionUsuarios gestionUsuarios;

    public Autenticacion() {
        // Inicializa la instancia de GestionUsuarios
        gestionUsuarios = new GestionUsuarios();
    }

    // Método para verificar si las credenciales son válidas
    public boolean verificarCredenciales(String usuario, String contrasena) {
        // Llama a GestionUsuarios para validar el rol del usuario
        String rol = gestionUsuarios.validarRolUsuario(usuario, contrasena);
        return rol != null; // Si el rol no es nulo, las credenciales son correctas
    }

    // Método para obtener el rol del usuario
    public String obtenerRol(String usuario, String contrasena) {
        // Llama a GestionUsuarios para obtener el rol del usuario
        return gestionUsuarios.validarRolUsuario(usuario, contrasena);
    }
}
