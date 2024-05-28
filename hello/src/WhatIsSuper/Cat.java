package WhatIsSuper;

/**
 * "What is super"
 * https://youtu.be/Qb_NUn0TSAU?si=jdu6HCG95plTX0ib
 * 
 * Animal is a super class of Cat class.
 * So, within the Cat class, I'll use the 'super' keyword to access things in the Animal class.
 * 
 * -- 클래스에서 'super'를 사용하는 2가지 방법
 *	1. Call methods in the super class that I had overridden in the child class.
 *	   Cat class which extends Animal would probably make noise in its own specific way.
 *	   -> We might do in our Cat class is override the makeNoise().
 *     -> Wanna able to call the parent Animal classes version of the makeNoise(), use 'super' keyword.
 *  
 *  2. Call the parent classes constructors to avoid code duplication.
 *     
 *     +Plus+
 *     Very beginning of every constructor method, if you don't have any call super constructor, 
 *     JAVA will actually call the parent classes no args constructor implicitly and automatically
 *     without you having any code.
 *     Notice that JAVA add 'no args' constructor, 
 *     if you wanna use any of the args constructors, you have to explicitly call it like we did here. 
 *    
 *  ** 'super' 사용시 주의점
 *  1. Only can be used inside a class to refer to that class's super class.
 *  2. Can't use the 'super' keyword to access any private methods or fields in the parent class.
 *     'private' still only going to be accessible to that parent class and not anywhere else 
 *     even if you do use 'super' keyword.
 *  3. Calling the super class's constructors can only be used inside the subclass constructors,
 *     and has to be the very first line of that constructor.
 */
public class Cat extends Animal{

	String catFoodPreference;
	
	/*
	 * Make constructor can take in this Cat's name, age, and catFoodPreference.
	 * After coding, there's kind of a little bit of code duplication happening here.
	 * We're setting age and name, but in our Animal class we already have a constructor that's doing that.
	 * So it would be nice if we could use this Animal constructor for setting the first two values on our Cat,
	 * and then we just need to set the catFoodPreference.
	 * -> super() allows you to do exactly that. 
	 * 
	 * super() call the parent classes from here.
	 * -> Animal constructor will take care of setting int age, String name, 
	 * 	  and we only have to worry about setting additional values that are specific to cats, 'catFoodPreference'.
	 */
	public Cat(int age, String name, String catFoodPreference) {
//		this.age = age;
//		this.name = name;
		super(age, name);
		this.catFoodPreference = catFoodPreference;
	}
	
	@Override
	public void makeNoise() {
		// Wanna call the parent Animal classes version
		super.makeNoise();
		System.out.println("Meow Meow Meow");
		
		/*
		 * Would call Animal classes implementation of the eat().
		 * But in this situation we don't really have to use 'super' keyword, 
		 * we can just call eat();.
		 * Because our Cat class isn't overriding super class's implementation of the eat(),
		 * by default just calling eat() will get us the Animal's implementation of the eat().
		 */
//		super.eat();
		eat();
		
		/*
		 * 주의점 2. The method doSomethingPrivate() from the type Animal is not visible
		 */
//		super.doSomethingPrivate();
	}
	
}
