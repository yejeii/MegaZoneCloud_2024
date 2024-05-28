package ch6;

/**
 * ========================== Polymorphism(다형성) ===========================
 * 
 * - 의미
 *   일반적인 의미 : 다양한 형태를 가지고 있음
 * 	 JAVA에서의 다형성 : 하나의 타입의 참조변수로 여러 타입의 객체를 참조할 수 있는 것
 * 					 -> 타입 변환 == 형변환(casting)
 * 	 
 *   Tv t = new Tv();			// 기반 클래스
 *   TvVcr tvcr = new TvVcr(); 	// 파생 클래스
 *   TvCaption tcap = new TvCaption();	// 파생 클래스
 *   
 *   Tv t = new TvVcr();		// 다형성 : 인스턴스는 TcVcr, 참조변수 타입은 기반 클래스인 Tv!!
 *   Tv t2 = new TvCaption();	
 *   
 *   => Tv라는 하나의 타입으로 TvVcr, TvCaption의 인스턴스 타입으로 표현함
 *   
 * - 다형성으로 표현된 클래스가 인스턴스가 되었을 때
 *   Tv tv = new TvVcr();		// 메모리의 인스턴스는 TvVcr, 참조변수의 타입은 기반 클래스 
 *   							   -> 부모 클래스의 범위 내에서 한정적으로만 사용 가능(사용 제한)	
 *   							   비록 인스턴스는 TvVcr이지만 Tv 멤버들만 사용 가능
 *   							   -> TvVcr의 모든 것을 사용하려면 down casting
 *   TvVcr tvcr = new TvVcr();	// 메모리의 인스턴스는 TvVcr, 참조변수의 타입은 자기 자신 -> 파생 클래스 자신의 범위 내에서 사용 가능
 *   
 *   
 *   Tv t = new Tv();			
 *   Tv t = new TvVcr(); 	
 *   Tv t = new TvCamera(); 	
 *   Tv t = new TvGame(); 	
 *   Tv t = new TvCdPlayer(); 	
 *   -> 다양한 타입의 인스턴스들을 하나의 타입으로 표현
 *   Tv[] tvArr = new Tv[100];
 *   
 *   Tv[0] = new TvVcr();
 *   Tv[1] = new TvCamera();
 *   Tv[2] = new TvGame();
 *   Tv[3] = new TvCdPlayer();
 *   
 * - 형변환(casting)
 *   1. up casting : 파생 -> 기반, 자동형변환
 *   				 JAVA는 단일 상속만 허용 -> 파생 입장에서 부모는 하나이므로 명시적 형변환 필요 X
 *   2. down casting : 기반 -> 파생, 명시적 형변환
 *   				   기반 입장에선 여러 파생이 있을 수 있으므로 down casting 대상이 누가 될 지 알 수 없음로 명시적 형변환 필요
 * 
 * - instanceof 연산자
 *   참조변수가 참조하고 있는 인스턴스의 실제 타입을 알아보는 용도로 사용
 *   
 *   메모리 상의 인스턴스 상태(live-dead)까지 고려해주는 것은 아님
 *   -> 고려 대상 : 상속관계에서의 상태
 *   
 * - 다형성의 활용과 instanceof의 용도
 *   매개변수를 활용한 다형성 -> 메서드의 매개변수가 다형성을 띄고 있다
 *   					  전달된 인스턴스의 참조변수 타입에 따라 해당 인스턴스의 멤버변수 사용시 범위를 알 수 있기 때문
 *   
 *   반환타입을 활용한 다형성 -> 메서드의 반환타입이 다형성을 띄고 있다
 *   					  메서드를 호출한 쪽에서 현재 참조변수가 누구의 인스턴스인지 확인이 필요한 경우가 있기 때문
 *   
 *   매개변수를 활용한 메서드 / 반환이 있는 메서드의 경우, 동일한 기능을 하는 오버로딩된 메서드가 많아지면 유지보수가 hard...
 *   -> 기반클래스 타입으로 매개변수와 반환형을 적용하면 관리 대상 메서드가 적게 됨!
 * 
 * - 참조변수와 인스턴스의 연결
 */
public class OopEx5 {

