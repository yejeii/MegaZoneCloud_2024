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

public class EnumEx1 {

	public static void main(String[] args) {
		
		Direction d1 = Direction.EAST;
		Direction d2 = Direction.valueOf("WEST");
		Direction d3 = Enum.valueOf(Direction.class, "EAST");
		
		System.out.println("d1 = "+ d1);
		System.out.println("d2 = "+ d2);
		System.out.println("d3 = "+ d3);
		
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
