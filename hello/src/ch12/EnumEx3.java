package ch12;

/**
 * 상수의 구성 정보
 * 	- 불연속적인 상수값
 * 	- 기능적인 메서드
 * 		1. 추상 메서드 선언
 * 		2. 각 상수에 추상 메서드 구현
 * 		   -> 각 상수에 기능 구현 제한(강제)
 * 		3. 상수 메서드 내에서 인스턴스 변수 사용 불가
 * 		   -> 참조할 수 있도록 별도의 상수 생성, 참조하도록 함(우회 참조)
 * 
 * >정리<
 * enum 에서
 * 상수 하나(ex. BUS)는 하나의 private final static 객체.
 * 
 * -> 컴파일 시, static 객체로 올라가므로 상수값, 심볼, 기능적인 메서드를 가질 수 있음
 * 		1. 상수값, 심볼은 상수(ex. BUS - 객체)의 final 값(== 값이 고정된 하나의 인스턴스 변수)
 * 		2. 메서드는 이런 final 값을 사용해서 원하는 값을 추출하기 위한 기능
 * -> 이를 위해선 
 * 		1. 상수값을 저장할 private final 인스턴스 변수와		-> private final : enum 클래스에서만 접근 가능한 절대값. 
 * 		2. 인스턴스 변수를 초기화하는 private 생성자,
 * 			( private 이다보니 하나의 인스턴스를 의미한다 )	
 * 		3. public getter() 가 필요하다					-> private final 인스턴스 변수를 가져오기 위한 public get 처리
 * 			( enum 자체는 고정된 상수이므로 setter() 따로 필요 X )
 * 
 * 상수에 기능 추가하기
 * 	1. 상수 메서드에서 인스턴스 변수로 접근하기 위해선 (public -> private 로의 접근)
 * 		public getter() 를 사용하거나 변수값을 저장할 public 상수를 선언, 사용해야 한다.
 *  2. 상수 메서드에서 기능을 추가하기 위해선 enum 클래스 딴에서 abstract 메서드를 생성해야 한다.
 *  	-> 반드시 구현해야 하는 강제성을 띄우므로 구현이 가능하다.
 */

enum Transportation {
	BUS(100) {
		/*
		 * 요금 계산 기능 추가
		 * -> 매개변수를 받아서 구현하고자 함.
		 */
//		return 100 * this.basicFare;
		int fare(int distance) {
//			return distance * BASIC_FARE;	// BASIC_FARE : 100
			return distance * getBasicFare();	// BASIC_FARE : 100
		}
	},
	TRAIN(150) {

		@Override
		int fare(int distance) {
			return distance * BASIC_FARE;
		}
		
	};

	private final int basicFare;
	public final int BASIC_FARE;
	
	Transportation(int basicFare) {
		this.basicFare = basicFare;
		BASIC_FARE = basicFare;
	}
	
	public int getBasicFare() { return basicFare; }
	
	// 추상 메서드 -> 상수에서 반드시 구현해야 함(강제)
	abstract int fare(int distance);	
	
}

public class EnumEx3 {

	public static void main(String[] args) {
		
		System.out.println("Bus 요금 : " + Transportation.BUS.fare(100));
		System.out.println("Train 요금 : " + Transportation.TRAIN.fare(100));
	}

}
