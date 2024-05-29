package ch6;

import java.awt.*;
import java.awt.event.*;

/**
 * ======================= 내부 클래스 =======================
 * 
 * - 지금까지의 핵심부분
 * 	 - OOP 4대 특성의 개념, 활용, 코드레벨로 이해
 * 	   스터디 그룹으로 공부 중에 4대 특성과 관련된 새로운 적용사례가 있으면 정리를 잘 해두어야 함
 * 	 - 추상화, 다형성 정리
 *   - 인터페이스 -> 다형성 -> 약한 결합 -> 생산성 향상
 *   - 상속 다형성 -> 강한 결합 -> 수정이 없을 경우, 상속을 적용
 *   - 인터페이스를 활용한 약한 결합 예제 : 리펙토링 도서, 디자인 패턴
 *   	리펙토링(초보, 아키텍처, DDD, TDD)
 *   
 * - 내부 클래스
 * 	 왜 필요할까? : 멤버 변수와 유사한 역할을 위해.
 *   선언 이유 : 그만큼 클래스간의 밀접도가 높기 때문. 즉, 다른 클래스에서 이너 클래스를 사용할 이유가 없음
 *   장점 : 멤버변수처럼 쉽게 접근 가능 + 코드의 복잡성 저하(캡슐화)
 *   
 *   종류 : 인스턴스 클래스(변수), static 클래스(변수), 지역 클래스(변수), 익명(무명) 클래스(변수)
 *   	1. 인스턴스 클래스 : 인스턴스 멤버처럼 다루어짐. 멤버변수 선언위치에 선언
 *   	2. static 클래스 : static 멤버처럼 다루어짐. static 멤버변수 선언위치에 선언
 *   	3. 지역 클래스 : 외부 클래스의 메서드, 초기화 블럭 안에 위치
 *   	4. 익명 클래스 : 클래스 코드만 한번 쓰고말건데 이름이 필요하겠나..(일회용)
 *   				  -> 클래스 선언과 동시에 객체 생성이 이루어짐
 *   
 *   	class Outer {
 *   		class InstanceInnerClass {} // 1
 *   		static class StaticInnercClass {} // 2
 *   		
 *   		void myMethod() {
 *   			class LocalInnerClass {} // 3
 *   		}
 *   	}
 * 
 * - 익명 클래스 : 이름이 없는 클래스
 *   -> 클래스 선언과 동시에 인스턴스 생성
 *   -> 일회용 
 *   -> 단 하나의 객체만 생성할 수 있음 : 이벤트 처리
 *   
 *   이름이 없기 때문에 생성자 가질 수 없음
 *   -> 기반 클래스명 또는 인터페이스명을 사용해 정의
 *   이때, 기반클래스도 단일 상속, 인터페이스도 단일 implement만 가능
 *   
 *   사용 예시>
 *   폰에서 버튼이 눌렀을 때 기능이 동작해야 함
 *   누름-인식 사이 모니터링 : 수행할 내용을 작성
 *   
 *   Button b = new Button("눌러주세요");
 *   
 *   안드로이드 앱 사용자가 버튼을 클릭했다는 것은 아래의 addActionListener가 호출됨
 *   b.addActionListener(
 *   	수행해야 할 Action 인스턴스를 매개변수로 전달하면 됨(인스턴스 생성)
 *   	버튼이 눌러졌을 때 인스턴스가 생성되면 되는 것 -> 단발적 액션이자 금방 소멸되는 객체여야 하므로 익명 클래스를 사용하는 것
 *   
 *   	버튼 액션을 처리하는 기반 클래스 : ActionListener
 *   	new ActionListener() {
 *   		public void actionPerformed(ActionEvent e) {
 *   			System.out.println("버튼을 눌렀습니다");
 *   		}
 *   	} // end of anonymous class
 *   	
 *   ); // and of addActionListener()
 *   
 *   -> 함수 안에 인스턴스 꼴
 */

public class OopEx11 {
	
	/*
	 * Instance와 static의 사용 차이
	 * static -> instance X
	 * instance -> static O
	 */
	
	/* 1 : 인스턴스 클래스 */
	class InstanceInner {
		int iv = 100;
//		static int cv = 200;
		final static int CONST = 100;
	}
	
	/* 2 */
	static class StaticInner {
		int iv = 200;
		static int CV = 200;
	}
	
	void myMethod() {
		/* 3 */
		class LocalInner {
			int iv = 300;
			final static int CONST= 300;
		}
	}
	
