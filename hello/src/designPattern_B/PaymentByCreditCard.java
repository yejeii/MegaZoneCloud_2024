package designPattern_B;

public class PaymentByCreditCard implements PaymentStrategy {

	private CreditCard card;

	@Override
	public void collectPaymentDetails() {
		CreditCard card = new CreditCard("cardNumber", "expiryDate", "cvv");
		
	}

	@Override
	public boolean validatePaymentDetails() {
		// 신용카드 유효성 검사 (생략)
		return false;
	}
	
	@Override
	public void pay(int amount) {
		System.out.printf("Paying %m using Credit Card", amount);
		card.setAmount(card.getAmount() - amount);
	}

}
