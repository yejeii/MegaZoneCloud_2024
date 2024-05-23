package ch6;

/** 
 * 자동차 클래스
 */
public class Car {

	public String color;
	public String gearType;
	public int door;
	public int count;
	public int serialNo;
	
	/* 인스턴스변수(멤버변수)의 초기화 목록
	 * 모든 생성자에서 공통으로 수행되어야 하는 문장이 있다면, 하나의 블럭에서 관리 
	 * -> 가독성, 유지보수성 : 코드 중복의 제거
	 */
	{
		this.count++;
		this.serialNo = this.count;
	}
	
	public Car() {
		this("white", "auto", 3);
//		this.count++;
//		this.serialNo = this.count;
	}
	
	// 생성자 오버로딩 : 매개변수 한 개
	public Car(String color) {
		/* 생성자 내에서 생성자 호출 : 매개변수 하나의 값을 넣어주면 다른 건 고정적으로 초기화됨
		 * 항상 메서드 첫줄에 입력할 것!
		 */
		this(color, "auto", 5);
//		this.count++;
//		this.serialNo = this.count;
	}
	
	// 생성자 오버로딩 : 매개변수 세 개
	public Car(String color, String gearType, int door) {
		this.color = color;
		this.gearType = gearType;
		this.door = door;
		
//		this.count++;
//		this.serialNo = this.count;
	}
	
	// 생성자 오버로딩 : 매개변수 자기자신 타입 => 인스턴스 변수 초기화용
	public Car(Car car) {
		this.color = car.color;
		this.gearType = car.gearType;
		this.door = car.door;	
		
//		this.count++;
//		this.serialNo = this.count;
	}
	
	
	
}
