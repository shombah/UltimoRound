package ObjetosDB;

public class VentaProducto   {
    private static final long serialVersionUID = 1L;
    
    private Integer idVentaProducto;
    private String fecha;
    private Integer precioUnitarioNeto;
    private Integer cantidadProducto;
    private Integer precioUnitarioFinal;
    private Integer precioTotalNeto;
    private Integer precioTotalFinal;
    private Integer descuento;
    private Kitproductos idKitProducto;
    private Integer idOrdenDeVenta;

    public VentaProducto() { //Constructor
    }

    /*Setters y Getters*/
    public Integer getIdVentaProducto() {
        return idVentaProducto;
    }

    public void setIdVentaProducto(Integer idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getPrecioUnitarioNeto() {
        return precioUnitarioNeto;
    }

    public void setPrecioUnitarioNeto(Integer precioUnitarioNeto) {
        this.precioUnitarioNeto = precioUnitarioNeto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getPrecioUnitarioFinal() {
        return precioUnitarioFinal;
    }

    public void setPrecioUnitarioFinal(Integer precioUnitarioFinal) {
        this.precioUnitarioFinal = precioUnitarioFinal;
    }

    public Integer getPrecioTotalNeto() {
        return precioTotalNeto;
    }

    public void setPrecioTotalNeto(Integer precioTotalNeto) {
        this.precioTotalNeto = precioTotalNeto;
    }

    public Integer getPrecioTotalFinal() {
        return precioTotalFinal;
    }

    public void setPrecioTotalFinal(Integer precioTotalFinal) {
        this.precioTotalFinal = precioTotalFinal;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Kitproductos getIdKitProducto() {
        return idKitProducto;
    }

    public void setIdKitProducto(Kitproductos idKitProducto) {
        this.idKitProducto = idKitProducto;
    }

    public int getIdOrdenDeVenta() {
        return idOrdenDeVenta;
    }

    public void setIdOrdenDeVenta(int idOrdenDeVenta) {
        this.idOrdenDeVenta = idOrdenDeVenta;
    }

    /*Fin Setters y Getters*/
}
