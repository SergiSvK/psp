
package org.psp.comunes;

import java.io.Serializable;
import java.util.List;


/**
 * Representa una ciudad y sus datos
 * @author ruben
 */
public class Ciudad implements Serializable {
    private String codigo;
    private String nombre;
    private List<String> temperaturas;

    public Ciudad(String codigo,String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }    
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    /**
     * @return the temperaturas
     */
    public List<String>  getTemperaturas() {
        return temperaturas;
    }

    /**
     * @param temperaturas the temperaturas to set
     */
    public void setTemperaturas(List<String> temperaturas) {
        this.temperaturas = temperaturas;
    }
    
    @Override
    public String toString() {
        return this.nombre;
    }

    
    
}
