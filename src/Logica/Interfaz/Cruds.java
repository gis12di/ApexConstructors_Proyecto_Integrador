/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logica.Interfaz;

import java.util.List;

/**
 *
 * @author giset
 * @param <Tipo>
 */
public interface Cruds<Tipo> {
    List<Tipo> obtener(String criterio);
    public boolean guardar(Tipo obj);
    public boolean actualizar(Tipo obj);
    public boolean eliminar(String criterio);
}
