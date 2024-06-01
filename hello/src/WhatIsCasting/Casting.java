package WhatIsCasting;

public class Casting {

	public static void main(String[] args) {
		
		/** 
		 * "What is Upcasting and Downcasting"
		 * https://youtu.be/HpuH7n9VOYk?si=2ir9h1IdhJEgGU3Q
		 * 
		 * 1. Up casting 
		 * Creating a new Dog Object but casting it to be an Animal.
		 * How it it possible?
		 * -> "Every Dog is an Animal". Is-A 
		 * -> Up cating : implicitly, automatically
		 * 
		 * When you do this, the 'myAnimal' variable has the type of Animal. 
		 * That's called the reference type.
		 * -> You can do anything with this 'myAnimal' variable that you could with any other Animal variable.
		 *    == 'Animal' 처럼 행동하겠다.
		 * 
		 * But since it's treated as an Animal variable, you can't do things with this variable that only apply to dogs.
		 * -> Even though underneath it's actually a Dog Object, 
		 * 	  because the 'myAnimal' variable's reference type is Animal, 
		 * 	  we don't have any access to any of the specific Dog methods or attributes.
		 *    We only have what's available in Animal.
		 * 
		 * Then why do we need upcasting even though can't use Dog's things(Only apply in Dog class)? 
		 * See doAnimalStuff() comment, and read next.
		 * 
		 * So without up casting, 
		 * you would have to take each and every class that you wanted to create a 'doAnimalStuff method for
		 * and make a seperate 'doAnimalStuff' method for each of them.
		 * -> seperate method for cats, dogs, chitahs, ...
		 * 
		 * So with up casting,
		 * you can make one method that operates on any type of Animal at all even animals that haven't been created yet.
		 * 
		 * 
		 * 2. Down casting
		 * -> Down cating : explicitly, can throw exceptions if you don't put in the right checks.
		 * See doAnimalStuff() inside comment.
		 */
		Animal myAnimal = new Dog();

		// Down casting instanceof 예시
//		Cat myAnimal = new Cat();
		
		// Can't access to Dog's methods or attributes.
//		myAnimal.growl();
		
		
		/*
		 * Print "Woof Woof".
		 * That's because myAnimal object that was being passed in was actually a Dog.
		 * And when Dog object makes noise out, it prints out "woof woof".
		 */
		doAnimalStuff(myAnimal);	// Woof Woof
		
	}
	
	/*
	 * Then why do we need upcasting even though can't use Dog's things?
	 * 
	 * This method takes an Animal.
	 * But when you call this method, 
	 * you can send an Animal object but you can also send any object that's a subtype of Animal. 
	 * -> It can be a dog, a cat, a cheetah.. whatever.
	 *    This method doesn't know and doesn't really care or need to know what type of Animal it's being called with.
	 * -> It just needs to be an Animal and any object that's a subtype of Animal is an Animal.
	 * 
	 * When combination of up casting and method overriding, 
	 * when 'doAnimalStuff' method takes its Animal object and calls the 'makeNoise' method on it,
	 * it will make the correct noise for whichever Animal object was passed in.
	 * == up casting & overriding 상황에서, 메서드는 인스턴스의 재정의된 메서드를 따라간다.
	 * 
	 * One limitation of this though is 
	 * because this method doesn't know which subtype of Animal it's working with,
	 * you can't use any of the methods or attributes that are only in those sub classes.
	 * == up casting 상황에서 매개변수로 어느 subtype이 들어올지 모르기 때문에 인스턴스에만 존재하는 변수 및 메서드는 사용하지 못한다.
	 * 
	 * 정리>
 	 * The type of the variable determines which methods you can call.
 	 * But the specific type of the object that this variable is referring to determines 
 	 * which specific implementation of that method will be used when the method is called.
 	 * -> That's why when this makesNoise method is called on a Dog, it says 'woof woof',
 	 *    and when it's called on a Cat, is says 'Meow Meow'.
	 * 
	 */
	public static void doAnimalStuff(Animal animal) {
		animal.makeNoise();
//		animal.growl();
//		animal.dogFoodPreference = "Llkist";
		
		/*
		 * How to do Down casting
		 * Telling java 
		 * 	"I know all we have is an Animal variable, but trust me. 
		 *   I know that this animal is a Dog.
		 *   So please cast it as a Dog, so I can treat it as one."
		 * Java "Ok. I'll give it a try."
		 * 
		 * -> Since we have cast this animal to a Dog, 
		 *    we can treat this 'myDog' variable just as we would any other Dog variable.
		 *    myDog 를 Dog 로 다룰 수 있게 된 것.
		 * -> Now we can call myDog.growl().
		 * 
		 * How about when 'myAnimal' object that was being passed in was actually a Cat?
		 * It will occur 'java.lang.ClassCastException'.
		 * 	"Cat cannot be cast to class WhatIsCasting.Dog".
		 * 
		 * Don't wanna explode if it's not a Dog object, what do you do?
		 * -> Add a special kind of check to see if this 'animal' object that's being passed in actually is a Dog.
		 * -> instanceof()
		 * 
		 * istanceof() check will return true if this 'animal' object that was passed in is actually a Dog,
		 * and false if it's anything else like a cat, a tiger ...
		 * Once we know that this 'animal' object actually is a Dog,
		 * we can safely cast it as a Dog and can call growl method.
		 * 
		 */
		if(animal instanceof Dog) {
			Dog myDog = (Dog)animal;
			myDog.growl();
		} else {
			System.out.println("It's not a Dog!");
		}
		
		
	}

}
