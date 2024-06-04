package ch12;

/**
 * 상수의 구성 정보
 * - 불연속적인 상수값
 * - 상수값 외의 추가적인 정보
 * 
 * 
 */

enum Direction3 {
	
	/* 상수 선언 : 상수명 + 상수값 + 심볼 */
	EAST(1, ">"), 
	SOUTH(2, "V"), 
	WEST(3, "<"), 
	NORTH(4, "^");
	
	/*
	 * 상수에 () 사용시 추가할 부분
	 * 1. 생성자 생성
	 * 2. 인스턴스 상수 변수 정의 : () 안에 든 값을 저장
	 * 3. 생성자 : 인스턴스 상수 변수 초기화
	 * 4. getter()
	 */
	
	private final int value;
	private final String symbol;

	private static final Direction3[] DIR_ARR = Direction3.values();
	
	private Direction3(int value, String symbol) {
		this.value = value;
		this.symbol = symbol;
	}
	
	// 상수값 getter
	public String getSymbol() { return symbol; }
	
	public int getValue() { return value; }
	
	// 정수 매개변수를 받아서 해당 상수를 반환하는 메서드
	public static Direction3 direction3(int idx) {
		
		if(idx < 1 || idx > 4) {
			throw new IllegalArgumentException("Invalid value : " + idx);
		}
		return DIR_ARR[idx-1];
				
	}
	
//	public String getEnum(int value) {
//		return switch (value) {e
//		case 1 -> "EAST";
//		case 2 -> "SOUTH";
//		case 3 -> "WEST";
//		default -> "NORTH";
//		};
//	}
	
	public String toString() {
		return name() + " " + getSymbol();
	}
}

public class EnumEx2 {
	public static void main(String[] args) {
		
		
		for(Direction3 dir : Direction3.values()) {
			System.out.printf("%s=%s%n", dir.name(), dir.getSymbol());
		}
		
		// 위치정보를 사용해서 상수를 반환하는 메서드 사용
//		for(Direction3 dir : Direction3.values()) {
//			dir.getEnum(dir.getValue());
//		}
		
		Direction3 d3 = Direction3.direction3(1);
		
		
		// 상수명, 상수값, 심볼 정보 출력
//		for(Direction3 dir : Direction3.values()) {
//			dir.getEnum(dir.getValue()) + " : " + dir.getValue() + " : " + dir.getSymbol();
//		}
		
		System.out.printf("d3 = %s, %d, %s %n", d3.name(), d3.getValue(), d3.getSymbol());
		
		
	}
}
