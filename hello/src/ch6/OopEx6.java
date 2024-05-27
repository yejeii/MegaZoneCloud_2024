package ch6;

import java.util.Vector;
import java.util.function.BiConsumer;

/**
 * ======================== 다형성 적용 =============================
 * 
 * - 매개변수의 다형성
 *   쇼핑몰 시스템 구축, 상품정보, 회원
 *   
 *   상품 정보 : 기반 클래스, 카메라, 컴퓨터
 *   회원 정보 : 결제, 구매 메서드
 *   		  구매(상품) -- 구매(카메라), 구매(컴퓨터)
 *   
 *   		  구매(상품) -> 메서드의 매개변수 다형성 구현
 *   
 *   -> JAVA만 사용된 비즈니스 로직이 됨
 *   
 *   최종 목표 : AWS에서 SaaS 운영 
 *   		  클라딴(React), 서버(Spring boot, mysql, jpa) 등등
 *   
 *   
 */
public class OopEx6 {

	public static void main(String[] args) {
		
		/* 
		 * 구매 실행
		 * 1. 상품, 구매자 인스턴스 생성
		 * 2. 구매자가 상품 구매
		 * 3. 구매 가능/불가능 조회
		 * 4. 구매 리스트 조회 : 상품 정보가 필요
		 */
		Buyer buyer = new Buyer();
		
		// 3번의 구매 시도 : 상품은 다르나 동일한 구매 메서드 사용
		// buy 매개변수에 상품의 기반 클래스 Products 타입으로 선언되었으므로.
		buyer.buy(new Camera());	// up casting : 부모의 것을 쓰겠다!(부모의 멤버 변수를 사용해야 하므로)
		buyer.buy(new Computer());
		buyer.buy(new Computer());
		
		System.out.println();
		
		// 구매 완료 후 구매 목록 출력
		buyer.getBuyList();
		System.out.println();
		
		// 반품 : index를 삭제키로 PK키로 생각
		buyer.refund(1);
		System.out.println();
		
		// 구매자의 잔고 확인
		System.out.printf("구매자 잔고 현황 : %d, %d", buyer.getMoney(), buyer.getBonusPoint());
		
		
	}
}

/** 
 * 상품 정보에 대한 기반 클래스
 */
class Products {
	int price;
	int bonusPoint;
	
	
	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getBonusPoint() {
		return bonusPoint;
	}


	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}


	Products(int price) {
		this.price = price;
		this.bonusPoint = (int) (price * 0.1);
	}
}

/**
 * 상품 : 카메라
 * is-a 관계 : 카메라는 상품이다
 */
class Camera extends Products {
	
	Camera() {
		super(1000);
	}

	@Override
	public String toString() {
		return "Camera [price=" + price + ", bonusPoint=" + bonusPoint + "]";
	}

}

/**
 * 상품 : 컴퓨터
 */
class Computer extends Products {

	Computer() {
		super(2000);
	}

	@Override
	public String toString() {
		return "Computer [price=" + price + ", bonusPoint=" + bonusPoint + "]";
	}
}

/** 
 * 구매자(회원)
 */
class Buyer {
	/*
	 * 캡슐화를 적용하게 되면 멤버변수는 외부에서 접근 불가 -> getter, setter를 통해 접근
	 */
	int money = 4000;
	int bonusPoint = 0;
	int buycnt = 0;		// 상품 총 구매 개수
	
	/* 
	 * 문제. 현재 구매 후에는 소유할 수 없는 상태.. 
	 * 구매한 객체를 확인하고 싶음 : 객체 배열 활용
	 * 
	 * 객체 배열의 단점 : 구매할 수 있는 상품의 가지수가 제한됨
	 * -> 구매 개수에 대한 제한이 없도록 하기위한 방법 모색하고자 함
	 * 
	 * -> 다형성을 적용한 객체 배열 생성
	 */
//	Products[] list = new Products[10];		// 배열길이를 명시적으로 초기화해야 하는 불편함... 
	Vector<Products> buyList = new Vector<>();	
	// Vector : 저장공간이 자동으로 증가/줄어드는 자료구조
	// 초기화시 10개의 인스턴스가 저장되는 공간 생성. 
	
	/*
	 * 비즈니스 로직
	 * 구매 상품 목록에 대한 CRUD가 가능해야 함
	 * Vector - Vector.add, Vector.remove, Vector.get, Vector.remove()에 해당
	 */
	
	/*
	 * 구매 비즈니스 로직
	 * 1. 매개변수 다형성 적용 
	 * 2. 구매 가능한지를 확인 : 금액 확인
	 * 		- 구매 불가/가능
	 * 3. 구매한 경우, 잔액 변경
	 * 4. 보너스 포인트 지급
	 * 5. 구매 성공했음을 알림
	 * 
	 */
	void buy(Products p) {
		
		// 1. 인스턴스의 클래스 타입 확인
		if (p instanceof Products) {
			p = (Products) p;
			System.out.println(p.getClass());
		}
		
		if(this.money < p.price) {
			System.out.println("잔액이 상품 금액보다 적습니다!");
			return;
		} else {
			// 구매 가능한 경우
			this.buycnt++;
			this.money -= p.price;
			this.bonusPoint += p.bonusPoint;
			
			System.out.printf("구매되었습니다! 잔액 : %d, 잔여 포인트 : %d", this.money, this.bonusPoint);
			System.out.println();
			System.out.println("---------------------------");
		}
		
		buyList.add(p);
	}
	
	public int getMoney() {
		return money;
	}

	public int getBonusPoint() {
		return bonusPoint;
	}

	public int getBuycnt() {
		return buycnt;
	}

	/*
	 * 구매 목록 조회
	 */
	public void getBuyList() {
		if(this.buyList.isEmpty()) {
			System.out.println("구매 이력이 없습니다.");
			return;
		}
		
		int sum = 0;
		String buyList = "";
		
		for(int i=0; i<this.buyList.size(); i++) {
			// 형변환 : Object -> Products
			Products products = this.buyList.get(i);
			sum += products.price;
			buyList += (i==0) ? "" + products : ", " + products;
		}
		System.out.println("총 구매 금액 : " + sum);
		System.out.printf("구매 목록 \n " + buyList);
		System.out.println();
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public void setBuycnt(int buycnt) {
		this.buycnt = buycnt;
	}

	public void setBuyList(Vector<Products> buyList) {
		this.buyList = buyList;
	}
	
	/*
	 * 구매한 상품 반품
	 */
	// ex. 1 삭제
	public void refund(int idx) {
		
		for(int i=0; i<this.buyList.size(); i++) {
			if(idx==i+1) {
				// 삭제
				this.buyList.remove(i);
				
				// 구매자 금액 재설정
				Products removeP = this.buyList.get(i);
				System.out.println("반품할 제품 : " + removeP);

				this.money += removeP.getPrice();
				this.bonusPoint -= removeP.getBonusPoint();
				
			}
			break;	
		}

		System.out.println("반품 완료!");
		getBuyList();
		
	}

}