	public static void main(String[] args) {
		
		Car2 car = null;
		FireEngine fe = new FireEngine();
		FireEngine fe2 = null;
		
		fe.water();
		
		// 다형성을 고려한 코드
		
		/* up casting : 파생 -> 기반 
		 * 파생 인스턴스의 멤버 사용 불가 */
		car = fe;		// 인스턴스는 파생 클래스인데, 참조변수 타입은 기반클래스 : up casting(자동 형변환, 아래와 동일)
		car = (Car2)fe;	
		
		// Car2 참조변수의 인스턴스 사용 범위 고려
//		car.water();	Error : 파생 인스턴스의 멤버 사용 불가
		
		/* down casting : 기반 -> 파생 
		 * 부모한텐 많은 자식이 을 수 있으니 casting할 클래스 타입을 명시해주는 것
		 * 파생 인스턴스의 멤버 사용 가능 */
//		fe2 = car; Error : Type mismatch: cannot convert from Car2 to FireEngine
		fe2 = (FireEngine)car;	// 명시적 형변환
		// runtime error 발생치 않고 정상 종료
		// 메모리 상에는 FireEngine의 인스턴스 존재
		// FireEngine 파생 클래스는 기반 클래스인 Car2로부터 상속된 상태
		// 메모리 상의 FireEngine 내부에는 Car2가 있음

		fe2.water();
		
		/* down casting 시 고려 사항 
		 * up, down이 모두 다되는 경우가 있고, 아닌 경우가 있음
		 * */
		Car2 car2 = new Car2();
		FireEngine fe3 = null;
		
		car2.drive();
		
//		car2 = fe3;
//		fe3 = (FireEngine)car2;	// Error 
		// down casting
		// down casting의 본질적인 목적은 파생 클래스의 인스턴스 멤버를 사용하기 위함
		// 현재 메모리의 인스턴스는 car2만 존재..
		// -> 메모리에는 존재하지 않는 FireEngine의 멤버를 사용하기 위해 down casting 한다는 자체가 성립 X
		// -> 사용하기 위해선 fe3으로 car2를 up casting을 우선적으로 해주면 됨 
		
		/**
		 * 현재의 인스턴스는 Car2 == 메모리에 있는 인스턴스
		 * -> FireEngine으로 다운 캐스팅하더라도 Car2만 존재하므로 FireEngine의 멤버 사용 불가
		 * 
		 * 에러 종류 : 문법 에러, 실행(runtime) 에러
		 * 			문법적으로는 정상적인 것처럼 보이지만, 실제 메모리상의 존재 인스턴스에 위배되는 코드로 실행됨으로 runtime 에러가 발생하게 됨
		 * 
		 * 다형성을 활용하는 코드 작성시, 반드시 메모리 상의 인스턴스 상태를 고려해 작성해야 함
		 * 
		 */
		
		/* instanceof 연산자 */
		FireEngine fe4 = new FireEngine();
		
		/* up casting(자동 형변환) 가능한지 확인 */
		if(fe4 instanceof FireEngine) {
			// 참조변수 fe4의 클래스 타입이 FireEngine인 경우 : 자동 형변환(up casting) 가능
			System.out.println("up casting : FireEngine instacne");
			FireEngine fe5 = fe4;
		}

		if(fe4 instanceof Car2) {
			// 참조변수 fe4의 클래스 타입이 Car2인 경우 : 자동 형변환(up casting) 가능
			System.out.println("up casting : Car2 instacne");
			Car2 car4 = fe4;
		}
		
		if(fe4 instanceof Object) {
			// 참조변수 fe4의 클래스 타입이 Object인 경우 : 자동 형변환(up casting) 가능
			System.out.println("up casting : Object instacne");
			Object obj = fe4;
		}
		
		/* down casting 확인 
		 * up casting 후 실행하면 되지만, up casting 전에 실행하면 Error!! 
		 * 사유는 아래와 Error와 동일 */
		 Object fe5 = new FireEngine();
		
		if (fe5 instanceof Object) {
			System.out.println("down casting : Object instacne");
			Object obj = (Object) fe5;
		}
		
		if (fe5 instanceof Car2) {
			System.out.println("down casting : Car2 instacne");
			Car2 car4 = (Car2) fe5;
		}
		
		if (fe5 instanceof FireEngine) {
			System.out.println("down casting : FireEngine instacne");
			FireEngine fe6 = (FireEngine) fe5;
		}

		if (fe5 instanceof Ambulance) {
			System.out.println("down casting : Ambulance instacne");
			Ambulance am = (Ambulance) fe5;
		}
		
		/* 참조변수와 인스턴스의 연결 1
		 * casting 후 사용되는 멤버 변수와 멤버 메서드를 확인 
		 * 
		 * 오버라이드 된 상태에서 up casting인 경우
		 * 1. 멤버 변수를 사용하는 경우 : 참조 변수의 타입을 따라감
		 * 2. 멤버 메서드를 사용하는 경우 : 인스턴스(메모리에 존재)의 클래스 타입을 따라감
		 * */
		Parent3 p = new Child3();	// up casting : 부모꺼 사용
		Child3 c = new Child3();
		
		// 참조변수 타입이 Parent3인 경우, Child3의 멤버변수 및 멤버메서드 사용
		System.out.println("p.x : " + p.x);	// 100 : 목적 달성(기반 멤버)
		p.method();	// 자식 메서드가 호출됨 : 목적 달성 X(파생 멤버)
		
		// 참조변수 타입이 Child3경우, Child3의 멤버변수 및 멤버메서드 사용
		System.out.println("c.x : " + c.x);	// 200(파생 멤버)
		c.method();							// 파생 멤버
		
		/* 참조변수와 인스턴스의 연결 2
		 * 
		 * 파생 클래스에 멤버가 없는 경우(기반 클래스에만 있는 경우)
		 * 기반 클래스의 멤버를 따라감
		 */
		Parent3 p2 = new Child4();
		Child4 c2 = new Child4();
		
		System.out.println("p2.x : " + p2.x);	
		p2.method();
		
		System.out.println("c2.x : " + c2.x);	
		c2.method();
		
	}// end of main
}

/**
 * 참조변수 형변환 관련 클래스
 */
class Car2 {
	String color;
	int door;
	
	void drive() {
		System.out.println("driving..");
	}
	void stop() {
		System.out.println("stopped");
	}
}

class FireEngine extends Car2 {
	void water() {
		System.out.println("화재 진압중");
	}
}

class Ambulance extends Car2 {
	void siren() {
		System.out.println("사이렌");
	}
}

/** 
 * 참조변수와 인스턴스의 연결 관련 클래스
 */
class Parent3 {
	int x = 100;
	
	void method() {
		System.out.println("Parent3.method()");
	}
}

class Child3 extends Parent3 {	
	int x = 200;
	
	@Override
	void method() {
		System.out.println("Child3.method()");
	}
}

class Child4 extends Parent3 {	
	
}























