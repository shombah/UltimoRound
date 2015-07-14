/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ttars
 */
@Entity
@Table(name = "Productos", catalog = "UltimoRound", schema = "")
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdProducto", query = "SELECT p FROM Productos p WHERE p.productosPK.idProducto = :idProducto"),
    @NamedQuery(name = "Productos.findByCodigoBarra", query = "SELECT p FROM Productos p WHERE p.productosPK.codigoBarra = :codigoBarra"),
    @NamedQuery(name = "Productos.findByNombre", query = "SELECT p FROM Productos p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Productos.findByMarca", query = "SELECT p FROM Productos p WHERE p.marca = :marca"),
    @NamedQuery(name = "Productos.findByTalla", query = "SELECT p FROM Productos p WHERE p.talla = :talla"),
    @NamedQuery(name = "Productos.findByColor", query = "SELECT p FROM Productos p WHERE p.color = :color"),
    @NamedQuery(name = "Productos.findByPrecioCompra", query = "SELECT p FROM Productos p WHERE p.precioCompra = :precioCompra"),
    @NamedQuery(name = "Productos.findByPrecioVenta", query = "SELECT p FROM Productos p WHERE p.precioVenta = :precioVenta"),
    @NamedQuery(name = "Productos.findByProveedor", query = "SELECT p FROM Productos p WHERE p.proveedor = :proveedor"),
    @NamedQuery(name = "Productos.findByCantidadActual", query = "SELECT p FROM Productos p WHERE p.cantidadActual = :cantidadActual")})
public class Productos implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductosPK productosPK;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "marca")
    private String marca;
    @Column(name = "talla")
    private String talla;
    @Column(name = "color")
    private String color;
    @Column(name = "precio_compra")
    private Integer precioCompra;
    @Column(name = "precio_venta")
    private Integer precioVenta;
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "cantidad_actual")
    private Integer cantidadActual;

    public Productos() {
    }

    public Productos(ProductosPK productosPK) {
        this.productosPK = productosPK;
    }

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
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        String oldMarca = this.marca;
        this.marca = marca;
        changeSupport.firePropertyChange("marca", oldMarca, marca);
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        String oldTalla = this.talla;
        this.talla = talla;
        changeSupport.firePropertyChange("talla", oldTalla, talla);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        String oldColor = this.color;
        this.color = color;
        changeSupport.firePropertyChange("color", oldColor, color);
    }

    public Integer getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Integer precioCompra) {
        Integer oldPrecioCompra = this.precioCompra;
        this.precioCompra = precioCompra;
        changeSupport.firePropertyChange("precioCompra", oldPrecioCompra, precioCompra);
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        Integer oldPrecioVenta = this.precioVenta;
        this.precioVenta = precioVenta;
        changeSupport.firePropertyChange("precioVenta", oldPrecioVenta, precioVenta);
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        String oldProveedor = this.proveedor;
        this.proveedor = proveedor;
        changeSupport.firePropertyChange("proveedor", oldProveedor, proveedor);
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        Integer oldCantidadActual = this.cantidadActual;
        this.cantidadActual = cantidadActual;
        changeSupport.firePropertyChange("cantidadActual", oldCantidadActual, cantidadActual);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productosPK != null ? productosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.productosPK == null && other.productosPK != null) || (this.productosPK != null && !this.productosPK.equals(other.productosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GUI.Productos[ productosPK=" + productosPK + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
