package designPattern_B;

/**
 * PaymentServiceB 클래스의 문제 수정
 * 
 * 해당 클래스는 Strategy 인터페이스를 사용하기 때문에 결제가 어떤 방식으로 진행되는지 알지 못한다.
 * 클라이언트가 사용하려는 전략(결제 수단)을 해당 클래스에 전달만 잘 해주면 알아서 찾아가게 된다. : 상호 교환
 * (런타임 시, 전략이 쉽게 교체된다)
 *   
 * >정리<
 * Strategy Pattern.
 * 1. Defines a family of behaviors, puts each of them into a separate class, and makes their objects interchangeable.
 * 2. On top of reducing code duplication, by doing this your several behaviors or strategies will be easily replaceable
 *    and interchangeable by clients at runtime.
 * 3. By following the strategy pattern, you will be applying the single responsibility and the open-closed principles.
 *    Because each strategy is isolated in a separate class, 
 *    and you now have the ability to introduce new strategies without modifying the existing strategy classes nor the context.
 * 
 */
public class PaymentServiceA {

	private int cost;
	private boolean includeDelivery;
	
	private PaymentStrategy strategy;
	
	public void processOrder() {
		
		// 결제 정보 저장
		strategy.collectPaymentDetails();
		
		// 유효성 검사 및 결제 진행
		if(strategy.validatePaymentDetails()) {
			strategy.pay(getTotal());
		}
	}
	
	private int getTotal() {
		return includeDelivery ? cost + 10 : cost;
	}
	
	// 전략(결제 수단) 설정
	public void setStrategy(PaymentStrategy strategy) {
		this.strategy = strategy;
	}
	
	
}
