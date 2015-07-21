/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;


public class DB_connection{
    Connection conn = null;
public DB_connection()
    {
        Connection conn = null;
        try 
        {
       //conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/UltimoRound", "root","idp");
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/UltimoRound", "root","hola");
        System.out.println("Conección exitosa!");
        this.conn = conn;
        } catch (Exception e) {System.out.println(e);}
    }
    public Connection getConnection()
    {
    return this.conn;
    }
}
