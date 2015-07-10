
package ObjetosDB;

import java.util.ArrayList;

public class OrdenDeCompra   {
    private static final long serialVersionUID = 1L;
   
    private Integer idOrdenCompra;
    private String fecha;
    private Integer montoTotal;
    private Integer numeroFacturaRecibida;
    private String proveedor;
    private ArrayList<CompraProducto> comprasProducto;
    public OrdenDeCompra() { //Constructor
    }

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
    
    
    public OrdenDeCompra getOrdenDeCompraById(int id_buscada) //Implementar
    {
    /*Retorna un objeto OrdenDeCompra desde la base de datos, segun la id dada*/
    return null;
    }
}
