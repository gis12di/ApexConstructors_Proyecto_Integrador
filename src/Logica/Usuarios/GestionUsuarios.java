package Logica.Usuarios;

import Persistencia.CrudUsuarios;

public class GestionUsuarios {
    private final CrudUsuarios crudUsuarios;

    public GestionUsuarios() {
        this.crudUsuarios = new CrudUsuarios();
    }

    // Método para validar el rol del usuario
    public String validarRolUsuario(String usuario, String contrasena) {
        if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            throw new IllegalArgumentException("El usuario y la contraseña no pueden estar vacíos.");
        }
        // Llamar al método de la capa de persistencia para obtener el rol
        return crudUsuarios.obtenerRolUsuario(usuario, contrasena);
    }
}
