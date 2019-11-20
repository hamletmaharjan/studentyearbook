/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.studentyearbook.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hams
 */
@Entity
@Table(name = "table_event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableEvent.findAll", query = "SELECT t FROM TableEvent t")
    , @NamedQuery(name = "TableEvent.findById", query = "SELECT t FROM TableEvent t WHERE t.id = :id")
    , @NamedQuery(name = "TableEvent.findByTitle", query = "SELECT t FROM TableEvent t WHERE t.title = :title")
    , @NamedQuery(name = "TableEvent.findByDate", query = "SELECT t FROM TableEvent t WHERE t.date = :date")})
public class TableEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public TableEvent() {
    }

    public TableEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof TableEvent)) {
            return false;
        }
        TableEvent other = (TableEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kist_bit.studentyearbook.entity.TableEvent[ id=" + id + " ]";
    }
    
}
