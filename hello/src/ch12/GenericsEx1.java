package ch12;

import java.util.ArrayList;

/**
 * ===================== Generics ======================
 * 
 * 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일 시 타입 체크를 해주는 기능
 * 
 * ex. ArrayList
 * - 끄집어 낼 때
 * 	 down casting이 되는지 안되는지가 고려사항.
 * 	 casting 전에 타입체크가 필요 (instanceof 연산자)
 * 
 * - 저장될 때
 * 	 원하지 않는 객체가 포함되지는 않을까 하는 고려사항.
 * 
 * 상기의 고려사항을 해결하면 Collection을 사용하는데 부담이 없어짐. : Generics
 * 
 * - Generics 장점
 *   - 타입 안전성을 제공
 *   - 타입체크와 형변환을 생략가능 -> 코드가 심플해짐
 * 
 * - Generics 사용
 *   class Box {
 * 		Object item;
 * 		
 * 		void setItem(Object item) {
 * 			this.item = item;
 * 		}
 * 
 * 		Object getItem() { return this.item; }
 *   } 
 * 
 *   ->
 * 
 *   class Box<T> {
 * 		T item;
 * 		
 * 		void setItem(T item) {
 * 			this.item = item;
 * 		}
 * 
 * 		T getItem() { return this.item; }
 *   }
 * 
 * 
 *   용어 정리
 * 		- Box<T> : Generic 클래스
 * 		- T		: 타입 변수
 * 		- Box	: 원시 타입
 * 
 *   인스턴스 생성
 * 	 Box<String> b = new Box<String>();
 * 		- String : 대입된 타입
 * 		- Box<String> : Generics 타입의 클래스 호출
 * 
 *   Box<Integer> b = new Box<Integer>();
 * 
 * - Generics 사용 제한
 *   - static 멤버에 타입 변수 T 사용 불가
 *     static 멤버는 인스턴스 멤버 X -> 인스턴스 생성과 무관
 *     하지만, 타입 변수 T는 인스턴스임 -> 컴파일 시점에 타입이 지정되므로 인스턴스 변수로 간주됨.
 *     
 *     class Box<T> {
 *     	// class 멤버
 *     	static T item;	// ERROR
 *     }
 *   
 *   - Generics 타입의 배열 생성 불가
 *     -> 컴파일 시점에 타입이 지정되므로 ? 클래스로 인스턴스 생성 불가
 *     T[] tmpArr = new T[100]; // ERROR
 *  
 *   -> instanceof 연산자, new 연산자에서 타입 변수 T를 피연산자로 사용 불가
 *   
 * - Generics 란?
 *   인스턴스 별로 원하는 타입을 지정해서 사용하므로 
 *   인스턴스 별로 다르게 동작되도록 만들려고 하는 자바의 기능!
 * 
 */
public class GenericsEx1 {
	
	public static void main(String[] args) {
		
		/* 
		 * Box class 
		 * 
		 * Box에 담을 수 있는 타입
		 * Fruit - Apple
		 * Fruit - Grape
		 * 
		 * Box에 담을 수 있는 타입 지정 : Box<Fruit>, Box<Apple>, Box<Grape>
		 * - Fruit Box 에 담을 수 있는 대상 : Fruit, Apple, Grape
		 * - Apple Box 에 담을 수 있는 대상 : Apple
		 * - Grape Box 에 담을 수 있는 대상 : Grape
		 * 
		 * 
		 * */
		
		Box<Fruit> fruitBox = new Box<>();	// Fruit 전용 박스
		Box<Apple> appleBox = new Box<>();	// Apple 전용 박스
		Box<Grape> grapeBox = new Box<>();	// Grape 전용 박스
		Box<Toy> toyBox = new Box<>();		// Toy 전용 박스
		
		// 원치 않는 타입이 저장되지 않도록 타입체크를 해서 알려줌
//		Box<Fruit> fruitBox2 = new Box<Apple>();	
//		Box<Toy> toyBox2 = new Box<Apple>();		
		
		// add() Fruit 타입으로 다형성 매개변수 적용
		fruitBox.add(new Fruit());
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());

//		fruitBox.add(new Toy());
		
		Fruit fruit = new Apple();
		System.out.println(fruit);
		Apple apple = (Apple)fruit;
		
		appleBox.add(new Apple());
//		appleBox.add(new Grape());
//		appleBox.add(new Fruit());
		appleBox.add(apple);
		
		System.out.println(fruitBox);
		System.out.println(appleBox);
		
		
	}

}


/* Box 클래스 생성 */
class Box<T> {
	
	ArrayList<T> list = new ArrayList<>();
	void add(T item) { list.add(item); }
	T get(int i) { return list.get(i); }
	int size() { return list.size(); }
	public String toString() { return list.toString(); }
}

/* 과일 클래스 생성 */
class Fruit {
	public String toString() { return "Fruit"; }
}

class Apple extends Fruit {
	public String toString() { return "Apple"; }
}

class Grape extends Fruit {
	public String toString() { return "Grape"; }
}

class Toy {
	public String toString() { return "Toy"; }
}