	public static void main(String[] args) {
		
		/* ---- 1. 인스턴스 클래스 사용 ---- */
		
		/* InstanceInner의 static 변수 사용 */
		System.out.println("Oop11Ex.InstanceInner.CONST : " + InstanceInner.CONST);	// static 변수이기에 static 메서드에서 접근 가능
		
//		System.out.println(InstanceInner.iv); ERROR : static 메서드에서 인스턴스 클래스의 인스턴스 변수 접근 불가

		/* InstanceInner의 non-static 변수 사용
		 * 인스턴스 클래스의 인스턴스 변수 사용하려면?
		 * 인스턴스 클래스는 Outer class의 일종의 인스턴스 변수 -> Outer class 객체 생성 우선 */
		OopEx11 outerEx11 = new OopEx11();
		OopEx11.InstanceInner inner = outerEx11.new InstanceInner();
		System.out.println("Oop11Ex.InstanceInner.iv : " + inner.iv); 
		/* ---- 1. 인스턴스 클래스 사용 끝 ----  */
		
		
		/* ---- 3. Outer 클래스의 인스턴스 및 static 클래스의 멤버변수 사용(위의 내용 복습) ---- */

		/* 인스턴스 클래스 사용 */
		Outer3 oc = new Outer3();
		Outer3.InstanceInner ii = oc.new InstanceInner();
		System.out.println("Outer3.InstanceInner.iv : " + ii.iv);
		
		/* Static 클래스 사용 
		 * 1. static 변수의 경우 클래스명으로 접근
		 * 2. non-static 변수의 경우 new 연산자 */
		System.out.println("Outer3.StaticInner.cv : " + Outer3.StaticInner.cv);
		
		Outer3.StaticInner si = new Outer3.StaticInner();
		System.out.println("Outer3.StaticInner.iv : " + si.iv);
		/* ---- 3. Outer 클래스의 인스턴스 및 static 클래스의 멤버변수 사용 끝 ---- */
		
		
		/* ---- 4. Outer, Inner class에서의 this 사용 ---- */
		
		Outer4 o4 = new Outer4();
		Outer4.Inner i4 = o4.new Inner();
		i4.method1();
		/* ---- 4. Outer, Inner class에서의 this 사용 끝 ---- */
		
		
		/* ------------------- 5. 익명 클래스 적용 전후 ------------------ */
		Button b = new Button("Start!");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ActionEvent Occurred!!");
			}
		});
		
		/* ------------------- 5. 익명 클래스 적용 전후 끝 ------------------ */
		
	}

}

/* ----- 2. 멤버 메서드, 클래스 메서드에서 멤버 클래스 및 지역 클래스 접근 ----- */
class Outer2 {
	class InstanceInner {}
	static class StaticInner {}
	
	/* 인스턴스 멤버 변수가 인스턴스 클래스에 접근 
	 * 인스턴스 멤버간에 접근*/
	InstanceInner iv = new InstanceInner();
	
	/* static 멤버가 static 클래스에 접근 */
	static StaticInner cv = new StaticInner();
	
	/* Outer2의 인스턴스 메서드 : 인스턴스와 static 접근 가능 */
	void outerMethod() {
		InstanceInner obj1 = new InstanceInner();
		StaticInner obj2 = new StaticInner();
//		LocalInner lv; Error : LocalInner cannot be resolved to a type
	}
	
	/* Outer2의 static 메서드 : static끼리만 접근 가능 */
	static void staticOuterMethod() {
//		InstanceInner obj3 = new InstanceInner(); : Error
		StaticInner obj4 = new StaticInner();
//		LocalInner lv; Error : LocalInner cannot be resolved to a type
	}

	//	LocalInner lv; Error : LocalInner cannot be resolved to a type
	
	void myMethod() {
		/* 지역 클래스 */
		class LocalInner {}
		LocalInner lv = new LocalInner();
	}
}

/* ---- 3. Outer 클래스의 인스턴스 및 static 클래스의 멤버변수 사용 ---- */
class Outer3 {
	class InstanceInner {
		int iv = 100;
	}
	static class StaticInner {
		int iv = 200;
		static int cv = 300;
	}
	
	void myMethod() {
		class LocalInner {
			int iv = 400;
		}
	}
}

/* ---- 4. Outer, Inner class에서의 this 사용 ---- */
class Outer4 {
	int value = 10;
	
	class Inner {
		int value = 20;
		
		void method1() {
			int value = 30;
			
			// method1()에서 value 변수 사용 3가지 케이스
			System.out.println("Outer4.Inner.method1().value : " + value);
			System.out.println("Outer4.Inner.value : " + this.value);
			System.out.println("Outer4.value : " + Outer4.this.value);
			
		}
	}
	
}

/* ------------------- 5. 익명 클래스 ------------------ */
class AnonymousClass {
	
	/*
	 * 익명 클래스 
	 * 자기 이름이 없기 때문에 기반 클래스명을 사용해 정의
	 * -> 정의와 동시에 객체 생성
	 */
	Object obj = new Object() { void method() {} };
	
	static Object obj2 = new Object() { void method() {} };

	void myMethod() {
		Object obj3 = new Object() { void method() {} };
	}
}




