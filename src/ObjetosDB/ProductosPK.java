//Tengo que cachar que chucha hace esta clase, NO USAR
package ObjetosDB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ttars
 */
@Embeddable
public class ProductosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_producto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "codigo_barra")
    private String codigoBarra;

    public ProductosPK() {
    }

    public ProductosPK(int idProducto, String codigoBarra) {
        this.idProducto = idProducto;
        this.codigoBarra = codigoBarra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProducto;
        hash += (codigoBarra != null ? codigoBarra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosPK)) {
            return false;
        }
        ProductosPK other = (ProductosPK) object;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if ((this.codigoBarra == null && other.codigoBarra != null) || (this.codigoBarra != null && !this.codigoBarra.equals(other.codigoBarra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ObjetosDB.ProductosPK[ idProducto=" + idProducto + ", codigoBarra=" + codigoBarra + " ]";
    }
    
}
