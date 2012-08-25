package com.sysquare.malert.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author pradeep
 */
@Entity
@Table(name = "workshop")
public class Workshop implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "tsm_name")
    private String tsmName;
    
    @Column(name = "tsm_email")
    private String tsmEmail;
    
    @Column(name = "active")
    private Boolean active;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Transient
    private List<String> warnings = new ArrayList<String>();
    
    public Workshop() {
    }

    public Workshop(Integer id) {
        this.id = id;
    }

    public Workshop(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name != null ? name.trim() : name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code != null ? code.trim() : code;
    }

    public String getTsmName() {
        return tsmName;
    }

    public void setTsmName(String tsmName) {
        this.tsmName = tsmName != null ? tsmName.trim() : tsmName;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTsmEmail() {
        return tsmEmail;
    }

    public void setTsmEmail(String tsmEmail) {
        this.tsmEmail = tsmEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Workshop)) {
            return false;
        }
        Workshop other = (Workshop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
