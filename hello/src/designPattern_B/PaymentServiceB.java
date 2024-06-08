package designPattern_B;

/**
 * 결제수단에 따른 결제를 진행하는 클래스
 * 
 * proessOrder() 에서 신용카드 결제 뿐 아니라 PayPal, 카카오페이 등 기타 여러 결제 수단이 추가된다고 가정해보자.
 * -> 기존 코드를 if 문이나 switch-case문으로 복제하는 방식으로 구현해야 할 것이다. 
 * -> 시간이 지나면 ... 더 다양한 결제 방식을 도입하는 데 있어 코드 유지보수의 어려움을 겪을 것이다.
 * 
 * -> OCP 원칙에 위반하는 코딩!! ( Open for extension and Closed for modification )
 * 	  Because every time a change is required, we are going to have to open this method and modify it.
 * 	  And that is obviously not what is stated by the open-closed principle we all know.
 * 
 * + SRP 원칙에도 위반하고 있다 ( Single Responsibility Principle)
 * 해당 클래스는 몇 개의 기능을 담당하고 있는데,
 * 신용카드 및 PayPal, KakaoPay 결제 기능은 SRP 에 위배된다.
 * 
 * 
 * >해결 방법< ( Strategy 전략이 필요한 이유 )
 * 	1. 결제 방법을 각자의 클래스에 배치하여 하나의 클래스가 하나의 결제 방법만 담당하도록 하자.
 * 	2. 사용자가 어떤 결제 방법을 사용할 지 모르기 때문에, 이러한 클래스는 서로 쉽게 교체될 수 있어야 한다.
 * 	   -> Strategy Design Pattern 을 적용하자!
 * 
 * >Strategy Design Pattern<
 * 전략 디자인 패턴을 사용하면,
 * 알고리즘 계열을 정의하고 각 알고리즘을 별도의 클래스에 분리시킬 수 있으며, 이들 객체를 서로 교환 할 수 있게 한다.
 * 
 */
public class PaymentServiceB {

	private int cost;
	private boolean includeDelivery;
	
	// 문제가 있는 메서드
	public void proessOrder(String paymentMethod) {
		
		if("CreditCard".equals(paymentMethod)) {

			// 신용카드 정보 저장
			CreditCard card = new CreditCard("cardNumber", "expiryDate", "cvv");
			
			// 신용카드 유효성 검사 (생략)
			
			// 결제 진행
			System.out.printf("Paying %m using Credit Card", getTotal());
			card.setAmount(card.getAmount() - getTotal());
		} else if ("PayPal".equals(paymentMethod)) {
			
			// PayPal 계졍 정보 저장
			String email = "...";
			String pw = "...";
			
			// 계정 유효성 검사
			
			// 결제 진행
			System.out.printf("Paying %m using PayPal", getTotal());
		} else if ("Kakao Pay".equals(paymentMethod)) {
			
			// Kakao Pay 계졍 정보 저장
			String email = "...";
			String pw = "...";
			
			// 계정 유효성 검사
			
			// 결제 진행
			System.out.printf("Paying %m using Kakao Pay", getTotal());
		}
		
		
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setIncludeDelivery(boolean includeDelivery) {
		this.includeDelivery = includeDelivery;
	}
	
	private int getTotal() {
		return includeDelivery ? cost + 10 : cost;
	}
	
	
	
	
}
