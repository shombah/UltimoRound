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
    private Cliente idCliente;
    private ArrayList<VentaProducto> ventaProducto;

    public OrdenDeVenta() { //Constructor
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

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    /*Fin Getters y Setters*/
    
}
