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
    int StockCritico = 10;
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
            case "Kit_productos": 
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
            case "Promociones":
                columname = "id_promo";
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
    public Cliente getClienteById2(int id_cliente) throws SQLException
{
   Productos producto = null;
   Cliente cliente=null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM Cliente WHERE id_cliente="+id_cliente;
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
         cliente = new Cliente(resultados.getInt("id_cliente"),resultados.getString("nombre"),resultados.getString("telefono"),resultados.getString("email"),false);      
          conexion.close();
  stm.close();
         return cliente;
      }
       
    return null;
}
public Cliente addCliente(Cliente m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      
      if(this.getClienteById2(m.getIdCliente())==null){
 String query = "INSERT INTO Cliente (id_cliente,nombre,telefono,email) VALUES (?,?,?,?);";    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
       stm.setInt(1, m.getIdCliente());
       stm.setString   (2, m.getNombre());
      stm.setString(3, m.getTelefono());
      stm.setString   (4, m.getEmail());
      
              stm.executeUpdate();
    stm.close();
return m;
}else{
    return m;
    }
    
    
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
                Kitproductos kit = new Kitproductos(0,null,0,0,null).getKitById(resultados.getInt("id_kit_producto"));
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

     public ArrayList<OrdenDeVenta> getVentaClienteById(int id_cliente) throws SQLException
{
   Productos producto = null;
   Cliente cliente=null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      ArrayList<OrdenDeVenta> ordenesEncontradas=null;
        ordenesEncontradas = new ArrayList<OrdenDeVenta>();
       
      String query = "SELECT * FROM ordendeventa WHERE id_cliente="+id_cliente;
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      
      while(resultados.next())
          
      {   
          
          OrdenDeVenta orden  = new OrdenDeVenta(resultados.getInt("id_orden_venta"),resultados.getString("fecha"),resultados.getString("monto_total"),resultados.getInt("numero_boleta"),resultados.getString("medio_pago"),0,null,null);
          ordenesEncontradas.add(orden);
  
       
      }
               conexion.close();
  stm.close();
   return ordenesEncontradas;
}
     
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
        Productos product = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),"tipo",resultados.getString("codigo_barra"));      
        productos.add(product);
      }
      closeConnections(c,conexion,stm,resultados);
    return productos;
}
public ArrayList<Productos> getProductosStockCritico() throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM Productos";
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
          if(resultados.getInt("cantidad_actual")<=StockCritico)
          { Productos product = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),"tipo",resultados.getString("codigo_barra"));      
            productos.add(product);
          }
      }
      closeConnections(c,conexion,stm,resultados);
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
       System.out.print(stm);
              stm.executeUpdate();
    stm.close();
    
    return true;
}
public boolean updateProductos(Productos m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "UPDATE Productos set nombre = ? , marca= ? , talla = ? , color=?, precio_compra=?, precio_venta=?, proveedor=?,cantidad_actual=?,tipo=?,codigo_barra=? where id_producto=?";
    
      
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
       conexion.close();
  stm.close();
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
        producto = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),"tipo",resultados.getString("codigo_barra"));      
       
      }
        conexion.close();
  stm.close();
    return producto;
}
public Productos getProductoByCodigo(String codigo) throws SQLException
{
Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM Productos WHERE codigo_barra=?";
  
      PreparedStatement stm = conexion.prepareStatement(query);
      stm.setString(1,codigo);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        producto = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),"tipo",resultados.getString("codigo_barra"));      
       
      }
        conexion.close();
         stm.close();
    return producto;
}

public Productos getProductoByNombre(String nombre) throws SQLException
{
Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM Productos WHERE nombre= ?";
      System.out.println(query);
      PreparedStatement stm = conexion.prepareStatement(query);
      stm.setString(1,nombre);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        producto = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),"tipo",resultados.getString("codigo_barra"));      
       
      }
        conexion.close();
         stm.close();
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
public boolean updatekitproductos(Kitproductos m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "UPDATE Kit_productos set nombre_kit = ? , precio_compra_productos= ? , precio_venta_kit = ? , descripcion_kit=? where id_kit_productos=?";
    
       String query2 = "DELETE FROM relacion_kit_productos WHERE id_kit_productos=?";
      PreparedStatement stm = conexion.prepareStatement(query);
       PreparedStatement stm2 = conexion.prepareStatement(query2);
      
       stm.setString   (1, m.getNombreKit());
      stm.setInt(2, m.getPrecioCompraProductos());
      stm.setInt   (3, m.getPrecioVentaKit());
      stm.setString(4,m.getDescripcionKit());
      stm.setInt   (5, m.getIdKitProductos());
       stm2.setInt   (1, m.getIdKitProductos());
       stm.executeUpdate();
        stm2.executeUpdate();
    stm.close();
    
    return true;
}
public int addKitProductos(Kitproductos kit) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "INSERT INTO Kit_Productos (id_kit_productos,nombre_kit,precio_compra_productos,precio_venta_kit,descripcion_kit) VALUES (?,?,?,?,?);";    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      kit.setIdKitProductos(new metodosDB().getLastId("Kit_productos")+1);
       stm.setInt(1,kit.getIdKitProductos());
       stm.setString   (2, kit.getNombreKit());
      stm.setInt(3, kit.getPrecioCompraProductos());
      stm.setInt   (4, kit.getPrecioVentaKit());
       stm.setString   (5, kit.getDescripcionKit());
              stm.executeUpdate();
           
    stm.close();
    
    return kit.getIdKitProductos();
}

