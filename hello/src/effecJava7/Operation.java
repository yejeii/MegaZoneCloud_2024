package effecJava7;

import java.util.function.DoubleBinaryOperator;

public enum Operation {
	
	/* 람다 적용 전 */
//	PLUS("+") {
//		@Override
//		public double apply(double x, double y) { return x + y; }
//	},
//	MINUS("-") {
//		@Override
//		public double apply(double x, double y) { return x - y; }
//	},
//	TIMES("*") {
//		@Override
//		public double apply(double x, double y) { return x * y; }
//	},
//	DIVIDE("/") {
//		@Override
//		public double apply(double x, double y) { return x / y; }
//	};
	
	/* 람다 적용 후 */
	
	/* 1. 상수의 동작(메서드)을 람다식으로 구현 -> 생성자로 넘길 것 */
	PLUS("+", (x, y) -> x + y ),
	MINUS("-", (x, y) -> x - y ), 
	TIMES("*", (x, y) -> x * y ), 
	DIVIDE("/", (x, y) -> x / y );

	private final String symbol;
	private final DoubleBinaryOperator op;
	
	/* 2. 생성자 생성, 람다를 인스턴스 필드로 저장
	 * 람다식을 DoubleBinaryOperation 인터페이스 변수에 할당 
	 * DoubleBinaryOperation : 함수 인터페이스의 하나로, Double 타입 인수 2개를 받아 Double 타입 결과를 돌려줌
	 * 
	 * !생성자에 함수형 매개변수를 활용한 것!
	 * DoubleBinaryOperator op : 함수형 인터페이스로 받은 것
	 * DoubleBinaryOperator op = (x, y) -> x + y;
	 * -> op.applyAsDouble(x, y) 가능
	 */
	Operation(String symbol, DoubleBinaryOperator op) {
		this.symbol = symbol;
		this.op = op;
	}
	
	@Override
	public String toString() { return symbol; }
	
	// 3. 필드에 저장된 람다 호출
	public double apply(double x, double y) {
		return op.applyAsDouble(x, y);
	}
	
}
