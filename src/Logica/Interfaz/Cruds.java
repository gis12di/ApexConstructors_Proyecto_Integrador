package Logica.Interfaz;

import java.util.List;

public interface Cruds<Tipo> {
    List<Tipo> obtener(String criterio);
    public boolean guardar(Tipo obj);
    public boolean actualizar(Tipo obj);
    public boolean eliminar(String criterio);
    
}
