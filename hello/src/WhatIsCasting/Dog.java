package WhatIsCasting;

/**
 * "What is Upcasting and Downcasting"
 * https://youtu.be/HpuH7n9VOYk?si=2ir9h1IdhJEgGU3Q
 * 
 * 
 * 
 */
public class Dog extends Animal {

	@Override
	public void makeNoise() {
		System.out.println("Woof Woof");
	}

	public void growl() {
		System.out.println("Grrrrrrrrrrr");
	}
	
	
}
