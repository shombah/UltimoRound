
package ObjetosDB;

public class Productos   {
    private static final long serialVersionUID = 1L;
    protected ProductosPK productosPK;
    private String nombre;
    private String marca;
    private String talla;
    private String color;
    private Integer precioCompra;
    private Integer precioVenta;
    private String proveedor;
    private Integer cantidadActual;

    public Productos() { //Constructor
    }

    /*Setters y Getters*/
    public Productos(int idProducto, String codigoBarra) {
        this.productosPK = new ProductosPK(idProducto, codigoBarra);
    }

    public ProductosPK getProductosPK() {
        return productosPK;
    }

    public void setProductosPK(ProductosPK productosPK) {
        this.productosPK = productosPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Integer precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
/*Fin Setters y Getters*/
   
    public Productos getProductoById(int id_buscada)//Implementar
    {
    /*Retorna un objeto productos desde la base de datos, segun la id dada*/
    return null;
    }
    
}
