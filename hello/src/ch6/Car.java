package ch6;

public class Car {

	public String color;
	public String gearType;
	public int door;
	
	public Car() {
		this("white", "auto", 3);
	}
	
	// 생성자 오버로딩
	public Car(String color) {
		/* 생성자 내에서 생성자 호출 : 매개변수 하나의 값을 넣어주면 다른 건 고정적으로 초기화됨
		 * 항상 메서드 첫줄에 입력할 것!
		 */
		this(color, "auto", 5);
	}
	
	// 생성자 오버로딩
	public Car(String color, String gearType, int door) {
		this.color = color;
		this.gearType = gearType;
		this.door = door;
	}
	
	
	
}
