package com.app.entities;

import java.util.List;

import com.app.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private Integer quantity;
	private String description;
	
	/**
	 * below given two fields we have give input during Product creation in Service class, above 4 fields
	 * name,price,quantity, description will give by as User Input
	 * */
	private Boolean isStock;
	private String barCode;
	
	
	/**
	 * Many Products belongs to 1 Category so ManyToOne Relationship from Product->to->Category
	 * During Product creation we need to pass Product ID here in JSON input
	 * */ 
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	
	@OneToMany(mappedBy="product")
	@JsonIgnore
	private List<Review> reviews;
	
	@Enumerated(EnumType.STRING)
	private ProductStatus productStatus;
}
