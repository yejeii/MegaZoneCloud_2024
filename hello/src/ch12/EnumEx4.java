package ch12;

/**
 * 상수를 직접 만드는 것 (enum 사용 x)
 * 
 * - enum 의 구조
 * 		
 * 		1. 기반 클래스
 * 		   	enum Direction { EAST, SOUTH, WEST, NORTH }
 * 		   		- 내부적으로 순서 정보를 관리가 가능해야 함 : ordinal()
 * 				- 상수명도 관리가 되어야 함
 * 
 * 		2. 파생 클래스
 * 		  	enum Direction {
 * 				BUS(100) {
 * 					int fare(int distanceFare) {
 * 						return distance * DISTANCE_FARE;
 * 					}
 * 				}
 * 		  	}
 * 
 * 		  	Direction 은 자기 자신의 클래스 타입임
 * 			자기 자신의 클래스 타입이 Direction 클래스에서 상수 멤버의 형태가 되게 됨
 * 
 * 		3. main()
 * 		  	1, 2 번에서 구현된 기능(메서드)을 사용
 * 
 * 
 * - class 를 직접 구현
 * 	 상수(상수명, 상수값, 기능적인 요소)
 * 
 * 	 생성자,
 * 
 * 	 상수명 반환 함수 -> name()
 * 
 * 
 */
class EnumBase {
	
	int ordinal; 		// enum 내부 순서 정보
	String name = "";	// 상수명
	
	public int fare(int distance) { return distance; }
	
	public EnumBase(String name, int idx) {
		this.ordinal = idx;
		this.name = name;
	}
}

class DirecEnum extends EnumBase {

	public static final DirecEnum EASTT = new EASTT("EASTT");
	public static final DirecEnum WESTT = new WESTT("WESTT");
	public static final DirecEnum NORTHH = new NORTHH("NORTHH");
	public static final DirecEnum SOUTHH = new SOUTHH("SOUTHH");
	
	private static int idx = 0;	// 순서 정보에 활용되는 정보
	
	public DirecEnum(String name) {
		super(name, idx);
		idx++;
	}
}

class EASTT extends DirecEnum {
	
	EASTT(String name) {
		super(name);
	}
}

class WESTT extends DirecEnum {
	
	WESTT(String name) {
		super(name);
	}
}

class NORTHH extends DirecEnum {
	
	NORTHH(String name) {
		super(name);
	}
}

class SOUTHH extends DirecEnum {
	
	SOUTHH(String name) {
		super(name);
	}
}



/* EnumBase 를 바탕으로 사용될 Enum 파생 클래스 */
class TransEnum extends EnumBase {

	public static final TransEnum BUS = new BUS("BUS", 100);
	public static final TransEnum TRAIN = new TRAIN("TRAIN", 150);
	
	final int BASIC_FARE;
	private static int idx = 0;	// 순서 정보에 활용되는 정보
	
	protected TransEnum(String name, int basicFare) {
		super(name, idx);	// 상수명은 부모가 가지고 있음
		idx++;
		BASIC_FARE = basicFare;
	}
	
	@Override
	public int fare(int distance) { return distance * BASIC_FARE; }
	
	public String name() { return name; }
	
}

class BUS extends TransEnum {
	
	BUS(String name, int basicFare) {
		super(name, basicFare);
	}
}

class TRAIN extends TransEnum {
	
	TRAIN(String name, int basicFare) {
		super(name, basicFare);
	}
}

public class EnumEx4 {

	public static void main(String[] args) {

		// 2-1. 상수 참조 변수 선언
		TransEnum tEnum = TransEnum.BUS;
		
		// 2-2. 상수명, 상수의 내부 관리 정수값 출력
		System.out.println(TransEnum.BUS.name);
		System.out.println(TransEnum.BUS.ordinal);
		
		// 2-3. fare() 메서드를 활용해서 출력
		System.out.println(TransEnum.BUS.fare(100));
		
	}

}
