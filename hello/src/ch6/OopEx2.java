package ch6;

/* ################## 본격적인 OOP ##########################
 * 
 * - 생성자
 * 	 인스턴스를 생성할 때 호출되는 '인스턴스 초기화 메서드'
 * 	 인스턴스의 초기화 작업에 주로 사용됨
 * 	 인스턴스 생성 시 실행되어야 할 작업을 위해서도 사용
 * 
 * 	 특징 : 구조가 메서드와 유사하나, 반환값 X
 * 	 작성 : 클래스명과 동일해야 함
 * 
 *   생성자를 통한 인스턴스 생성
 *   MyMath myMath = new MyMath(); 	: 기본 생성자(컴파일러가 기본적으로 제공)
 *   
 *   1. 연산자 new에 의해 메모리에 MyMath 클래스의 인스턴스가 생성됨
 *   	메모리의 빈공간에 MyMath 클래스를 적재할 곳을 마련 == 주소값 생성 == this 사용가능
 *   2. 자리 잡은 인스턴스의 초기화 작업 수행 : 생성자 호출 MyMath()
 *   3. 생성된 MyMath 인스턴스 주소가 반환되어 참조변수 myMath에 저장됨
 *   4. 참조변수 myMath를 사용해 인스턴스에 접근해 사용
 *   
 * - 기본 생성자(Default constructor)
 * 	 컴파일 시 소스파일에 생성자가 하나도 정의되지 않은 경우, 컴파일러가 제공
 * 
 * - 매개변수가 있는 생성자
 * 	 인스턴스마다 각기 다른 값으로 초기화해야 하는 경우 사용
 * 	 오버로딩도 적용 가능
 * 
 * 	 인스턴스 변수 초기화(생성자 사용 전)
 *   d1.value = 100;
 *   d2.value = 200;
 *   d3.value = 300;
 *   d4.value = 400;
 *   d5.value = 500;
 *   
 *   인스턴스 변수 초기화(생성자 사용 후)
 *   Data1(100,200,300,400,500));
 * 
 * - 생성자에서 다른 생성자 호출 : 클래스 내에 여러개의 생성자가 있는 경우
 * 
 * - 생성자를 이용한 인스턴스 복사
 *   현재 사용하고 있는 인스턴스와 같은 상태를 갖는 인스턴스를 하나 더 만들고자 하는 경우 : 게임에서의 분신술
 *   상태가 동일함 : 고유정보(주민번호)가 동일하다는 의미 X
 *   
 *   생성자의 매개변수를 해당 클래스의 참조변수로 매개변수를 받으면 구현 가능
 *   
 * - 멤버변수의 초기화 : 기본값으로 초기화됨.
 *   지역변수는 반드시 초기화가 필수
 *   
 * - 멤버변수의 초기화 방법
 *   1. 명시적 초기화
 *      class car {
 *      	int doorType = 4;//기본형
 *      
 *      	DoorType dt = new DoorType(); //참조형
 *      }
 *   
 *   	참조변수.멤버변수 = 100;
 *   2. 생성자
 *   3. 초기화 블록
 *      - 인스턴스 변수 초기화 블록 : 인스턴스 변수가 대상.				
 *      - 클래스(static) 변수 초기화 블록 : 클래스 변수가 대상
 *      
 *      - 동작
 *        클래스가 메모리에 처음 로딩될 때 한 번만 수행되며,
 *        인스턴스 초기화 블럭은 생성자와 같이 인스턴스를 생성할 때마다 수행됨.
 *        인스턴스 초기화 블럭은 생성자 보다 먼서 초기화 블럭이 수행됨.
 */  
public class OopEx2 {
	
	public static void main(String[] args) {
		
		// Data1, Data2 인스턴스 생성
		Data1 data1 = new Data1();
		Data2 data2 = new Data2();		// 개발자가 생성자를 생성한 경우, 컴파일러는 기본 생성자 제공 X
		Data2 data3 = new Data2(77L);	
		System.out.println("data1.value : "+data1.value);
		System.out.println("data2.value : "+data2.value);
		System.out.println("data3.value : "+data3.value);
		
		/* 생성자를 이용한 인스턴스 복사 */
		Car car1 = new Car();
		System.out.println(car1);
		System.out.println(car1.color);
		
		Car car2 = new Car(car1);
		System.out.println(car2);
		System.out.println(car2.color);
		
		
		
		
	}

}

class Data1 {
	int value;
}

class Data2 {
	int value;
	
	/* 기본 생성자 : 명시적 추가 */
	public Data2() {
		/* 생성자에서 다른 생성자 호출 */
		this(50);	// 첫 줄이며 생성자명 대신 this()를 사용했기에 사용 가능

		this.value = 100;
		
		/* 생성자에서 다른 생성자 호출 */
//		Data2(200);	 The method Data2(int) is undefined for the type Data : 생성자 내에서 생성자명으론 호출 불가
//		this(200);	 Constructor call must be the first statement in a constructor : 생성자의 의도에 위배 : 생성자의 사용 목적은 초기화이므로 반드시 첫줄에서 사용
	}

	/* 매개변수가 있는 생성자 */
	public Data2(int x) {
		this.value = x;
	}
	
	// 생성자 overloading
	public Data2(long x) {
		this.value = (int)x;	// 명시적 casting(큰 쪽에서 작은 쪽으로) : 값 손실 각오...
	}
}
