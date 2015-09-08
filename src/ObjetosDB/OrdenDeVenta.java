package ObjetosDB;

import java.util.ArrayList;

public class OrdenDeVenta   {
    private static final long serialVersionUID = 1L;
    private Integer idOrdenVenta;
    private String fecha;
    private String montoTotal;
    private Integer numeroBoleta;
    private String medioPago;
    private Integer estadoPresupuesto;
    private Cliente cliente;
    private ArrayList<VentaProducto> ventaProducto;

    public OrdenDeVenta(int idOrdenVenta, String fecha, String montoTotal, int numeroBolta, String medioPago, int estadoPresupuesto, Cliente cliente, ArrayList<VentaProducto> ventaProducto) { //Constructor
        this.idOrdenVenta = idOrdenVenta;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.numeroBoleta = numeroBolta;
        this.medioPago = medioPago;
        this.estadoPresupuesto = estadoPresupuesto;
        this.cliente = cliente;
        this.ventaProducto = ventaProducto;
    }

    /*Setters y Getters*/
    public Integer getIdOrdenVenta() {
        return idOrdenVenta;
    }

    public void setIdOrdenVenta(Integer idOrdenVenta) {
        this.idOrdenVenta = idOrdenVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getNumeroBoleta() {
        return numeroBoleta;
    }

    public void setNumeroBoleta(Integer numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public Integer getEstadoPresupuesto() {
        return estadoPresupuesto;
    }

    public void setEstadoPresupuesto(Integer estadoPresupuesto) {
        this.estadoPresupuesto = estadoPresupuesto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
      public ArrayList<VentaProducto> getVentasProducto()
    {
        return this.ventaProducto;
    }

    /*Fin Getters y Setters*/
    
}
