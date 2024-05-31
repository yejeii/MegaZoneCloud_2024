package WhatIsCasting;

public class Casting {

	public static void main(String[] args) {
		
		/*
		 * Creating a new Dog Object but casting it to be an Animal.
		 * How it it possible?
		 * -> "Every Dog is an Animal". Is-A 
		 * -> Upcating : implicitly, automatically
		 * 
		 * When you do this, the 'myAnimal' here has the type of Animal. 
		 * That's called the reference type.
		 * -> You can do anything with this 'myAnimal' that you could with any other Animal variable.
		 * 
		 * But since it's treated as an Animal variable, you can't do things with this variable that only apply to dogs.
		 * -> Even though underneath it's actually a Dog Object, 
		 * 	  because the 'myAnimal' variable's reference type is Animal, 
		 * 	  we don't have any access to any of the specific Dog methods or attributes.
		 * We only have what's available in Animal.
		 * 
		 * Then why do we need upcasting even though can't use Dog's things?
		 */
		Animal myAnimal = new Dog();
		
		// Can't access to Dog's methods or attributes.
//		myAnimal.growl();
		
		myAnimal.makeNoise();
		
	}
	
	/*
	 * Then why do we need upcasting even though can't use Dog's things?
	 * 
	 * 
	 */
	public static void doAnimalStuff(Animal animal) {
		animal.makeNoise();
	}

}
