package WhatIsStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Java Streams Part 2 - Filter Method | Filtering Collection by using Stream | Hands-On
 * https://youtu.be/DvQVdJB4Va0?si=Zs_RiwjBziy_iDBJ
 * 
 * - filter().collect()
 * - filter().forEach()
 */
public class FilterDemo2 {

	public static void main(String[] args) {

		List<Product> productsList = new ArrayList<Product>();
		productsList.add(new Product(1, "HP Laptop", 25000));
		productsList.add(new Product(2, "Dell Laptop", 30000));
		productsList.add(new Product(3, "Lenovo Laptop", 28000));
		productsList.add(new Product(4, "Sony Laptop", 23000));
		productsList.add(new Product(5, "Apple Laptop", 90000));
		
		productsList.stream()
					.filter(p->p.price>25000)
					.forEach(pr->System.out.println(pr.price));
	}

}

class Product {
	int id;
	String name;
	double price;
	
	public Product(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
}