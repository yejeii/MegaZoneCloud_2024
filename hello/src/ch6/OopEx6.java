package ch6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		for (Map.Entry<Integer, Products> pEntry : buyer.buyList.entrySet()) {
//			Integer cnt = pEntry.getKey();
//			Products product = pEntry.getValue();
			System.out.printf("%d번째 구매 : %s", pEntry.getKey(), pEntry.getValue());
			System.out.println();
			
		}
		
	}
}

/** 
 * 상품 정보에 대한 기반 클래스
 */
class Products {
	int price;
	int bonusPoint;
	
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
	int money = 4000;
	int bonusPoint = 0;
	int buycnt = 0;	// 상품 총 구매 개수
	
	Map<Integer, Products> buyList = new HashMap<>();
	
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
		
		buyList.put(buycnt, p);	// 1부터 저장
	}
	
	/*
	 * 구메 목록 조회
	 */
	Map<Integer, Products> getBuyList() {
		
		return this.buyList;
	}
}