package ch6;

/**
 * =====================
 * 
 * - 제어자
 *   접근 제어자 : public, protected, default, private
 *   	private : 같은 클래스 내에서만
 *   	default : 같은 패키지 안에서만, 접근 제어자를 명시하지 않으면 설정되는 default값
 *   	protected : 같은 패키지 및 상속관계에서만
 *   	public : 접근 제한 X
 *   
 *   	public > protected > default > private
 *   
 *   	접근 제어자의 사용목적 : 캡슐화
 *   	  1. 클래스 내의 데이터 보호(information hiding)
 *   	  2. 외부 노출을 하지 않도록 하기 위함 -> 복잡성 제거
 *   
 *		메서드 레벨에서의 접근 제어자 : all
 *			생성자에서의 접근 제어자 : public, private(싱글톤 패턴)    
 *   	클래스 레벨에서의 접근 제어자 : public, default
 *   
 *   	싱글톤 패턴
 *   	class Singleton {
 *   		private static Singleton s = new Singleton();
 *   
 *   		private Singleton() {	}
 *   		
 *   		public static Singleton getInstance() {
 *   			if(s==null) s = new Singleton();
 *   			return s;
 *   		}
 *   
 * 	
 *   접근 제어자 외 : static, final, abstract ...
 *   	static
 *   	  	멤버 변수 : 모든 인스턴스에서 공통적으로 사용되는 클래스 변수가 됨
 *   	             클래스가 메모리에 로드될 때 딱 한번 초기화되어 공유됨 -> 인스턴스를 생성하지 않고도 사용 가능 : Math.PI;
 *   	  	멤버 메서드 : 인스턴스를 생성하지 않고 호출이 가능한 static 메서드가 됨
 *   		           static 메서드 내에서는 인스턴스 멤버 사용 불가
 *   
 *    	  	클래스 멤버들은 사용하기 편리, 인스턴스 멤버보다 속도가 빠름
 *   	
 *   	final
 *   		변수 레벨 : Read-Only 값(상수)
 *   		메서드 레벨 : Override 불가	-> 파생 클래스에서 기반 클래스의 메서드를 사용하게 강제함
 *   		클래스 레벨 : 파생 클래스를 생성불가(상속불가)
 *   
 *   		>생각해보기<
 *   		상수란, "초기화된 값으로만 사용하겠다"
 *   		-> 보통 선언과 동시에 초기화함
 *   		그러나, 만약 인스턴스별로 값을 모두 동일하게 초기화하는 경우라면?
 *		   		-> final static int MAX_VALUE = 100;	: 클래스간에 공유하는 값(static memory에 적재)
 *   		혹은, 만약 인스턴스별로 상수를 다르게 초기화해야 하는 경우라면?
 *   			-> final int MAX_VALUE = 100;			: 클래스간에 공유하는 값 X(Stack에 적재)
 *   													  즉, 인스턴스별로 값이 다를 수 있음 
 *   													  -> 인스턴스 블록/생성자에서 초기화해야 함
 *   
 *   	abstract(추상.. "미완성이다")
 *   		메서드 abstract(미완성 메서드) 레벨 : 미완성 메서드는 선언부만 있고, 몸통은 없는 메서드
 *   
 *   		클래스 abstract(미완성 클래스) 레벨 : 추상 메서드가 있는 클래스
 *   
 *    		abstract class AbstractTest {	-- 추상 클래스
 *    			abstract void move();		-- 추상 메서드
 *    		}
 *    
 *    		추상 클래스의 존재 목적
 *    			미완성된 클래스를 메모리에 적재한다는 것은 말이 안됨...
 *    			-> 추상 클래스는 인스턴스 생성 불가(추상 클래스 자체로는 효용 X)
 *    			-> 대신, 추상 클래스를 구현하면 사용 가능 : 상속
 *    			-> final 클래스가 아님으로 상속을 통해 추상 메서드를 오버라이딩해서 사용
 *    
 *    			상속관계에서 기반 클래스에는 정상적인 클래스와 추상 클래스가 있음
 *    			기반 클래스가 정상적인 클래스인 경우 : 자체적으로 인스턴스화가 가능
 *    			기반 클래스가 추상 클래스인 경우 : 자체적으로 인스턴스화 불가. 메서드를 구현해야함
 *    									  -> 메서드 구현을 강제시키는 효과
 *    
 *    			1. 다형성으로 사용 가능
 *    			   추상 클래스를 상속받아 추상 메서드를 구현(override)했기 때문에 기반 클래스 타입으로 묶을 수 있음
 *    			2. 각 인스턴스별로 자기에 맞게 추상클래스를 재정의
 *    
 *    		>생각해보기<
 *    		다중 상속처럼 사용하지만, 멤버로 사용하지 않고 사용하고자 한다면..? 
 *    		이와 함께 메서드 구현을 강제하고 싶다면..?
 *     		-> Interface
 *     
 *     	Interface : 다중상속처럼 구현 가능, 다형성 활용 가능
 *     				+ 논리적으로 관계없는 것을 그룹핑가능
 */
public class OopEx7 {
	
	public static void main(String[] args) {
	
		// FinalCard 사용
		FinalCard c = new FinalCard(13, "HEART");
//		c.number = 10;	Error : The final field FinalCard.number cannot be assigned
		
		// 다형성 적용
		Unit[] movealbeUnit = new Unit[4];
		movealbeUnit[0] = new Bus();
		movealbeUnit[1] = new Airplane();
		movealbeUnit[2] = new Ship();
		
		// 동시에 이동
		for(Unit unit:movealbeUnit) {
			unit.move(3, 5);
		}
		
	}// end of main

}

/** 
 * final 활용1 : 레벨별 사용
 */
final class FinalTest {
	final static int MAX_SIZE = 100;
	
	// 파생 클래스에서 기반 클래스의 메서드를 사용하게 강제하고 싶을 때
	final void getMethod() {
		int lv = 10;
	}
}

/**
 * final 활용2 : 인스턴스별 상수를 다르게 초기화
 */
class FinalCard {
	
	/*
	 * final 멤버 변수
	 * 대상 : 카드 표면 무늬, 숫자
	 * 이유 : 카드 정보는 초기화된 후 변경되면 안됨 -> 생성자에서 초기화 수행
	 */
	final int number;
	final String kind;
	
	// 상수 초기화를 위한 생성자 -> 인스턴스별로 상수를 초기화해 사용가능
	public FinalCard(int number, String kind) {
		this.number = number;
		this.kind = kind;
	}
}

/**
 * abstract
 */
abstract class Unit{
  int x,y;
  
  abstract void move(int x, int y);
}

// 파생 클래스들은 이동하는 기능이 있는 클래스.. -> Unit을 상속받는게 논리적으로 합당
// 단, 이동에 대한 세세한 구현은 각 클래스별로 상세화해야 함
class Bus extends Unit {

	@Override
	void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}
  
}

class Airplane extends Unit {

	@Override
	void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}
  
}

class Ship extends Unit {

	@Override
	void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}
  
}


