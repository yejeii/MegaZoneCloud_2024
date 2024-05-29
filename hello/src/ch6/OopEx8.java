package ch6;

/**
 * ========================= OOP의 4대 특성 =============================
 * 
 * - 캡슐화 
 * 	 데이터를 외부에서 함부로 변경하지 못하도록 하기 위해 접근을 제어하는 것 
 * 	 데이터 감추기(data hiding)
 * 
 * 	 외부에는 불필요한, 내부적으로만 사용되는, 부분을 감추기 위함 복잡성을 줄임
 * 
 * 	 문법적 : private 데이터 사용 -> getter, setter, 필요한 비즈니스용 메서드
 * 			private 생성자 -> 인스턴스를 내부에서 생성하는 목적 -> 싱글톤 : getInstance()
 * 
 * - 상속 
 * 	 기존의 클래스를 재사용해 새로운 클래스를 작성하는 것(기반/파생 클래스)
 * 	 상속의 원칙은 단일 상속(단일 상속을 대신해서 확장 : 추상클래스, 인터페이스) 
 *   클래스 간의 관계에 대한 논리성을 고려 : Is-A(상속), Has-A(포함) 관계 -> 다중상속 같은 용도
 * 
 * 	 문법적 : extends (단일 상속에서의 확장 : implements) 
 * 			생성자와 초기화 블록은 상속되지 않음. 멤버만 상속됨!!
 * 
 * - 추상화 클래스간의 공통점을 찾아내서 공통의 기반을 만드는 작업 추상 클래스는 추상 메서드가 존재하는 클래스(추상 메서드는 선언만 된
 * 상태. 몸통이 없는 상테)
 * 
 * 비행기, 선박, 버스의 공통점 : 이동하는 기능 -> 모두 구현해야 할 부분이기에 기반 클래스에 정의
 * 
 * 추상화의 반대는 구체화 == 상속을 통한 클래스 확장
 * 
 * 문법적 : extends (인스턴스로 사용 가능한 기반 클래스 or 인스턴스로 사용 불가능한 기반 클래스(추상 클래스))
 * 
 * - 다형성 단어적 의미 : 여러 형태를 가질 수 있는 능력 또는 특성 자바에서의 의미 : 기반 클래스 타입의 참조 변수로 파생클래스의
 * 인스턴스를 참조할 수 있도록 하는 것 -> 인스턴스의 그룹핑이 가능해짐 -> 관리가 편해짐(10개의 메서드를 하나의 메서드로 관리)
 * 
 * 문법적 : 형변환 연산자, instanceOf
 * 
 * ========================= Interface =============================
 * 
 * - Interface 
 * 	 일종의 추상클래스.. 추상클래스처럼 추상메서드를 갖지만 추상클래스보다 추상화 정도가 높음
 * 
 * 	 추상 메서드 지원 
 * 	 -> 메서드 구현 강제, 다중 상속처럼 사용 가능(다형성) 
 * 	 -> 2개 이상의 인터페이스를 사용 가능 다형성 가능(논리적이지 않은 것까지 묶을 수 있음) 
 * 	 -> 하나의 타입으로 뭉뜽그려 표현 가능 == 코드의 반복 지양
 * 
 * 	 추상 클래스 vs 인터페이스 
 * 	 추상 클래스 : extends(상속) == 논리적인 관계성이 있어야 함 -> override 
 *   인터페이스 : 클래스의 부족한 부분인 단일 상속, 관계성 있는 것들끼리만 다형성 적용할 수 있다는 점을 해결하기 위해 등장 
 *   		   implements(구현)
 * 			   -> 다중상속처럼 사용 가능(다형성) 
 * 			   -> 관계없는 것끼리 다형성 적용 가능.. 
 * 			   -> 기능을 묶어서 해당 기능타입으로 다형화 가능 아래
 * 				  스타크래프트 예시에서, repair의 인자 Repariable형은 SCV, Tank, Dropship이 구현했음 
 * 				  즉, 인자로 Repairable형이 가능한 SCV, Tank, Dropship 클래스만 허락한다는 것 
 * 				  특정 기능을 만든 인터페이스형 타입만으로도 접근제한이 가능하게 됨!
 *
 * 	 다형성 : Bus, Airplane, Ship의 공통점 : 이동체 공격 기능은 없지만, 만약 공격 interface를 추가한다면,,
 * 
 * 			어떤 Airplane은 공격 interface를 구현 X : 여객기 
 * 		    어떤 Airplane은 공격 interface를 구현 : 전투기
 * 
 * 			어떤 Ship은 공격 interface를 구현 X : 여객선 
 * 			어떤 Ship은 공격 interface를 구현 : 함선
 * 
 * 			인터페이스를 기준으로 관계없는 것들도 세분화해서 다형성 적용 가능 
 * 			-> 필요한 기능이 있는 인터페이스만 끼워넣고 구현만 하면 되는 편리함!
 * 
 * 			- 전투와 관련된 다형성 
 * 			- 전투와 관련되지 않은 다형성 
 * 			- 이동과 관련된 다형성
 * 
 * - interface의 제약사항 
 * 		- 모든 멤버변수는 public static final이어야 함(생략 불가) 
 * 		- 모든 메서드는 public abstract 이어야 함(생략 불가) 
 * 		  단, static 메서드와 default 메서드는 예외
 * 
 * - interface의 상속 
 * 		- 인터페이스는 인터페이스로부터만 상속받을 수 있음(extends) 
 * 		- 다중 상속 가능
 * 
 * - interface의 구현 
 * 		- 인터페이스의 추상 메서드를 구현하는 클래스에서 implement를 사용해 구현
 * 
 * - interface의 장점
 * 	 1. 개발 시간 단축 or 독립적인 프로그래밍이 가능 : 생산성
 *   2. 표준화가 가능 : 생산성
 *   3. 관계가 없는 것끼리 맺어줄 수 있음
 * 
 * ========================= Interface 예제 ===========================
 * 
 * 인스턴스 예제 : Marine, SCV, Tank, Dropship
 * 
 * <클래스간의 관계> 
 * Unit -> GroundUnit -> Marine, SCV, Tank 
 * 		-> AirUnit -> Dropship
 * 
 * 		-> Building
 * Unit -> Academy, Buncker, Barrack(공중부양 가능), Factory(공중부양 가능)
 * 
 * 1. SCV 클래스 구현 
 * 	- 기능 : 수리 
 * 	- 대상 : Tank, SCV, Dropship : 클래스와는 무관한 관계이나 긱 대상에 대한 수리 메서드가 나와야 함 -> 메서드 3개 
 * 								  -> '수리' 메서드에 다형성 적용 
 * 								  -> 수리 대상과 관련된 공통 부분을 인터페이스로 만들어서 사용.
 * 2. Building에서 공중으로 띄우는 공통 능력 구현(인터페이스 디자인)
 * 
 * 3. Barrack, Factory의 클래스 작성(인터페이스 구현) 
 * 	  Barrack : Marine 생산 
 * 	  Factory : Tank 생산
 * 
 * 	  이륙, 이동, 정지, 착륙
 * 
 * 	  class Barrack { 
 * 		이륙, 이동, 정지, 착륙 method 구현 -> 코드 중복 
 * 		이륙and이동 메서드 -> 향후 신기능... : 기본 메서드에서 확장되는 기능 
 *	  }
 * 
 * 	  class Factory { 
 * 		이륙, 이동, 정지, 착륙 method 구현 -> 코드 중복 
 * 		이륙and이동 메서드 -> 향후 신기능... : 기본 메서드에서 확장되는 기능 
 * 	  }
 * 
 * <고려사항> 
 * 1. 새로운 건물이 추가될 경우 
 * 2. 이륙, 이동, 정지, 착륙의 기본 메서드의 재활용, 기능 구현의 강제성 
 * 3. 유지보수 및 확장성을 위해
 * 
 * 
 * 
 */

