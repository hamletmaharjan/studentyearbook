/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.studentyearbook.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hams
 */
@Entity
@Table(name = "table_admin", catalog = "yearbook", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableAdmin.findAll", query = "SELECT t FROM TableAdmin t")
    , @NamedQuery(name = "TableAdmin.findById", query = "SELECT t FROM TableAdmin t WHERE t.id = :id")
    , @NamedQuery(name = "TableAdmin.findByFirstName", query = "SELECT t FROM TableAdmin t WHERE t.firstName = :firstName")
    , @NamedQuery(name = "TableAdmin.findByLastName", query = "SELECT t FROM TableAdmin t WHERE t.lastName = :lastName")
    , @NamedQuery(name = "TableAdmin.findByEmail", query = "SELECT t FROM TableAdmin t WHERE t.email = :email")
    , @NamedQuery(name = "TableAdmin.findByPassword", query = "SELECT t FROM TableAdmin t WHERE t.password = :password")
    , @NamedQuery(name = "TableAdmin.findByContact", query = "SELECT t FROM TableAdmin t WHERE t.contact = :contact")
    , @NamedQuery(name = "TableAdmin.findByImage", query = "SELECT t FROM TableAdmin t WHERE t.image = :image")
    , @NamedQuery(name = "TableAdmin.findByRole", query = "SELECT t FROM TableAdmin t WHERE t.role = :role")
    , @NamedQuery(name = "TableAdmin.findByStatus", query = "SELECT t FROM TableAdmin t WHERE t.status = :status")})
public class TableAdmin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "contact")
    private String contact;
    @Column(name = "image")
    private String image;
    @Column(name = "role")
    private String role;
    @Column(name = "status")
    private String status;

    public TableAdmin() {
    }

    public TableAdmin(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof TableAdmin)) {
            return false;
        }
        TableAdmin other = (TableAdmin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kist_bit.studentyearbook.entity.TableAdmin[ id=" + id + " ]";
    }
    
}
