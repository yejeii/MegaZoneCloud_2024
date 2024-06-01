package WhatIsCasting;

public class Animal {

	int age;
	String name;
	
	public Animal() {	}
	
	public Animal(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	/*
	 * Each Animal has the ablitiy to make noise.
	 * And each Sub class can override this method with their own make noise implementation if they want.
	 */
	public void makeNoise() {
		System.out.println("Hello, I am an animal");
	}
	
}