/*
 * A-B 강한 결합 상태 
 * 다른 쪽의 코드가 완성될 때까지 기다려야 함 : 생산성 저하 & 독립적 개발 X
class A {
	public void methodA(B b) {
		b.methodB();
	}
}

class B {
	public void methodB() {
		System.out.println("MethodB");
	}
}
*/

/*
 * 강한 결합을 약한 결합으로 변경해보자(결합도 low)
 * -> 인터페이스를 활용하면, class A에 영향이 가지 않게 됨
 * -> 클래스 A를 구현하는 팀원이 클래스 B의 완성을 기다리지 않아도 되는 것 : 독립적 프로그래밍이 가능해짐 + 유지보수성
 * 
 */
class A {
	public void methodA(I i) {	// 클래스 B를 주석처리해도 A에 영향 X
		i.methodB();
	}
}

/* 
class B implements I {

	// 표준화 : 기본 틀 제공 == 일관, 정형화된 개발
	@Override
	public void methodB() {
		// TODO Auto-generated method stub
		System.out.println("MethodB");
	}
	
}
*/

interface I {
	public abstract void methodB();
}

public class OopEx8 {

	public static void main(String[] args) {

		/*
		 * 구체화된 Fighter 클래스 사용 다형성이 적용되었는지 확인
		 * 
		 * 클래스 간의 관계가 있는 다형성(Object, Unit) -> Vector, 배열에서의 Unit 관리목적의 다형성 (확인 완료)
		 * 
		 * 클래스 간의 관계가 없는 다형성(Moveable, Attackable, Fighable) -> 메서드의 매개변수, 반환형 다형성 적용 가능
		 * (확인 대상) -> 클래스 간 관계없는 것들도 다형성 적용 가능
		 */

		Fighter f = new Fighter(0);

		if (f instanceof Fighable) {
			System.out.println("Fighable type!");
		}
		if (f instanceof Moveable) {
			System.out.println("Moveable type!");
		}
		if (f instanceof Attackable) {
			System.out.println("Attackable type!");
		}

		/*
		 * Fighter t는 Fightable, Moveable, Attackable 인터페이스로 다형성 적용됨 
		 * -> Fightable 인터페이스가 Moveable, Attackable 인터페이스를 상속받았기 때문(상속 == 재산 물리기) 
		 * -> 클래스 간 관계없는 것들도 묶을 수 있는것
		 */

		// ------------- 1 번 문제 동작 테스트 --------------
		Tank tank = new Tank();
		Dropship dropship = new Dropship();
		SCV scv = new SCV();

		Marine marine = new Marine();

		// Tank 수리 테스트
		tank.currentUP = 10; // 임의

		// 인자 대상 확인 : Marine 불가(Reparable 구현 X. 즉, 수리 관계를 맺지 않은 상태로 SCV가 수리할 대상이 안됨!!)
//		scv.repair(dropship);
//		scv.repair(scv);
		scv.repair(tank);
//		scv.repair(marine);	Error : The method repair(Repairable) in the type SCV is not applicable for the arguments 

		System.out.println("수리 후 : " + tank.currentUP);

		// -------------- 2, 3번 동작 테스트 -----------------

		// Barrack, Factory
		Barrack barrack = new Barrack();
		barrack.liftOff();
		barrack.land();

		Factory factory = new Factory();
		factory.liftOff();
		factory.land();
	}
}

