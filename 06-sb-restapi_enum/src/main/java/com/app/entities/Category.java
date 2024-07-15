package com.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	/**
	 * A Category have many Products of similar type so OneToMany Relationship from Category->to->Product
	 * 
	 * "category" is field name of Child Table Entity i.e "Product", will need to map with that
	 * */
	/**
	 * When we hit GET API for Product, here Category id fetching List<Product>, 
	 * so here we will face Circular Dependency problem because Product calling Category & Category calling Product
	 * In this type of scenario we use @JsonIgnore annotation on Parent Table's field
	 * 
	 * Product is calling Category, But when Category calling Product we need to Ignore this So used
	 * @JsonIgnore annotation on the top of this field
	 * 
	 * */
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<Product> products;
}
