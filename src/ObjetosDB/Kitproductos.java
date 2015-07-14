package ObjetosDB;

public class Kitproductos {
    private static final long serialVersionUID = 1L;
    
    private Integer idKitProductos;
    private String nombreKit;
    private Integer precioCompraProductos;
    private Integer precioVentaKit;
    private String descripcionKit;
    private Integer idProducto1;
    private Integer idProducto2;
    private Integer idProducto3;
    private Integer idProducto4;
    private Integer idProducto5;

    public Kitproductos() { //Constructor
    }

    /*Setters y Getters*/
    public Integer getIdKitProductos() {
        return idKitProductos;
    }

    public void setIdKitProductos(Integer idKitProductos) {
        this.idKitProductos = idKitProductos;
    }

    public String getNombreKit() {
        return nombreKit;
    }

    public void setNombreKit(String nombreKit) {
        this.nombreKit = nombreKit;
    }

    public Integer getPrecioCompraProductos() {
        return precioCompraProductos;
    }

    public void setPrecioCompraProductos(Integer precioCompraProductos) {
        this.precioCompraProductos = precioCompraProductos;
    }

    public Integer getPrecioVentaKit() {
        return precioVentaKit;
    }

    public void setPrecioVentaKit(Integer precioVentaKit) {
        this.precioVentaKit = precioVentaKit;
    }

    public String getDescripcionKit() {
        return descripcionKit;
    }

    public void setDescripcionKit(String descripcionKit) {
        this.descripcionKit = descripcionKit;
    }

    public Integer getIdProducto1() {
        return idProducto1;
    }

    public void setIdProducto1(Integer idProducto1) {
        this.idProducto1 = idProducto1;
    }

    public Integer getIdProducto2() {
        return idProducto2;
    }

    public void setIdProducto2(Integer idProducto2) {
        this.idProducto2 = idProducto2;
    }

    public Integer getIdProducto3() {
        return idProducto3;
    }

    public void setIdProducto3(Integer idProducto3) {
        this.idProducto3 = idProducto3;
    }

    public Integer getIdProducto4() {
        return idProducto4;
    }

    public void setIdProducto4(Integer idProducto4) {
        this.idProducto4 = idProducto4;
    }

    public Integer getIdProducto5() {
        return idProducto5;
    }

    public void setIdProducto5(Integer idProducto5) {
        this.idProducto5 = idProducto5;
    }
    public Kitproductos getKitById(int id)
    { //implementar
        return null;
    }
    /*Fin Setters y Getters*/
    
}
