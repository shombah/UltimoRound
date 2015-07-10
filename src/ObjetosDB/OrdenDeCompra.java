
package ObjetosDB;

import Clases.DB_connection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdenDeCompra extends metodosDB {
    private static final long serialVersionUID = 1L;
   
    private Integer idOrdenCompra;
    private String fecha;
    private Integer montoTotal;
    private Integer numeroFacturaRecibida;
    private String proveedor;
    private ArrayList<CompraProducto> comprasProducto;
    public OrdenDeCompra(int idOrdenCompra, String fecha, int montoTotal, int numeroFacturaRecibida, String proveedor, ArrayList<CompraProducto> comprasProducto) 
    { //Constructor
        this.idOrdenCompra = idOrdenCompra;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.numeroFacturaRecibida = numeroFacturaRecibida;
        this.proveedor = proveedor;
        this.comprasProducto = comprasProducto;
    }
    public OrdenDeCompra(){}

    /*Getters y Setters*/

    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getNumeroFacturaRecibida() {
        return numeroFacturaRecibida;
    }

    public void setNumeroFacturaRecibida(Integer numeroFacturaRecibida) {
        this.numeroFacturaRecibida = numeroFacturaRecibida;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    /*Fin Getters y Setters*/
    
    
   
    
}
