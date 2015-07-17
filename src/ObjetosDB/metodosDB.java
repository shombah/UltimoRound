package ObjetosDB;

import Clases.DB_connection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*El objetivo de esta clase es hacer querys a la basde de datos retornando colecciones de datos con los objetos cargados.
La idea es cargar los datos desde la base de datos en esta clase y luego manejar los Arraylist en la clase Clases/Metodos_objetos*/
public class metodosDB 
{
    public metodosDB() //Constructor
    { 
    }

/*METODOS DE UTILIDAD*/
 // <editor-fold defaultstate="collapsed" desc="Metodos de Utilidad">                          

    public int getLastId(String nombreTabla) throws SQLException
    {   //retorna ultimo id desde la tabla cuyo nombre es : nombreTabla
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
  
        String columname = "";
        switch(nombreTabla)
        {
            case "Cliente": 
                columname="id_cliente";
                break;
            case "CompraProducto": 
                columname="id_compra_producto";
                break;
            case "Ki_productos": 
                columname="id_kit_productos";
                break;
            case "OrdenDeCompra": 
                columname="id_orde_compra";
                break;
            case "OrdenDeVenta": 
                columname="id_orden_venta";
                break;
            case "Productos": 
                columname="id_producto";
                break;
            case "Usuarios": 
                columname="id_usuario";
                break;
            case "VentaProducto": 
                columname="id_venta_producto";
                break;
            default : columname="";
                    break;
        }
        String query = "SELECT * FROM "+nombreTabla+" ORDER BY "+columname+" DESC LIMIT 1";
        PreparedStatement stm = conexion.prepareStatement(query);
        int id=0;
        ResultSet resultados = stm.executeQuery();
        if(resultados.next())
           id = resultados.getInt(columname);
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
// </editor-fold> 
/*FIN METODOS DE UTILIDAD*/  
    
/*METODOS DE USUARIOS*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas Usuarios">                          

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
// </editor-fold>
/*FIN METODOS DE USUARIOS*/
    
/*METODOS DE CLIENTES*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas Clientes">                          

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
            Cliente cliente  = new Cliente(resultados.getInt("id_cliente"),resultados.getString("nombre"),resultados.getString("telefono"), resultados.getString("email"),false);
            clientesDB.add(cliente);
        }
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
        return clientesDB;
    }
      public Cliente getClienteById(int id_cliente) throws SQLException
    {//Retorna el cliente asociado a la orden de venta
        //Retorna un ArrayList de clientes desde la base de datos.
        Cliente cliente = null;
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM Cliente";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
            if(resultados.getInt("id_cliente") == id_cliente)
            {
                cliente  = new Cliente(id_cliente,resultados.getString("nombre"),resultados.getString("telefono"), resultados.getString("email"),false);
                closeConnections(c,conexion,stm,resultados);
                return cliente;
            }
            
        }
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
        return null;
    }
// </editor-fold>
/*FIN METODOS CLIENTES*/
      
/*METODOS DE COMPRAPRODUCTO*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas CompraProducto">                          

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
              //OrdenDeCompra ordenCompra = new OrdenDeCompra().getOrdenDeCompraById(resultados.getInt("id_orden_de_compra"));
              
              //Armamos el objeto compraProducto
              CompraProducto compraProducto = new CompraProducto(resultados.getInt("id_compra_producto"),resultados.getString("fecha"),resultados.getInt("cantidad"),resultados.getInt("monto_unitario"),resultados.getInt("monto_total"),resultados.getString("proveedor"),resultados.getInt("id_orden_compra"),producto);
              //Lo agregamos al ArrayList
              comprasEncontradas.add(compraProducto);
          }
      }
      //Finalmente, cerramos las conexiones a la base de datos y retornamos el ArrayList
      closeConnections(c,conexion,stm,resultados);
      return comprasEncontradas;   
     }
     public ArrayList<CompraProducto> getCompraProductoByIdOrdenCompra(int id_orden_compra) throws SQLException 
     {//Retorna un ArrayList de Compras producto segun la Orden de Compra asociada.
        ArrayList<CompraProducto> comprasEncontradas = new ArrayList<CompraProducto>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM CompraProducto";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
            if(resultados.getInt("id_orden_compra") == id_orden_compra)
            {
              //Buscamos el producto asociado desde la base de datos
              Productos producto = new Productos().getProductoById(resultados.getInt("id_producto"));
              //Armamos el objeto compraProducto
              CompraProducto compraProducto = new CompraProducto(resultados.getInt("id_compra_producto"),resultados.getString("fecha"),resultados.getInt("cantidad"),resultados.getInt("monto_unitario"),resultados.getInt("monto_total"),resultados.getString("proveedor"),resultados.getInt("id_orden_compra"),producto);
              //Lo agregamos al ArrayList
              comprasEncontradas.add(compraProducto);
            }
        }
        //Finalmente, cerramos las conexiones a la base de datos y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
        return comprasEncontradas;
     }
     public ArrayList<CompraProducto> getCompraProducto() throws SQLException
     {  //Retorna ArrayList con todas las compras producto de la base de datos
        ArrayList<CompraProducto> comprasEncontradas = new ArrayList<CompraProducto>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM CompraProducto";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
           Productos producto = new Productos().getProductoById(resultados.getInt("id_producto"));
           CompraProducto compraProducto = new CompraProducto(resultados.getInt("id_compra_producto"),resultados.getString("fecha"),resultados.getInt("cantidad"),resultados.getInt("monto_unitario"),resultados.getInt("monto_total"),resultados.getString("proveedor"),resultados.getInt("id_orden_compra"),producto);
        }
        closeConnections(c,conexion,stm,resultados);
        return comprasEncontradas;
     }
// </editor-fold>
/*FIN METODOS DE COMPRAPRODUCTO*/
     
/*METODOS ORDENDECOMPRA*/
 // <editor-fold defaultstate="collapsed" desc="Metodos de Tablas OrdenDeCompra">                          

    public OrdenDeCompra getOrdenDeCompraById(int id_buscada) throws SQLException
    {
    /*Retorna un objeto OrdenDeCompra desde la base de datos, segun la id dada*/
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM OrdenDeCompra";
     PreparedStatement stm = conexion.prepareStatement(query);   
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
     {
         if(resultados.getInt("id_orden_compra")==id_buscada)
         {
             //Buscamos el array de compras productos asociadas a esta orden de compra
             ArrayList<CompraProducto> comprasDeEstaOrden = new metodosDB().getCompraProductoByIdOrdenCompra(id_buscada);
             //(int idOrdenCompra, String fecha, int montoTotal, int numeroFacturaRecibida, String proveedor, ArrayList<CompraProducto> comprasProducto) 
             OrdenDeCompra ordenEncontrada = new OrdenDeCompra(id_buscada, resultados.getString("fecha"),resultados.getInt("monto_total"),resultados.getInt("numero_factura_recibida"),resultados.getString("proveedor"),comprasDeEstaOrden);
             closeConnections(c,conexion,stm,resultados);
             return ordenEncontrada;
     
         }
     }
     closeConnections(c,conexion,stm,resultados);
    return null;
    }
      
     public ArrayList<OrdenDeCompra> getOrdenDeCompra() throws SQLException
     { //Retorna un ArrayList con todas las ordenes de compra en la BD
      ArrayList<OrdenDeCompra> ordenesEncontradas = new ArrayList<OrdenDeCompra>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM OrdenDeCompra";
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
          //Buscamos el array de compras productos asociadas a esta orden de compra
          ArrayList<CompraProducto> comprasDeEstaOrden = new metodosDB().getCompraProductoByIdOrdenCompra(resultados.getInt("id_orden_compra"));
          OrdenDeCompra ordenEncontrada = new OrdenDeCompra(resultados.getInt("id_orden_compra"), resultados.getString("fecha"),resultados.getInt("monto_total"),resultados.getInt("numero_factura_recibida"),resultados.getString("proveedor"),comprasDeEstaOrden);
          ordenesEncontradas.add(ordenEncontrada);  
      }
      closeConnections(c,conexion,stm,resultados);
      return ordenesEncontradas;
     }
// </editor-fold>
/*FIN METODOS ORDENDECOMPRA*/

/*METODOS VENTA PRODUCTO*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas VentaProducto">                          

     public ArrayList<VentaProducto> getVentaProductoByIdOrdenVenta(int id_orden_venta) throws SQLException //Implementar
     {      //Retorna un arraylist con ventasproducto segun la orden de venta dada.
        ArrayList<VentaProducto> ventasAsociadas = new ArrayList<VentaProducto>();
        VentaProducto ventaEncontrada = null;
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM VentaProducto WHERE id_orden_venta = ?";
        PreparedStatement stm = conexion.prepareStatement(query);
        stm.setInt(1,id_orden_venta);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
            if(resultados.getString("kit_or_product").equals("kit"))
            {
                Kitproductos kit = new Kitproductos().getKitById(resultados.getInt("id_kit_producto"));
                 ventaEncontrada = new VentaProducto(resultados.getInt("id_venta_producto"),resultados.getString("fecha"),resultados.getInt("precio_unitario_neto"),resultados.getInt("cantidad_producto"),resultados.getInt("precio_unitario_final"),resultados.getInt("precio_total_neto"),resultados.getInt("precio_total_final"),resultados.getInt("descuento"),kit,null,resultados.getInt("id_orden_de_venta"),"kit");

            }else
            {
                Productos producto = new Productos().getProductoById(resultados.getInt("id_kit_producto")); //get producto by id;
                 ventaEncontrada = new VentaProducto(resultados.getInt("id_venta_producto"),resultados.getString("fecha"),resultados.getInt("precio_unitario_neto"),resultados.getInt("cantidad_producto"),resultados.getInt("precio_unitario_final"),resultados.getInt("precio_total_neto"),resultados.getInt("precio_total_final"),resultados.getInt("descuento"),null,producto,resultados.getInt("id_orden_de_venta"),"producto");

            }
                
            ventasAsociadas.add(ventaEncontrada);
        }
         closeConnections(c,conexion,stm,resultados);
         return ventasAsociadas;
     }
//</editor-fold>
/*FIN METODOS VENTAPRODUCTO*/
     
/*METODOS ORDENDEVENTA*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas OrdenDeVenta">                          

     public ArrayList<OrdenDeVenta> getOrdenDeVenta() throws SQLException
     {//Retorna un ArrayList con todas las ordenes de vena en la BD
      ArrayList<OrdenDeVenta> ordenesEncontradas = new ArrayList<OrdenDeVenta>();
      ArrayList<VentaProducto> ventasAsociadas = new ArrayList<VentaProducto>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM OrdenDeVenta";
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
          //Buscamos el cliente asociado a esta orden de venta
          Cliente cliente = getClienteById(resultados.getInt("id_cliente"));
          //Buscamos las ventas producto asociadas a esta orden de venta
          ventasAsociadas = getVentaProductoByIdOrdenVenta(resultados.getInt("id_orden_venta"));
          //Armamos el objeto OrdenDeVenta
          OrdenDeVenta orden  = new OrdenDeVenta(resultados.getInt("id_orden_venta"),resultados.getString("fecha"),resultados.getString("monto_total"),resultados.getInt("numero_boleta"),resultados.getString("medio_pago"),resultados.getInt("estado_presupuesto"),cliente,ventasAsociadas);
          ordenesEncontradas.add(orden);
      }
      closeConnections(c,conexion,stm,resultados);
      return ordenesEncontradas;
     }
     public ArrayList<OrdenDeVenta> getVentasDiarias(String fecha) throws SQLException
     {//Retorna un arraylist con las ventas de la fecha indicada
            ArrayList<VentaProducto> ventasAsociadas = new ArrayList<VentaProducto>();
            ArrayList<OrdenDeVenta> ordenesEncontradas = new ArrayList<OrdenDeVenta>();
            DB_connection c = new DB_connection();
            Connection conexion = c.getConnection();
            String query = "SELECT * FROM OrdenDeVenta WHERE fecha = ?";
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, fecha);
            ResultSet resultados = stm.executeQuery();
            while(resultados.next())
            {
               //Buscamos el cliente asociado a esta orden de venta
                Cliente cliente = getClienteById(resultados.getInt("id_cliente"));
                //Buscamos las ventas producto asociadas a esta orden de venta
                ventasAsociadas = getVentaProductoByIdOrdenVenta(resultados.getInt("id_orden_venta"));
                //Armamos el objeto OrdenDeVenta
                OrdenDeVenta orden  = new OrdenDeVenta(resultados.getInt("id_orden_venta"),resultados.getString("fecha"),resultados.getString("monto_total"),resultados.getInt("numero_boleta"),resultados.getString("medio_pago"),resultados.getInt("estado_presupuesto"),cliente,ventasAsociadas);
                ordenesEncontradas.add(orden);
            }
            closeConnections(c,conexion,stm,resultados);
            return ordenesEncontradas;
     }
     
//</editor-fold>
/*FIN METODOS ORDENDEVENTA*/

/*METODOS PRODUCTOS*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas Productos">
public ArrayList<Productos> getProductos() throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM Productos";
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        Productos product = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),"tipo",resultados.getString("codigo_barra"),false);      
        productos.add(product);
      }
    return productos;
}
public boolean addProductos(Productos m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "INSERT INTO Productos (id_producto,codigo_barra,nombre,marca,talla,color,precio_compra,precio_venta,proveedor,cantidad_actual,tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?);";    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
       stm.setInt(1,new metodosDB().getLastId("Productos")+1);
       stm.setString   (3, m.getNombre());
      stm.setString(4, m.getMarca());
      stm.setString   (5, m.getTalla());
      stm.setString(6,m.getColor());
      stm.setInt   (7, m.getPrecioCompra());
      stm.setInt(8, m.getPrecioVenta());
      stm.setString   (9, m.getProveedor());
      stm.setInt(10, m.getCantidadActual());
          stm.setString   (11, m.getTipo());
              stm.setString   (2, m.getCodigo_barra());
       stm.executeUpdate();
    stm.close();
    
    return true;
}
public boolean updateProductos(Productos m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "UPDATE productos set nombre = ? , marca= ? , talla = ? , color=?, precio_compra=?, precio_venta=?, proveedor=?,cantidad_actual=?,tipo=?,codigo_barra=? where id_producto=?";
    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
      
       stm.setString   (1, m.getNombre());
      stm.setString(2, m.getMarca());
      stm.setString   (3, m.getTalla());
      stm.setString(4,m.getColor());
      stm.setInt   (5, m.getPrecioCompra());
      stm.setInt(6, m.getPrecioVenta());
      stm.setString   (7, m.getProveedor());
      stm.setInt(8, m.getCantidadActual());
          stm.setString   (9, m.getTipo());
              stm.setString   (10, m.getCodigo_barra());
               stm.setInt(11, m.getId_producto());
       stm.executeUpdate();
    stm.close();
    
    return true;
}

public boolean DeleteProductoById(int id_producto) throws SQLException
{
   Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
     
      String query = "DELETE FROM PRODUCTOS WHERE id_producto=?";
    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
      System.out.println(id_producto);
       stm.setInt   (1, id_producto);

     stm.executeUpdate();
    return true;
}

public Productos getProductoById(int id_producto) throws SQLException
{
   Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM Productos WHERE id_producto="+id_producto;
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        producto = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),"tipo",resultados.getString("codigo_barra"),false);      
       
      }
    return producto;
}
public ArrayList<Productos> getProductosByIdCompraProducto(int id_compra) throws SQLException
{
 return null;
}

// </editor-fold>

/*FIN METODOS PRODUCTOS*/

   /*METODOS KITPRODUCTOS*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas Kit_productos">

// </editor-fold>

/*FIN METODOS KITPRODUCTOS*/     
}
