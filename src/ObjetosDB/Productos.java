
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

    public Productos(String nombre, String marca, String talla, String color, int precioCompra, int precioVenta, String proveedor, int cantidadActual, String tipo, String codigo_barra, Boolean saveme) 
    { //Constructor
        this.nombre = nombre;
        this.marca = marca;
        this.color = color;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.proveedor = proveedor;
        this.cantidadActual = cantidadActual;
        this.tipo = tipo;
        this.codigo_barra = codigo_barra;
        if(saveme)
            {
             //Guardamos el producto en la base de datos
             DB_connection c = new DB_connection();
             Connection conexion = c.getConnection();
             String query = "INSERT INTO Productos (id_producto,codigo_barra,nombre,marca,talla,color,precio_compra,precio_venta,proveedor,cantidad_actual) VALUES (?,?,?,?,?,?,?,?,?,?);";
            try {
                PreparedStatement stm = conexion.prepareStatement(query);
                stm.setInt(1,new metodosDB().getLastId("Productos")+1);
                stm.setString(2,this.codigo_barra);
                stm.setString(3,this.nombre);
                stm.setString(4,this.marca);
                stm.setString(5,this.talla);
                stm.setString(6,this.color);
                stm.setInt(7,this.precioCompra);
                stm.setInt(8, this.precioVenta);
                stm.setString(9,this.proveedor);
                stm.setInt(10,this.cantidadActual);
                //stm.setString(11,this.tipo);
                stm.executeUpdate();
                new metodosDB().closeConnections(c,conexion,stm,null);
            } catch (SQLException ex) {System.out.println("Error al almacenar producto");}
            }
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
    
}
