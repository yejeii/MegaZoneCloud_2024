package ch12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * ============= 제한된 Generic 클래스 - Generic 에 다형성 적용 ===================
 * 
 * 1. 클래스 관계에 대한 제한 
 * 
 * Box<T> b = new Box<>();
 * 
 * T 대신에 어떤것이라도 올 수 있음. Apple, Grape, Toy 등..
 * 제한을 두고 싶은데, 클래스 간의 관계를 통해서 제한을 둘 수 있음
 * 
 * Object
 * Fruit
 * Apple
 * 
 * 현재는 타입변수 T에 하나만 올 수 있고, 뭐든지 올 수 있음
 * Fruit 를 상속한 것들만 T 에 올 수 있도록 하고 싶다.. : 관계의 제한
 * 
 * -- 타입변수 T 에 뭐든지 올 수 있는 경우
 * class FruitBox<T> {
 * 	ArrayList<T> list = new ArrayList<>();
 * }
 * 
 * -- Fruit 를 상속한 것들만 T 에 오도록
 *    (T 에 어떤 것이 올지는 모르겠으나, Fruit 를 상속한 클래스만 오게 됨 -> 상속 제한)
 * class FruitBox<T extends Fruit> {
 * 	
 *  <컴파일 전>
 *  ArrayList<T> list = new ArrayList<T>();
 *  
 *  <컴파일 후>
 *  ArrayList<Fruit> list = new ArrayList<Fruit>();
 * }
 * 
 * <- : 다형성 :: 완젼 편행
 *
 * class FruitBox<Apple> {
 * 	ArrayList<T> list = new ArrayList<T>();
 * }
 * class FruitBox<Grape> {
 * 	ArrayList<T> list = new ArrayList<T>();
 * }
 * class FruitBox<Fruit> {
 * 	ArrayList<T> list = new ArrayList<T>();
 * }
 * 
 * 
 * 2. 인터페이스 구현에 대한 제한
 * 
 * T extends Fruit
 * T 에 오는 실제 타입 클래스에 인터페이스 구현을 강제시키고 싶음
 * 
 * - 인터페이스 작성시 구성 요소
 *   상수, 추상 메서드(강제성), 디폴트 메서드(강제성 X)
 * 
 * Fruit, Apple, Grape -> 피동의 대상
 * -> 공통적인 요소인 '먹다'를 추상메서드로 강제성을 부여하고 싶음
 * 
 * interface Eatable { }
 * 
 * T extends Fruit & Eatable
 * - Fruit 자신과 Fruit 를 상속한 것만 올 수 있도록 제한
 * 		&
 * - Eatable interface 를 구현한 것만 올 수 있도록 제한
 * 
 * 주의사항>
 * 클래스에서 interface 구현시 implements 키워드를 사용하지만,
 * Generics 에서는 implements 를 사용하지 않고 extends 를 사용함!
 * 
 * T extends Fruit : Fruit 자신과 Fruit 를 상속 받은 것만
 * T extends Eatable : Eatable interface 를 구현한 것만
 * 
 * 
 */
public class GenericsEx2 {

	public static void main(String[] args) {

		/* ------------- 1. 클래스 관계에 대한 제한 ---------------- */
		
		// Fruit2 box
		FruitBox<Fruit2> fruitBox = new FruitBox();
		/*
		 * <- 컴파일 전 : 어떤 타입으로도 사용 가능
		 * ArrayList<T> list = new ArrayList<T>();
		 * 
		 * -> 컴파일 후 : Apple, Grape 타입만 사용 가능
		 * ArrayList<Fruit2> list = new ArrayList<Fruit2>();
		 * 
		 */
		
		/* 
		 * Apple2 box & Grape2 box 
		 * -> 다형성 적용 X
		 *    파생 클래스 타입을 지정했으므로, 다형성을 활용하고 있지 못하는 상태
		 */
		FruitBox<Apple2> appleBox = new FruitBox();
		 
		FruitBox<Grape2> grapeBox = new FruitBox();
		
		/*
		 * 각 Box에 과일 담기
		 * Generic 제한이 다형성으로 적용되도록 했으므로 과일을 담을 때, 다형성이 적용되도록 해야 함
		 */
		Fruit2 apple = new Apple2();
		Fruit2 grape = new Grape2();
		
		fruitBox.add(apple);
		fruitBox.add(grape);
		
		appleBox.add(new Apple2());
		grapeBox.add(new Grape2());

		appleBox.add((Apple2) apple);	// Generic 은 클래스 타입을 제한 -> down casting
		grapeBox.add((Grape2) grape);
		
		
		/* ------------- 2. 인터페이스 구현에 대한 제한 ---------------- */
		
		/*
		 * 2-1. Eatable 을 기반쪽에 구현하지 않는 경우
		 * 		-> 모두 ERROR
		 * 2-2. Eatable 을 파생족에만 구현한 경우
		 * 		-> 기반에만 ERROR
		 * 2-3. Eatable 을 기반쪽에 구현한 경우
		 * 		-> 모두 정상
		 * 
		 * 2-4. 
		 * 
		 */		
		
		/*
		 * >구현해보기<
		 * 
		 * 1. 프로그램에서 사용중인 Collection 에서 Iterator 구하기
		 * 2. 표준 API 이용해서 overriding 된 toString() 사용
		 * 3. Fruit2 의 eat2() 사용
		 * 
		 * 4. 3번이 왜 안되는지 생각해보기.
		 *    왜 안되는지를 OOP 4대 특성과 연결지어서 생각해 볼 것.  
		 */
		// 1. 
		Iterator<Fruit2> it = fruitBox.list.iterator();
//		Iterator it = fruitBox.list.iterator();
		
		// 2.
		while(it.hasNext()) {
			
			// 3.
//			if(it.next() instanceof Fruit2) {
//				System.out.println(it.next().toString());	// Object <- Fruit2 <- Apple2
//				it.next().eat2(); // Apple2! 까지만 출력되고 while 종료.
//			}

			// 형변환 한 후(up casting)
			Fruit2 fruits = it.next();
			System.out.println(fruits.toString());
			
			System.out.println(fruits.eat2());
			/* 현재 interface 에 구현된 쪽은 기반 클래스만 되어 있음
			 * 자식의 eat2() 를 해도 기반쪽의 메서드가 호출됨
			 * 
			 * 만약 파생 클래스에 eat2() 를 모두 구현하면?
			 * -> 다형성 관계에서 파생 클래스의 메서드 접근은 인스턴스 타입을 따라간다.
			 * -> 다운 캐스팅 필요
			 */
			
			
		}
		
		/* ------------- 와일드 카드 : 하한 제한 --------------- */
		
		System.out.println(Juicer.makeJuice(fruitBox));
//		Juicer.makeJuice(appleBox); ERROR
		
		
		
	}// end of main
	
}

