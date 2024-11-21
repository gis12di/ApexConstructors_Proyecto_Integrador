/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.FactoryMethod;

import Logica.Interfaz.Cruds;

/**
 *
 * @author giset
 * @param <Tipo>
 */
public abstract class CreadorCrud<Tipo> {
    
    public abstract Cruds<Tipo> crearCrud();
}
