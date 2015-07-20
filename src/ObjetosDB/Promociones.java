/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosDB;


/**
 *
 * @author ttars
 */

public class Promociones   {
    private static final long serialVersionUID = 1L;
   
    private Integer idPromo;
    
    private int montoDcto;
  
    private String nombre;
   
    private int estado =0;

    public Promociones(int monto, String nombre) {
        this.montoDcto = monto;
        this.nombre = nombre;
    }

    public Promociones(Integer idPromo) {
        this.idPromo = idPromo;
    }
    

    public Promociones(Integer idPromo, int montoDcto, String nombre, int estado) {
        this.idPromo = idPromo;
        this.montoDcto = montoDcto;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Integer idPromo) {
        this.idPromo = idPromo;
    }

    public int getMontoDcto() {
        return montoDcto;
    }

    public void setMontoDcto(int montoDcto) {
        this.montoDcto = montoDcto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    

 
    
}
