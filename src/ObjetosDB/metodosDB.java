/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosDB;

import Clases.DB_connection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ttars
 */
public class metodosDB 
{
    public metodosDB() //Constructor
    { 
    }
    public int getLastId(String nombreTabla) throws SQLException
    {   //retorna ultimo id desde la tabla cuyo nombre es : nombreTabla
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM"+nombreTabla+"ORDER BY id_cliente DESC LIMIT 1";
        PreparedStatement stm = conexion.prepareStatement(query);
        int id = stm.executeQuery().getInt("id_cliente");
        //Cerramos las conecciones y retornamos la id encontrada.
        closeConnections(c,conexion,stm,null);
        return id;
    }
    
    public void closeConnections(DB_connection c, Connection conexion, PreparedStatement stm, ResultSet resultados) 
    {   //Cierra las conecciones a la base de datos
        c = null;
        try {
            conexion.close();
            stm.close();

        } catch (SQLException ex) {System.out.println("Error Cerrando Conexiones!");}
        resultados = null;
        System.out.println("Conexiones cerradas correctamente!");
    }
    
    public ArrayList<Usuarios> cargaUsuariosDB() throws SQLException
    {   //Retorna ArrayList con usuarios desde la base de datos
        ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM Usuarios";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
            Usuarios user = new Usuarios(resultados.getInt("id_usuario"),resultados.getString("nombre"),resultados.getString("login"),resultados.getString("password"));
            usuarios.add(user);
        }
        System.out.println("Cargados "+usuarios.size()+"Usuarios!"); //Delete
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
       return usuarios;
        
    }
     public ArrayList<Cliente> getClientes() throws SQLException
    { //Retorna un ArrayList de clientes desde la base de datos.
        ArrayList<Cliente> clientesDB = new ArrayList<Cliente>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM Cliente";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
            Cliente cliente  = new Cliente(resultados.getString("nombre"),resultados.getString("telefono"), resultados.getString("email"),false);
            clientesDB.add(cliente);
        }
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
        return clientesDB;
    }
     public ArrayList<CompraProducto> getCompraProductoEntreFechas(String fechaInicio, String fechaFinal) throws SQLException
     {//Retorna un ArrayList de comprasProducto, realizadas entre fechaInicio y fechaFinal.
      //Recordar que fecha es de formato dia/mes/ano
      ArrayList<CompraProducto> comprasEncontradas = new ArrayList<CompraProducto>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM CompraProducto";
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
          //Si la fecha de la compraProducto esta dentro del intervalo: fechaInicio<= fechaCompraProducto <= fechaFinal
          //Entonces nos interesa agregarla al ArrayList
          if((esMenorFecha(fechaInicio,resultados.getString("fecha")))&&(esMenorFecha(resultados.getString("fecha"),fechaFinal)))
          {
              //Buscamos el producto asociado desde la base de datos
              Productos producto = new Productos().getProductoById(resultados.getInt("id_producto"));
              //Buscamos la orden de compra asociada desde la base de datos
              OrdenDeCompra ordenCompra = new OrdenDeCompra().getOrdenDeCompraById(resultados.getInt("id_orden_de_compra"));
              //Armamos el objeto compraProducto
              CompraProducto compraProducto = new CompraProducto(resultados.getInt("id_compra_producto"),resultados.getString("fecha"),resultados.getInt("cantidad"),resultados.getInt("monto_unitario"),resultados.getInt("monto_total"),resultados.getString("proveedor"),ordenCompra,producto);
              //Lo agregamos al ArrayList
              comprasEncontradas.add(compraProducto);
          }
      }
      //Finalmente, cerramos las conexiones a la base de datos y retornamos el ArrayList
      closeConnections(c,conexion,stm,resultados);
      return comprasEncontradas;   
     }
     
     public boolean esMenorFecha(String fechaInicio, String fechaTermino)
     {  //Retorna un booleano para saber si fecha inicio es menor que fecha termino
         //Recordar que las fechas estan en formato "dia-mes-ano"
        /*Retornos de esta funcion
         fechaInicio<=fechaTermino = true
         fechaInicio>fechaTermino = false*/
         
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); //Formateamos la fecha
        try {
            if((sdf.parse(fechaInicio).before(sdf.parse(fechaTermino)))||(sdf.parse(fechaInicio).equals(sdf.parse(fechaTermino))))
                return true;
            else return false;
        } catch (ParseException ex) {return false; }
        
     }
     
     

        
}
