/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalexam.finalexam;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "biodata")
@NamedQueries({
    @NamedQuery(name = "Biodata.findAll", query = "SELECT b FROM Biodata b"),
    @NamedQuery(name = "Biodata.findById", query = "SELECT b FROM Biodata b WHERE b.id = :id"),
    @NamedQuery(name = "Biodata.findByNik", query = "SELECT b FROM Biodata b WHERE b.nik = :nik"),
    @NamedQuery(name = "Biodata.findByNama", query = "SELECT b FROM Biodata b WHERE b.nama = :nama"),
    @NamedQuery(name = "Biodata.findByTanggalLahir", query = "SELECT b FROM Biodata b WHERE b.tanggalLahir = :tanggalLahir"),
    @NamedQuery(name = "Biodata.findByTimestamp", query = "SELECT b FROM Biodata b WHERE b.timestamp = :timestamp")})
public class Biodata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "NIK")
    private Integer nik;
    @Column(name = "nama")
    private String nama;
    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @Lob
    @Column(name = "Photo")
    private byte[] photo;
    @Basic(optional = false)
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Biodata() {
    }

    public Biodata(Integer id) {
        this.id = id;
    }

    public Biodata(Integer id, Date timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNik() {
        return nik;
    }

    public void setNik(Integer nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Biodata)) {
            return false;
        }
        Biodata other = (Biodata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalexam.finalexam.Biodata[ id=" + id + " ]";
    }
    
}
