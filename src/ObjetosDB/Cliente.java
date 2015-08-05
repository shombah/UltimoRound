package ObjetosDB;

import Clases.DB_connection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cliente extends metodosDB  
{
    private int idCliente;
    private String nombre, telefono, email;
    
    public Cliente(int idCliente, String nombre, String telefono, String email, Boolean saveme) 
    { 
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        //Si saveme = true entonces guardamos el cliente en la base de datos
        if(saveme)
        {
            DB_connection c = new DB_connection();
            Connection conexion = c.getConnection();
            String query = "INSERT INTO Cliente (id_cliente,nombre,telefono,email) VALUES (?,?,?,?)";
            try 
            {
                PreparedStatement stm = conexion.prepareStatement(query);
                stm.setInt(1,getLastId("Cliente"));
                stm.setString(2,this.nombre);
                stm.setString(3,this.telefono);
                stm.setString(4, this.email);
                stm.executeUpdate();
                closeConnections(c,conexion,stm,null);
            } catch (SQLException ex) {System.out.println("Error almacenando Cliente");}
        }
    }

    public Cliente() {
    }

   
    /*Setters y Getters*/
    public Integer getIdCliente() 
    {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente)
    {
        this.idCliente = idCliente;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
    /*Fin Setters y Getters*/
   
   
    
}
