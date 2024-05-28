package WhatIsSuper;

public class SuperDuper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cat myCat = new Cat(3, "George", "Purina One");
		System.out.printf("age : %d, name : %s, catFood : %s \n", myCat.age, myCat.name, myCat.catFoodPreference);
		myCat.makeNoise();
		
		/* We aren't override eat() in Cat class, but since Cat is a subclass of the Animal class, 
		 * it automatically gets this implementation of the eat() method.
		 * -> Can still call myCat.eat()
		 */
		myCat.eat();
		
	}

}
