/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ttars
 */
@Entity
@Table(name = "Kit_productos", catalog = "UltimoRound", schema = "")
@NamedQueries({
    @NamedQuery(name = "Kitproductos.findAll", query = "SELECT k FROM Kitproductos k"),
    @NamedQuery(name = "Kitproductos.findByIdKitProductos", query = "SELECT k FROM Kitproductos k WHERE k.idKitProductos = :idKitProductos"),
    @NamedQuery(name = "Kitproductos.findByNombreKit", query = "SELECT k FROM Kitproductos k WHERE k.nombreKit = :nombreKit"),
    @NamedQuery(name = "Kitproductos.findByPrecioCompraProductos", query = "SELECT k FROM Kitproductos k WHERE k.precioCompraProductos = :precioCompraProductos"),
    @NamedQuery(name = "Kitproductos.findByPrecioVentaKit", query = "SELECT k FROM Kitproductos k WHERE k.precioVentaKit = :precioVentaKit"),
    @NamedQuery(name = "Kitproductos.findByDescripcionKit", query = "SELECT k FROM Kitproductos k WHERE k.descripcionKit = :descripcionKit"),
    @NamedQuery(name = "Kitproductos.findByIdProducto1", query = "SELECT k FROM Kitproductos k WHERE k.idProducto1 = :idProducto1"),
    @NamedQuery(name = "Kitproductos.findByIdProducto2", query = "SELECT k FROM Kitproductos k WHERE k.idProducto2 = :idProducto2"),
    @NamedQuery(name = "Kitproductos.findByIdProducto3", query = "SELECT k FROM Kitproductos k WHERE k.idProducto3 = :idProducto3"),
    @NamedQuery(name = "Kitproductos.findByIdProducto4", query = "SELECT k FROM Kitproductos k WHERE k.idProducto4 = :idProducto4"),
    @NamedQuery(name = "Kitproductos.findByIdProducto5", query = "SELECT k FROM Kitproductos k WHERE k.idProducto5 = :idProducto5")})
public class Kitproductos implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_kit_productos")
    private Integer idKitProductos;
    @Column(name = "nombre_kit")
    private String nombreKit;
    @Column(name = "precio_compra_productos")
    private Integer precioCompraProductos;
    @Column(name = "precio_venta_kit")
    private Integer precioVentaKit;
    @Column(name = "descripcion_kit")
    private String descripcionKit;
    @Column(name = "id_producto_1")
    private Integer idProducto1;
    @Column(name = "id_producto_2")
    private Integer idProducto2;
    @Column(name = "id_producto_3")
    private Integer idProducto3;
    @Column(name = "id_producto_4")
    private Integer idProducto4;
    @Column(name = "id_producto_5")
    private Integer idProducto5;

    public Kitproductos() {
    }

    public Kitproductos(Integer idKitProductos) {
        this.idKitProductos = idKitProductos;
    }

    public Integer getIdKitProductos() {
        return idKitProductos;
    }

    public void setIdKitProductos(Integer idKitProductos) {
        Integer oldIdKitProductos = this.idKitProductos;
        this.idKitProductos = idKitProductos;
        changeSupport.firePropertyChange("idKitProductos", oldIdKitProductos, idKitProductos);
    }

    public String getNombreKit() {
        return nombreKit;
    }

    public void setNombreKit(String nombreKit) {
        String oldNombreKit = this.nombreKit;
        this.nombreKit = nombreKit;
        changeSupport.firePropertyChange("nombreKit", oldNombreKit, nombreKit);
    }

    public Integer getPrecioCompraProductos() {
        return precioCompraProductos;
    }

    public void setPrecioCompraProductos(Integer precioCompraProductos) {
        Integer oldPrecioCompraProductos = this.precioCompraProductos;
        this.precioCompraProductos = precioCompraProductos;
        changeSupport.firePropertyChange("precioCompraProductos", oldPrecioCompraProductos, precioCompraProductos);
    }

    public Integer getPrecioVentaKit() {
        return precioVentaKit;
    }

    public void setPrecioVentaKit(Integer precioVentaKit) {
        Integer oldPrecioVentaKit = this.precioVentaKit;
        this.precioVentaKit = precioVentaKit;
        changeSupport.firePropertyChange("precioVentaKit", oldPrecioVentaKit, precioVentaKit);
    }

    public String getDescripcionKit() {
        return descripcionKit;
    }

    public void setDescripcionKit(String descripcionKit) {
        String oldDescripcionKit = this.descripcionKit;
        this.descripcionKit = descripcionKit;
        changeSupport.firePropertyChange("descripcionKit", oldDescripcionKit, descripcionKit);
    }

    public Integer getIdProducto1() {
        return idProducto1;
    }

    public void setIdProducto1(Integer idProducto1) {
        Integer oldIdProducto1 = this.idProducto1;
        this.idProducto1 = idProducto1;
        changeSupport.firePropertyChange("idProducto1", oldIdProducto1, idProducto1);
    }

    public Integer getIdProducto2() {
        return idProducto2;
    }

    public void setIdProducto2(Integer idProducto2) {
        Integer oldIdProducto2 = this.idProducto2;
        this.idProducto2 = idProducto2;
        changeSupport.firePropertyChange("idProducto2", oldIdProducto2, idProducto2);
    }

    public Integer getIdProducto3() {
        return idProducto3;
    }

    public void setIdProducto3(Integer idProducto3) {
        Integer oldIdProducto3 = this.idProducto3;
        this.idProducto3 = idProducto3;
        changeSupport.firePropertyChange("idProducto3", oldIdProducto3, idProducto3);
    }

    public Integer getIdProducto4() {
        return idProducto4;
    }

    public void setIdProducto4(Integer idProducto4) {
        Integer oldIdProducto4 = this.idProducto4;
        this.idProducto4 = idProducto4;
        changeSupport.firePropertyChange("idProducto4", oldIdProducto4, idProducto4);
    }

    public Integer getIdProducto5() {
        return idProducto5;
    }

    public void setIdProducto5(Integer idProducto5) {
        Integer oldIdProducto5 = this.idProducto5;
        this.idProducto5 = idProducto5;
        changeSupport.firePropertyChange("idProducto5", oldIdProducto5, idProducto5);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKitProductos != null ? idKitProductos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kitproductos)) {
            return false;
        }
        Kitproductos other = (Kitproductos) object;
        if ((this.idKitProductos == null && other.idKitProductos != null) || (this.idKitProductos != null && !this.idKitProductos.equals(other.idKitProductos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GUI.Kitproductos[ idKitProductos=" + idKitProductos + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
