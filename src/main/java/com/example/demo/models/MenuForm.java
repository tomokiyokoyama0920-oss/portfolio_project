package com.example.demo.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "menu")
public class MenuForm implements Serializable {
	private static final long serialVersionUID = -6647247658748349084L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	public void setId(Long id) {
	    this.id = id;
	}

	public Long getId() {
	    return id;
	}

	@NotBlank
	@Size(max = 10)
	private String menuname;

	@NotBlank
	@Email
	private String menuaddress;

	@NotBlank
	@Size(max = 400)
	private String price;
	
	public void clear() {
		menuname = null;
		menuaddress = null;
		price = null;
	}
}