/**
 * 기반 클래스
 */
class Unit2 {
	int currentUP; // 유닛의 에너지, 부족하면 수리받아야 함
	int x; // 이동좌표 x
	int y; // 이동좌표 y
	final int MAX_HP;

	/*
	 * Unit 별로 최대 체력이 다르기 때문에 생성자에서 상수인 MAX_XP를 초기화하도록 함
	 */
	public Unit2(int hp) {
		this.MAX_HP = hp;
	}
}

class GroundUnit extends Unit2 {

	public GroundUnit(int hp) {
		super(hp);

	}
}

class AirUnit extends Unit2 {

	public AirUnit(int hp) {
		super(hp);
	}
}

/**
 * 인터페이스(이동, 공격) Unit의 이동 능력 표현
 */
interface Moveable {
	void move(int x, int y);
}

/**
 * Unit의 공격 능력 표현
 */
interface Attackable {
	/*
	 * 스타크래프트에서 모든 대상은 공격의 대상 모든 대상이 포함되게 관계도상의 최상위 기반 클래스 Unit으로 다형성 적용
	 */
	void attack(Unit u);
}

/**
 * 인터페이스의 상속 Fight : 실제로 공격하는 것 - 이동과 공격이 모두 사용되어야 함 Unit의 이동, 공격 능력을 표현
 */
interface Fighable extends Moveable, Attackable {

}

interface Repairable {
}

// --------------- 추상화 레벨(기반 클래스, 인터페이스, 추상 메서드) -------------- 

// --------------- 구체화 레벨(공격자 클래스) --------------

/**
 * class 상속(다형성) & interface 구현(기능 강제)
 */
class Fighter extends Unit2 implements Fighable {
	
	public Fighter(int hp) {
		super(hp);
		// TODO Auto-generated constructor stub
	}

	@Override 
	public void move(int x, int y) { // TODO Auto-generated method stub
		System.out.println("Fighter.move!");
	}

