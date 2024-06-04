package ch12;

/**
 * ==================== Enum(열거형) =====================
 * 
 * 관련된 상수를 한 곳에 모아서 사용하기 위함
 * 
 * 열거형의 정의
 * 
 * 	- enum Direction { EAST, SOUTH, NORTH, WEST }
 *  - class Unit {
 *  	int x, y;		// 유닛 위치
 *  	Direction dir;	// 방향 정보 : 열거형을 인스턴스 변수로 선언
 *  
 *  	void init() {
 *  		dir = Direction.EAST;	// 사용 : 열거형 이름.상수명
 *  	}
 *    }
 *    
 * - 열거형의 기반 : java.lang.Enum
 * 		name() : 상수명을 문자열로 반환
 * 		ordinal() : 열거형 상수가 정의된 순서를 정수로 반환(0부터 시작)
 * 		valueOf() : 매개변수와 일치하는 열거형 상수를 반환
 * 
 * - 열거형의 활용 및 확장
 * 	- 상수 간의 비교에 "==" 연산자 사용 가능
 *    -> equals() 메서드보다는 연산이 빠름
 *    단, 다른 열거형끼리 비교는 불가함(자신의 상수간끼리만 비교 가능)
 *    
 * 	  Comparing Enum Types Using “==” Operator
 * 	  -> Since enum types ensure that only one instance of the constants exist in the JVM(SingleTon Pattern),
 *       we can safely use the “==” operator to compare two variables.
 * 	     Plus, "==" operator provides complie-time and run-time safety.
 * 
 *  - 비교 연산자 사용 불가( <, > )
 *  - 그러나 compareTo() 사용 가능
 *    비교 대상이 같으면 0, 왼쪽이 크면 양수, 오른쪽이 크면 음수 리턴
 *  - case 문에서 사용 시, 반드시 상수 이름 사용할 것
 * 
 *  - 열거형의 확장 -> 기능적인 요소를 추가
 *    인스턴스 변수, 생성자, 메서드, 추상 메서드 -> 마치 클래스와 비슷한 형태
 *    
 *    상수에 불연속적인 값으로 설정하는 경우 필요한 추가 사항
 *    - 인스턴스 변수, 생성자(private)를 반드시 추가해야 함.
 *    - getter 필요
 *    
 *    // 열거형의 확장
 *    enum Direction {
 *    
 *    	// 상수명 + 불연속적 값
 *    	// 상수명, 상수값으로 구성
 *    	EAST(1), SOUTH(5), WEST(-1), NORTH(10);			// 클래스 내부에서는 객체로 사용됨
 *
 *		// 상수명 + 불연속적 값 + 심볼
 *		// 상수명, 상수값, symbol(사용자 정의)로 구성
 *    	EAST(1, ">"), SOUTH(5, ">"), WEST(-1, ">"), NORTH(10, ">");		
 *    	
 *    	// 상수명 + 불연속적 값 + 기능 
 *    	// 교통 수단 관련 상수, 상수값을 기본 요금으로 설정
 *    	// 교통 수단 관련 상수에 제한을 추가 : 요금 처리 관련
 *    	BUS(10000) {
 *    		// 요금 계산 처리를 위한 메서드
 *    	}
 *    	TRAIN(20000) {
 *    		// 요금 계산 처리를 위한 메서드
 *    	}
 *		SHIP(15000);	// 계산 기능의 추가를 강제할 수 있음	-> 추상 메서드
 *     	
 *     	// 불연속적인 값, 심볼을 저장할 인스턴스 변수 추가
 *    	private final int value;						
 *    	
 *    	// 생성자 생성, 초기화
 *    	Direction(int value) { this.value = value; }	// 묵시적으로 private -> 외부에서 호출불가(== 외부에서 객체 생성 불가)
 *    	
 *    	public int getValue() { return value; }
 *    
 *		// symbol 정보 관리와 관련된 멤버
 *		
 *		// 교통 수단의 요금 계산을 위한 
 *    
 *    
 *    }
 * 
 * 
 * class Card {
 * 	static final int CLOVER = 0;	// 카드무늬
 * 	static final int HEART = 1;	
 * 	static final int DIAMOND = 2;	
 * 	static final int SPADE = 3;	
 * 
 * 	static final int TWO = 0;		// 숫자
 *  static final int THREE = 1;
 *  stafic final int FOUR = 2;
 * 
 *  final int kind;
 *  final int num;
 * }
 * 
 * -> Enum 적용
 * 
 * class Card {
 * 	enum Kind {CLOVER, HEART, DIAMOND, SPADE}
 *  enum VALUE {TWO, THREE, FOUR}
 *  
 *  final Kind kind;
 *  final Value value;
 */

enum Direction { EAST, WEST, SOUTH, NORTH }
enum Direction2 { EAST, WEST, SOUTH, NORTH }

// 내부적으로 관리되는 순서 정보 -> 정수형, 0부터 시작

public class EnumEx1 {

	public static void main(String[] args) {
		
		Direction d1 = Direction.EAST;
		Direction d2 = Direction.valueOf("WEST");
		Direction d3 = Enum.valueOf(Direction.class, "EAST");
		
		Direction2 d4 = Direction2.EAST;
		
		System.out.println("d1 = "+ d1);
		System.out.println("d2 = "+ d2);
		System.out.println("d3 = "+ d3);
		
		/* 
		 * 열거형의 비교
		 * 
		 * Direction.EAST == Direction2.EAST
		 * 다른 enum 끼리는 비교 불가
		 * 비교 대상은 같은 enum 안에서 가능!
		 */
		System.out.println("d1 == d2 : " + (d1 == d2));
//		System.out.println("d1 == d4 : " + d1 == d4);	Error
//		System.out.println("Direction.EAST == Direction2.EAST : " + (Direction.EAST == Direction2.EAST));	Error
		
		System.out.println();
		
		// Enum 의 상수 정보를 배열로 반환
		Direction[] dirArr = Direction.values();
		for(Direction d : dirArr) {
			System.out.printf("%s=%d%n", d.name(), d.ordinal());
		}
		
		System.out.println();
		
		switch (d1) {
		case EAST:
			System.out.println("EAST");
			break;
		case SOUTH:
			System.out.println("SOUTH");
			break;
		default:
			System.out.println("업는 정보");
		}
	}
}