public boolean addKit_relacion_productos(int id_kit_productos,int id_producto) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "INSERT INTO relacion_kit_productos (id_kit_productos,id_producto) VALUES (?,?);";    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
       stm.setInt(1,id_kit_productos);
       stm.setInt   (2, id_producto);
   
              stm.executeUpdate();
    stm.close();
    
    return true;
}
public ArrayList<Kitproductos> getKitproductos() throws SQLException
{
     ArrayList<Kitproductos> kitproductos = new ArrayList<Kitproductos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "SELECT * FROM Kit_productos";    
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        Kitproductos kitproduct = new Kitproductos(resultados.getInt("id_kit_productos"),resultados.getString("nombre_kit"),resultados.getInt("precio_compra_productos"),resultados.getInt("precio_venta_kit"),resultados.getString("descripcion_kit"));      
        kitproductos.add(kitproduct);
      }
      closeConnections(c,conexion,stm,resultados);
      System.out.println("Kit Encontrados: !\n"+kitproductos.size());

    return kitproductos;
}
public Kitproductos getKitProductoByName( String name) throws SQLException
{
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "SELECT * FROM Kit_productos";    
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      Kitproductos kitproduct = null;
      while(resultados.next())
      {
        if(resultados.getString("nombre_kit").equals(name))
        {
                     kitproduct = new Kitproductos(resultados.getInt("id_kit_productos"),resultados.getString("nombre_kit"),resultados.getInt("precio_compra_productos"),resultados.getInt("precio_venta_kit"),resultados.getString("descripcion_kit"));      

        }      
          
      }
      closeConnections(c,conexion,stm,resultados);
      return kitproduct;

}

public ArrayList<Productos> getrelacionKitproductos(int id_kit_productos) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "SELECT * FROM relacion_kit_productos where id_kit_productos=?";    
      PreparedStatement stm = conexion.prepareStatement(query);
       stm.setInt(1,id_kit_productos);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        productos.add(this.getProductoById(resultados.getInt("id_producto")));
        
      }
      closeConnections(c,conexion,stm,resultados);
    return productos;
}

public boolean deleteKit(Kitproductos kitproducto) throws SQLException
{
   Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
     
      String query = "DELETE FROM Kit_productos WHERE id_kit_productos=?";
     String query2 = "DELETE FROM relacion_kit_productos WHERE id_kit_productos=?";
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
    PreparedStatement stm2 = conexion.prepareStatement(query2);
       stm.setInt   (1, kitproducto.getIdKitProductos());
 stm2.setInt   (1, kitproducto.getIdKitProductos());
     stm.executeUpdate();
     stm2.executeUpdate();
  conexion.close();
  stm.close();
  stm2.close();
    return true;
}

/*FIN METODOS KITPRODUCTOS*/ 

/*FIN METODOS KITPRODUCTOS*/   
/**METODOS PROMOCIONES/
 *
 */
 public ArrayList<Promociones> getPromociones() throws SQLException
 {
     ArrayList<Promociones> promos = new ArrayList<Promociones>();
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM Promociones;";    
     PreparedStatement stm = conexion.prepareStatement(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
        {
            Promociones prom = new Promociones(resultados.getInt("id_promo"),resultados.getInt("monto_dcto"),resultados.getString("nombre"),resultados.getInt("estado"));
            promos.add(prom);
            
        }
     closeConnections(c,conexion,stm,resultados);
     return promos;
 }
 public ArrayList<Promociones> getPromocionesDisponibles() throws SQLException
 {
     ArrayList<Promociones> promos = new ArrayList<Promociones>();
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM Promociones;";    
     PreparedStatement stm = conexion.prepareStatement(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
        {
            if(resultados.getInt("estado")==0)
            {
                Promociones prom = new Promociones(resultados.getInt("id_promo"),resultados.getInt("monto_dcto"),resultados.getString("nombre"),resultados.getInt("estado"));
                promos.add(prom);
            }
        }
     closeConnections(c,conexion,stm,resultados);
     return promos;
 }
 public Promociones getPromocionesById(int id) throws SQLException
 {
     Promociones prom = null;
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM Promociones;";    
     PreparedStatement stm = conexion.prepareStatement(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
        {   
            if(resultados.getInt("id_promo") == id)
            {
             prom = new Promociones(resultados.getInt("id_promo"),resultados.getInt("monto_dcto"),resultados.getString("nombre"),resultados.getInt("estado"));
             return prom;
            }
        }
     closeConnections(c,conexion,stm,resultados);
     return prom;
 }
 
 
 public boolean addPromo(Promociones promo) throws SQLException
 {
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "INSERT INTO Promociones (id_promo, monto_dcto , nombre , estado) VALUES (? , ? , ? , ?); ";    
     System.out.println(query);
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,getLastId("Promociones")+1);
     stm.setInt(2,promo.getMontoDcto());
     stm.setString(3,promo.getNombre());
     stm.setInt(4,promo.getEstado());
     System.out.println("asdatemen2"+stm);
     stm.executeUpdate();
     return true;
 }
 public void deletePromo(Promociones promo) throws SQLException
 {
     
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "DELETE FROM Promociones WHERE id_promo = ?";
     System.out.println(query);
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,promo.getIdPromo());
     stm.executeUpdate();
     closeConnections(c,conexion,stm,null);
     
 }
 public boolean updatePromo(Promociones promo) throws SQLException
 {
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "UPDATE Promociones set monto_dcto = ? , nombre = ? , estado = ?  where id_promo = ?; ";    
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,promo.getMontoDcto());
     stm.setString(2,promo.getNombre());
     stm.setInt(3,promo.getEstado());
     stm.setInt(4,promo.getIdPromo());
     System.out.println(stm);

     stm.executeUpdate();
     closeConnections(c,conexion,stm,null);
     return true;
 }
}
