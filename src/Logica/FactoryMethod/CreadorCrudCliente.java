/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.FactoryMethod;

import Logica.Cliente.Cliente;
import Logica.Interfaz.Cruds;
import Persistencia.CrudCliente;

/**
 *
 * @author giset
 */
public class CreadorCrudCliente extends CreadorCrud<Cliente>{
    @Override
    public Cruds<Cliente> crearCrud() {
            return (Cruds<Cliente>) new CrudCliente();
    }
}
