package com.app.responses;

import java.util.List;

import com.app.entities.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductBoResponse {

	private Integer totalPageNumber;
	private Long totalNumberOfElements;
	private List<Product> products;
	
}