	@Override
	public void attack(Unit u) {
		// TODO Auto-generated method stub
		System.out.println("Fighter.attack!");
	}

}

// ----------------- 1번 문제 ----------------------
class Tank extends GroundUnit implements Repairable {

	public Tank() {
		super(100);
		this.currentUP = this.MAX_HP;
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Tank";
	}

}

class Dropship extends AirUnit implements Repairable {

	public Dropship() {
		super(130);
		this.currentUP = this.MAX_HP;
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Dropship";
	}
}

// Marine : medic에서 치료받아야 하므로 Repairable의 구현대상 X
class Marine extends GroundUnit {

	public Marine() {
		super(20);
		this.currentUP = this.MAX_HP;
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Marine";
	}
}

class SCV extends GroundUnit implements Repairable {

	public SCV() {
		super(70); // 부모의 MAX_HP가 70으로 초기화
		this.currentUP = this.MAX_HP; // 처음 HP를 MAX값으로 설정
	}

	/*
	 * 메서드 구현: 수리 대상만 수리되도록 : Repairable 타입 참조변수 r의 대상 : Tank, Dropship, SCV 참조타입을
	 * Unit 지양하는 이유 : 수리에 필요한 대상만 명확히 구분하기 위함
	 */
	void repair(Repairable r) {

		// r이 Unit2 타입인지 한번 더 확인
		if (r instanceof Unit2) {
			// 체력 인스턴스 변수에 접근 필요
			// 인스턴스 변수는 타입을 따라기므로 Unit2로 캐스팅
			Unit2 u = (Unit2) r;

			// 체력 회복
			while (u.currentUP != u.MAX_HP) {
				u.currentUP++;
			}

			System.out.println(u.toString() + " 의 수리 완료!");
		}
	}
}

// ----------------- 2, 3번 문제 --------------------
class Building {
}

/**
 * 1. 이륙 가능한 건물의 타입으로 사용하려는 목적 
 * 2. 이륙 가능한 건물 클래스가 새롭게 추가될 경우, 이륙, 이동, 정지, 착륙 기능의
 * 강제 구현을 시키기 위함
 */
interface Liftable {
	public abstract void liftOff();

	public abstract void move();

	public abstract void stop();

	public abstract void land();
}

// 구체화 클래스 : Barrack, Factory
// 구체화 시, 코드의 중복성이 보임...유지보수 및 확장성을 생각해야 함
// -> 응집도 높이고 결합도 낮추려면 어떻게 해야할까?

// 해결 방향
// 1. 공통 또는 반복되는 코드를 별도로 뽑아내야 함
// 	  4가지 메서드가 한 곳에 있도록 해야 함 : 응집도 높이기
// 2. 뽑아낸 코드를 쉽게 사용할 수 있도록 해야 함
// 3. 뽑아낸 곳의 코드에 기능 추가, 성능 개선 등 요구사항을 반영하더라도 사용되는 곳에 영향이 가면 안됨 : 결합도 낮추기
// -> Liftable을 구현한 클래스 따로 생성

/**
 * 응집도 높아짐 여기서 수정이 발생하더라도 이 메서드를 사용하는 다른 클래스에선 코드 수정 영향 X
 */
class LiftableImpl implements Liftable {

	@Override
	public void liftOff() {
		// TODO Auto-generated method stub
		System.out.println("이륙");
		System.out.println("이륙 중");
		System.out.println("이륙 완료");
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("이동");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("정지");
	}

	@Override
	public void land() {
		// TODO Auto-generated method stub
		System.out.println("착륙");
	}

}

class Barrack extends Building implements Liftable {

	// 건물의 이동과 관련된 기능의 인스턴스만 생성 후, 필요한 기능의 메서드만 선택적 호출 -> 사용의 편리성
	LiftableImpl l = new LiftableImpl();

	@Override
	public void liftOff() {
		// TODO Auto-generated method stub
		l.liftOff();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		l.move();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		l.stop();
	}

	@Override
	public void land() {
		// TODO Auto-generated method stub
		l.land();
	}

}

class Factory extends Building implements Liftable {

	LiftableImpl l = new LiftableImpl();

	@Override
	public void liftOff() {
		// TODO Auto-generated method stub
		l.liftOff();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		l.move();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		l.stop();
	}

	@Override
	public void land() {
		// TODO Auto-generated method stub
		l.land();
	}

	// 확장 및 재활용
	public void liftOffAndMove() {
		this.liftOff();
		this.move();
	}

}
