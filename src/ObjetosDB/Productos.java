
package ObjetosDB;

import Clases.DB_connection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Productos   {

    private String nombre;
    private String marca;
    private String talla;
    private String color;
    private Integer precioCompra;
    private Integer precioVenta;
    private String proveedor;
    private Integer cantidadActual;
    private String tipo;
    private String codigo_barra;
    private Integer id_producto;
    public Productos(int id_producto,String nombre, String marca, String talla, String color, int precioCompra, int precioVenta, String proveedor, int cantidadActual, String tipo, String codigo_barra, Boolean saveme) 
    { //Constructor
        this.id_producto=id_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.color = color;
        this.talla = talla;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.proveedor = proveedor;
        this.cantidadActual = cantidadActual;
        this.tipo = tipo;
        this.codigo_barra = codigo_barra;
       
    }
    public Productos(){}

    /*Setters y Getters*/
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Integer precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
/*Fin Setters y Getters*/
   
    public Productos getProductoById(int id_buscada)//Implementar
    {
    /*Retorna un objeto productos desde la base de datos, segun la id dada*/
    return null;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the codigo_barra
     */
    public String getCodigo_barra() {
        return codigo_barra;
    }

    /**
     * @param codigo_barra the codigo_barra to set
     */
    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    /**
     * @return the id_producto
     */
    public Integer getId_producto() {
        return id_producto;
    }

    /**
     * @param id_producto the id_producto to set
     */
    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }
    
}
