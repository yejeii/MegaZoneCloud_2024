package designPattern_B;

public class PaymentByPayPal implements PaymentStrategy {

	// PayPal 계졍 정보
	private String email;
	private String pw;

	@Override
	public void collectPaymentDetails() {
		email = "...";
		pw = "...";
		
	}

	@Override
	public boolean validatePaymentDetails() {
		// 계정 유효성 검사
		return false;
	}
	
	@Override
	public void pay(int amount) {
		System.out.printf("Paying %m using PayPal", amount);
	}

}
