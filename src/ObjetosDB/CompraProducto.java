/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosDB;


public class CompraProducto  {
    private static final long serialVersionUID = 1L;

    private Integer idCompraProducto;
    private String fecha;
    private Integer cantidad;
    private Integer montoUnitario;
    private Integer montoTotal;
    private String proveedor;
    private OrdenDeCompra OrdenDeCompra;
    private Productos Productos;

    //Constructor
    public CompraProducto(Integer idCompraProducto, String fecha, int cantidad, int montoUnitario, int montoTotal, String proveedor, OrdenDeCompra ordenDeCompra, Productos productos) {
        this.idCompraProducto = idCompraProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.montoUnitario = montoUnitario;
        this.montoTotal = montoTotal;
        this.proveedor = proveedor;
        this.OrdenDeCompra = ordenDeCompra;
        this.Productos = productos;
    }

    /*Setters y Getters*/
    public Integer getIdCompraProducto() {
        return idCompraProducto;
    }

    public void setIdCompraProducto(Integer idCompraProducto) {
        this.idCompraProducto = idCompraProducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getMontoUnitario() {
        return montoUnitario;
    }

    public void setMontoUnitario(Integer montoUnitario) {
        this.montoUnitario = montoUnitario;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public OrdenDeCompra getOrdenDeCompra() {
        return this.OrdenDeCompra;
    }

    public void setOrdenDeCompra(OrdenDeCompra idOrdenDeCompra) {
        this.OrdenDeCompra = idOrdenDeCompra;
    }

    public Productos getProductos() {
        return Productos;
    }

    public void setProducto(Productos Producto) {
        this.Productos = Producto;
    }
    /*Fin setters y Getters*/
    
}
