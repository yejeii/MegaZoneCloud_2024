package gof_StrategyP;

/**
 * Apply Strategy Pattern : Using Interface and separate class
 * 
 * Extract the collectPaymentDetails(), validatePaymentDetails(), pay()
 * This will grant us more flexibility later on while shaping the skeleton of the common algorithm 
 * all these strategies should make use of.
 */
public interface PaymentStrategy {

	/* 결제 수단 정보 저장 */
	void collectPaymentDetails();
	
	/* 결제 정보 유효성 검사 */
	boolean validatePaymentDetails();
	
	/* 결제 진행 */
	void pay(int amount);
}
