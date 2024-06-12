package gof_StrategyP;

/**
 * The Strategy Pattern Explained and Implemented in Java | Behavioral Design Patterns | Geekific
 * https://youtu.be/Nrwj3gZiuJU?si=-zYGay6-OH4yjKAz
 * 
 * @author yesie
 */
public class StrategyPattern {

	public static void main(String[] args) {
		PaymentServiceA service = new PaymentServiceA();
		
		// The strategy can now be easily picked at runtime.
		service.setStrategy(new PaymentByCreditCard());
		service.processOrder();
	}

}
