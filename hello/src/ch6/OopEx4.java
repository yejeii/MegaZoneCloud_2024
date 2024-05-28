package ch6;

/**
 * ===================== 클래스 간의 관계 ====================
 * 
 * - 상속
 *   조상 클래스 : 부모 클래스, 상위 클래스, 기반 클래스
 *   자식 클래스 : 자손 클래스, 하위 클래스, 파생된 클래스
 *   
 * - 장점
 *   상속을 통하면 보다 적은 양의 코드로 새로운 클래스를 작성.
 *   코드의 추가 및 변경이 매우 용이해짐.
 *   
 * - 단점
 *   강한 결합의 가능성이 있을 수 있음.
 *   
 *   결합도 낮음 : 클래스간의 관계를 약하게 함
 *   			즉, 코드 수정시 사용되는 다른 곳에 영향이 없도록 코드를 작성
 *   응집도 높음 : 관련 기능을 한 곳에서 관리가 되도록 함(도메인 지식..)
 *   
 * - 관계
 *   논리적으로 합당한 관계
 *   
 *   상속관계 : is-a 관계. ~은 ~이다.
 *   	사람, 학생. 학생은 사람이다. 		=> 학생 extends 사람
 *      권총, 경찰. 경찰은 권총을 가지고 있다. => 논리적 성립 X
 *   포함관계 : has-a 관계. ~은 ~ 가지고 있다.
 *      사람, 학생. 학생은 사람을 가지고 있다. => 논리적 성립 X
 *      권총, 경찰. 경찰은 권총을 가지고 있다. => 논리적 성립 가능 => 멤버변수 가능성
 *   
 * - 문법적 관계
 *   extends
 *   
 * - 단일 상속, 멀티 상속
 * 	 단일 상속 : 기반 클래스가 하나
 * 	 멀티 상속 : 기반 클래스가 다중
 * 
 *   JAVA에서 멀티 상속처럼 사용하고 싶은 경우
 *   	is-a(상속) + has-a(포함) => extends, 멤버변수
 *   
 * - Object 클래스
 *   JAVA의 모든 클래스 상속계층도의 최상위에 있는 기반 클래스
 *   
 *   class Tv {} : 컴파일러가 아래의 코드로 변경
 *   class Tv extends Object {}
 *   
 * - overriding(오버라이딩)
 *   기반 클래스로부터 상속받은 메서드의 내용을 변경하는 것
 *   
 *   조건 : 메서드명, 매개변수, 반환타입의 동일
 * 
 * - overloading vs overriding
 * 	 overloading : 상속관계 X. 기존에 없는 메서드를 새로 정의하는 것
 * 	 overriding : 상속관계. 파생된 쪽에서 부모 메서드와 동일한 것을 수정, 정의하는 것
 * 
 * - super 
 * 	 모든 인스턴스 메서드에는 자신이 속한 인스턴스의 주소가 지역변수로 저장되는데, 이것이 참조변수인 this와 super!!
 *   
 *   this : 멤버변수와 지역변수의 차이를 두고 싶어서 사용. 자기 클래스 안에서 구분을 위함
 *   super : 클래스 간의 관계에서 기반과 파생을 구분키 위함
 *   
 * - super() : 메서드
 * 	 자식 클래스의 인스턴스를 생성하면, 자식멤버와 조상멤버가 모두 합쳐진 하나의 인스턴스가 생성됨 
 *   -> 파생 클래스에서 조상 클래스의 멤버를 사용할 수 있게 됨
 *   -> super()는 조상 생성자를 호출해 조상멤버를 초기화하도록 함
 * 
 * - >생각해보기<
 *   인스턴스 생성 시 선택
 *   	클래스 : 어떤 클래스의 인스턴스를 생성할 것인가? -> 클래스 설계
 *   	생성자 : 선택한 클래스의 어떤 생성자를 이용해서 인스턴스를 생성할 것인가? -> 상태 설계
 */
public class OopEx4 {

	public void main(String[] args) {
		
	}// end of main
}


/**
 * 상속 관련 클래스
 * Parent.class, Child.class는 is-a 관계 : extends
 * Parent.class, Child.class는 직접 상속관계
 */
class Parent {

	public int age;
}

class Child1 extends Parent {}
class Child2 extends Parent {}

// Parent 기준으로는 간접 상속관계
// Child1 기준으로는 직접 상속관계
class GrandChild extends Child1 {}