/**
 * Box class : row type class -> Generic 클래스 적용
 */
class Box2<T> {
	ArrayList<T> list = new ArrayList<>();
	
	void add(T item) { list.add(item); }
	T get(int i) { return list.get(i); }
	public ArrayList<T> getList() { return list; }

	int size() { return list.size(); }
	public String toString() { return list.toString(); }
}

/**
 * 과일 Box : 클래스 간의 관계 제한 -> Generic 활용
 * 
 * Fruit2 또는 파생 클래스는 반드시 Eatable 을 구현해야 함
 * Fruit2 에서 implements Eatable 을 작성하지 않으면 컴파일 에러 발생
 * -> 강제 구현 유도
 */
class FruitBox<T extends Fruit2 & Eatable> extends Box2<T> {

}

/**
 * Generic 제한용 인터페이스
 */
interface Eatable {
	abstract void eat();
	
	abstract String eat2();
}

/**
 * Fruit 의 공통 기능(추상 메서드)을 강제 구현 -> Generic 활용
 */
class Fruit2 implements Eatable {
	
	@Override
	public String toString() { return "Fruit2!"; }

	@Override
	public void eat() {
	}

	@Override
	public String eat2() {
		return "과일 먹기";
	}
}

class Apple2 extends Fruit2 {

	@Override
	public String toString() { return "Apple2!"; }

	@Override
	public void eat() {
	}
	
	@Override
	public String eat2() {
		return "Appple 과일 먹기";
	}
	
}

class Grape2 extends Fruit2 {
	
	@Override
	public String toString() { return "Grape2!"; }
	
	@Override
	public void eat() {
	}
	
	@Override
	public String eat2() {
		return "Grape 과일 먹기";
	}
}

class Toy2 {
	
	@Override
	public String toString() { return "Toy"; }
}

/**
 * FruitBox 를 이용한 쥬스를 만드는 클래스
 * 
 * FruitBox 의 종류 : FruitBox(Fruits2 fruit), AppleBox(Apple2 apple), GrapeBox(Grape2 grape)
 * 
 * 
 */
class Juicer {
	

	/* 컴파일 전 */
//	static Juicer makeJuice(FruitBox box) {
//		// 작업 후 Juice 를 반환하면 됨
//	
//		return new Juicer();
//	}
//	static Juicer makeJuice(AppleBox box) {
//		// 작업 후 Juice 를 반환하면 됨
//	
//		return new Juicer();
//	}
//	static Juicer makeJuice(GrapeBox box) {
//		// 작업 후 Juice 를 반환하면 됨
//	
//		return new Juicer();
//	}
		
	
	/* 컴파일 후 -> 메서드 중복 정의가 발생하게 됨 */
//	static Juicer makeJuice(FruitBox box) {
//		// 작업 후 Juice 를 반환하면 됨
//
//		return new Juicer();
//	}
//	static Juicer makeJuice(FruitBox box) {
//		// 작업 후 Juice 를 반환하면 됨
//
//		return new Juicer();
//	}
//	static Juicer makeJuice(FruitBox box) {
//		// 작업 후 Juice 를 반환하면 됨
//
//		return new Juicer();
//	}
	
	/* 
	 * -> 와일드 카드 적용 : 메서드의 중복 선언을 막기 위한 용도로 사용
	 * 메서드 중복의 해결 방법: 기반 타입으로 매개변수 타입이 되도록 하는 것.
	 * <? super T> : 하한 제한. T와 T의 기반(조상)들만 가능  (위로만 가능)
	 * <? extends T> : 상한 제한. T와 그 파생(자식)들만 가능 (아래로만 가능)
	 * <?> : 제한 X. 모든 타입 가능. <? extends Object>
	 */
	public static Juice makeJuice(FruitBox<? super Fruit2> box) {
		// 작업 후 Juice 를 반환하면 됨

		String tmp = "";
		
		for(Fruit2 f : box.getList()) tmp += f + " ";
		
		return new Juice(tmp);
	}
}

class Juice {
	String name;

	public Juice(String name) {
		this.name = name + " Juice";
	}
	
	public String toString() { return name; }
}











