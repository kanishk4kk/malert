package com.sysquare.malert.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	
	@Column(name="fname")
	private String fname;

	@Column(name="lname")
	private String lname;

	@Column(name="email")
	private String email;

	@Column(name="password")
	private String password;

	@Column(name="type")
	private String type;

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
	List<String> warnings = new ArrayList<String>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname != null ? fname.trim() : fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname != null ? lname.trim() : lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email != null ? email.trim() : email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password != null ? password.trim() : password;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type != null ? type.trim() : type;
    }

    public List<String> getWarnings() {
        return warnings;
    }
    
    public String getFullName() {
        String fullName = this.getFname();
        if(this.getLname() != null && !this.getLname().isEmpty()) {
            fullName =  fullName + " " + this.getLname();
        }
        return fullName;
    }
    
    public boolean isAdminUser() {
        return getType() != null && getType().equals("admin");
    }
}