/** 포함 관련 클래스
 * Pistol, Police 는 has-a 관계 => Pistol 은 Police 의 멤버변수.
 */
class Pistol {}
class Police {
	Pistol p = new Pistol();
}

/*
 * JAVA에서의 다중상속과 유사한 클래스 작성
 * TV, VCR class를 이용해 다중상속처럼 사용하려면 하나의 클래스로부터는 상속을 받고, 다른 클래스를 참조변수 멤버변수로 가지게 함
 * TV : 부모 클래스
 * VCR : 멤버 변수(옵션)
 */
class Tv2 {
	boolean power;
	int channel;
	
	void power() { power = !power; }
	void channelUp() { ++channel; }
	void channeldDown() { --channel; }
}

class Vcr {
	boolean power;
	
	void play() {}
	void stop() {}
	void rew() {}
	void ff() {}
}

class TvVcr extends Tv2 {
	Vcr vcr = new Vcr();
	
	// 전원은 부모 클래스의 메서드 사용하면 될 듯
	
	// TvVcr은 별도의 새로운 제품이므로 Vcr 기능은 그대로 두고, 자기만의 기능을 추가 구현하고자 함
	void play() {
		
		// 추가적인 구현
		
		// vcr 기능은 내가 신경쓰지 않아도 관리되므로 나와는 상관 X
		vcr.play();	
	}
	
	void stop() {}
	
	void rew() {}
	
	void ff() {}
}

/**
 * 오버라이딩 클래스
 */
class Point {	// 기반 클래스
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String getLocation() {
		return "x : " + x + ", y : " +y;
	}
}

class Point3D extends Point {	// 파생 클래스
	
	int z;

	// 생성자 : 반드시 부모의 생성자를 호출해야 함
	public Point3D(int x, int y) {
//		int a = 10;	Error : 생성자는 항상 처음에 작성 
		
		/** 
		 * super() 
		 * Point3D는 파생 클래스이므로 기반 클래스 없이는 존재 불가!!
		 * -> Point 클래스가 먼저 인스턴스로 생성되어야 Point3D의 인스턴스를 생성할 수 있음
		 * -> 파생 클래스의 생성자에서 반드시 첫줄에 기반 클래스의 생성자를 호출해서 기반 클래스의 인스턴스를 먼저 생성하게 해야 함
		 */
		super(x, y);	// this : 자기참조, this() : 자신의 생성자, super : 부모, super() : 부모 생성자
		int a = 10;
	}

	
	/**
	 * 오버라이딩
	 * 좌표를 출력할 때 고려점
	 * 1. 재사용 => 기반 클래스 메서드 사용
	 * 2. 나의 상태 정보는 내가 출력 => 코드 추가 작성
	 */
	// 아쉬운 오버라이딩
//	public String getLocation() {
//		재사용 코드 X
//		return "x : " + x + ", y : " +y + ", z : " + z;
//	}
	
	// 추천되는 오버라이딩
	@Override
	public String getLocation() {
		 /* 재사용된 코드
		  * 조상 클래스의 메서드 내부의 내용이 변경되더라도
		  * 변경된 내용이 파생 클래스에 영향을 주지 않고 자동적으로 반영됨
		  */
		return super.getLocation() + ", z : " + z;	// 부모꺼 쓰되, 내가 원하는 것만 추가 구현해서 설정
	}
}

/**
 * super 활용 예제 클래스
 */
class Parents {
	int x = 10;
	int y = 10;
}

class Child extends Parents {
	
	int y = 20;
	
	void method1() {
		System.out.println("x : " + x);				// 10
		System.out.println("this.x : " + this.x);	// 10
		System.out.println("super.x : " + super.x); // 10
	}

	void method2() {
		System.out.println("y : " + y);				// 20
		System.out.println("this.y : " + this.y);	// 20
		System.out.println("super.y : " + super.y);	// 10
	}
}

/* 메모리 상에 아래와 비슷하게 만들어 줌 
 * -> x로 접근 가능하며 method()에서는 지역변수로 this, super 초기화되어 있음
 * -> this.x와 super.x를 메서드 내에서 사용할 수 있는 것
 * class Child {
 *  int x = 10;
 *  int y = 20;
 *  void method1() {
 * 	  this, super 
 *  } 
 *  void method2() {
 *    this, super
 *  }
 * } */
