package Logica;

public class Autenticacion {
    private ObtenerUsuario obtenerUsuario;

    public Autenticacion() {
        obtenerUsuario = new ObtenerUsuario();
    }

    // Método para verificar si las credenciales son válidas
    public boolean verificarCredenciales(String usuario, String contrasena) {
        // Llama a UsuarioDAO para obtener el rol del usuario
        String rol = obtenerUsuario.obtenerRolUsuario(usuario, contrasena);
        return rol != null; // Si el rol no es nulo, las credenciales son correctas
    }

    // Método para obtener el rol del usuario
    public String obtenerRol(String usuario, String contrasena) {
        return obtenerUsuario.obtenerRolUsuario(usuario, contrasena);
    }
}