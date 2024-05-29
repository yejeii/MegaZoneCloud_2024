package ch6;

/**
 * ======================== Interface 2 : default, static method(JDK 1.8~) ============================
 * 
 * Interface는 상수, 추상 메서드로 구성됨
 * 작성한 Interface는 이미 사용중...
 * 필요에 의해 새로운 추상 메서드를 추가해야하는 상황이라 추상메서드를 추가하게되면 에러가 발생!
 * -> 해당 Interface를 구현한 기존의 모든 클래스가 추가된 새로운 추상 메서드를 구현해야 하므로. : 모든 코드에 영향이 가게 됨
 * 
 * -> 새로운 메서드를 Interface에 추가하더라도 다른 곳에 영향이 없길 바람.. : default method 등장 배경
 * 
 * 또하나, Interface의 작성 원칙 : only 추상 메서드
 * 다른 곳에서 사용되고 있는 static method는 인스턴스 없이도 사용되고 있음
 * -> Interface는 인스턴스 생성을 하지 못하니까 static method를 추가할 수 있지 않을까? : static method 등장 배경
 * 
 * 한편, 클래스간의 상속, 인터페이스를 활용한 다중 상속처럼 사용 중인 경우, 
 * 클래스의 메서드명과 인터페이스의 default 메서드명이 중복될 수 있음
 * 	- 1. 여러 인터페이스에서 메서드명이 충돌될 경우  
 * 		 -> 파생 클래스의 메서드가 호출됨(구현된 메서드가 호출되는건 당연)
 * 
 * 	- 2. default 메서드와 조상 클래스에서 메서드명이 충돌될 경우
 * 		 -> 기반 클래스의 메서드가 호출됨(인터페이스의 default 무시)
 */
public class OopEx10 {
	
	public static void main(String[] args) {
		
		Child10 c = new Child10();
		
		/*
		 * 1. 여러 인터페이스에서 메서드명이 충돌된 경우 
		 * 	해결> 두 개의 인터페이스 중, 아무거나 하나를 구현하려는 클래스에 오버라이딩한다
		 *  결과> method1()은 두 개의 Interface와 Child10 class에 구현된 상태이나,
		 *       결과적으로 Child10에 구현된 method1()이 호출됨
		 *       즉, 클래스에서 구현된 메서드가 호출됨.
		 */
		c.method1(); // Child10.method1()
		
		/*
		 * 2. 인터페이스의 default 메서드와 조상 클래스의 메서드명이 충돌된 경우(상속 관계)
		 * 	결과> 조상 클래스의 메서드가 호출되고 default 메서드는 무시됨
		 */
		c.method2();	// Parent10.method2()
		
		/* 
		 * 인터페이스의 static method 호출 
		 * 	등장> JDK 1.8~ 
		 * */
		MyInterface.staticMethod1();
		MyInterface2.staticMethod1();
		
		
	}

}

class Parent10 {
	// MyInterface와 충돌
	public void method2() {
		System.out.println("Parent10.method2()");
	}
}

class Child10 extends Parent10 implements MyInterface, MyInterface2{

	/* 
	 * Duplicate default methods named method1 with the parameters () and () are inherited from the types MyInterface2 and MyInterface
	 * -> Implement one of those duplicated default method you want.
	 */
	@Override
	public void method1() {
		System.out.println("Child10.method1()");
	}

}

interface MyInterface {
	// default method
	// MyInterface2와 충돌
	default void method1() {
		System.out.println("MyInterface.method1()");
	}

	// Parent3와 충돌
	default void method2() {
		System.out.println("MyInterface.method2()");
	}
	
	// static method
	static void staticMethod1() {
		System.out.println("MyInterface.staticMethod1()");
	}
	
}

interface MyInterface2 {
	// default method : duplicated name
	// MyInterface와 충돌
	default void method1() {
		System.out.println("MyInterface2.method1()");
	}
	
	// static method
	static void staticMethod1() {
		System.out.println("MyInterface2.staticMethod1()");
	}
}






