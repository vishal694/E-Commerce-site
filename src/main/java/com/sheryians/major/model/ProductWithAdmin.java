package com.sheryians.major.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "ProductWithAdmin")
public class ProductWithAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private double price;

	private double weight;

	private String description;

	private String imageName;
	
	@Column(nullable = false, unique = true)
	@NotEmpty
	@Email(message = "{errors.Invalid_}")
	private String email;
}
