package ch14;

/**
 * 1. 람다의 의미 및 특징
 * 
 * - 코드 상 람다의 이해
 * 	 
 * 	 int method(int i) {
 * 		return (int)(Math.random() * 5)+1+i;
 * 	 }
 * 
 * 	 -> 람다식
 * 		
 * 	 (int i) -> (int)(Math.random() * 5)+1+i;
 * 
 * 	 1. 기존 함수의 반환타입, 메서드명을 제거
 * 	 2. 매개변수와 몸통의 실행코드 사이에 '->' 
 * 
 * - 람다의 장점
 * 	 1. 간결, 이해
 * 	 2. 람다식 자체로 메서드 역할을 대신
 * 	 3. 메서드의 매개변수로 람다식 사용 가능
 * 
 * - 다양한 람다식의 형태
 *   반환값이 있는 메서드의 경우, return 문 대신 "식"으로 대신 가능
 *   -> 식의 연산결과가 자동적으로 반환됨
 *   -> 식의 끝에 ";" X
 *   
 *   람다식에서 매개변수의 타입이 없는 경우, 생략된 것으로 생각.
 *   추론이 가능함으로 생략하는 걸 권장
 * 
 * 	 int max(int a, int b) {
 * 		return a > b? a : b;
 * 	 }
 * 	 ->
 *   (a, b) -> a > b? a : b
 * 
 * 	 void printVar(String name, int i) {
 * 		System.out.println(name+"="+i);
 * 	 }
 * 	 ->
 * 	 (name, i) -> System.out.println(name+"="+i)
 * 
 * 	 int square(int x) {
 * 		return x * x;	
 *   }
 *   ->
 *   x -> x * x
 *  
 *   int roll() {
 *   	return (int)(Math.random() * 5);
 *   }
 * 	 ->
 * 	 () -> (int)(Math.random() * 5)
 * 
 * 	 int sumArr(int[] arr) {
 * 		int sum=0;
 * 		for(int i:arr) {
 * 			sum += i;
 * 		}
 * 		return sum;
 * 	 }
 *   ->
 *   arr -> { 
 *   			int sum=0;
 *   			for(int i:arr) sum += i;
 *   			return sum;
 *   		}
 * 
 * - 함수형 인터페이스
 * 	
 * 	 함수를 어떤 메서드의 매개변수로 활용하고 싶음
 * 	 매개변수에는 타입이 존재. 함수를 나타내는 타입의 필요성이 나옴
 * 
 *   타입 f = (a, b) -> a > b? a : b;
 *   
 *   람다식을 메서드라고 생각하고, 람다식을 호출해서 사용하려고 한다면,
 *   기존에는 객체를 생성해서 메서드 접근해서 사용함
 *   
 *   위의 람다식이 어떤 타입의 변수에 저장되려면, 
 *   람다식은 눈에는 보이지 않지만, 익명 클래스 안에 있는게 아닌가?? 
 *   
 *   (a, b) -> a > b? a : b
 *   
 *   new Object() {		// 익명 클래스
 *   	int (int a, int b) {
 *   		return a > b? a : b;
 *   	}
 *   }
 *   
 *   타입 f = (a, b) -> a > b? a : b;
 *   
 *   그렇다면, 위 f를 나타내는 타입이 현재 배운 범위에서 적용 가능한게 무엇인지?
 *   기본형은 아니고, 참조형이 되는게 논리적으로 맞음
 *   
 *   참조형중에서 어떤 것으로 타입을 하면 될까? : 인터페이스
 *   -> 함수형 인터페이스
 *   @FunctionalInterface
 *   interface Myfunction {
 *   	public abstract int max(int a, int b);
 *   }
 *   
 *   -> Myfunction 인터페이스를 구현한 익명 클래스(인스턴스)를 통해 추상 메서드 구현.
 *   MyFunction f = new Myfunction() {
 *   	public int max(int a, int b) {
 *   		return (a, b) -> a > b? a : b;
 *   	}
 *   }
 *   
 *   결론>
 *   Myfunction f = (a, b) -> a > b? a : b;
 *   int big = f.max(4,6);
 *   
 *   람다식을 어떤 메서드의 매개변수로 전달이 가능해짐
 *   
 *   MyFunction 인터페이스를 람다식으로 다루기 위한 인터페이스가 되고,
 *   이를 "함수형 인터페이스" 라고 함.
 *   -> 사용하기 위해선 구현해야 함
 *   	== 인스턴스를 생성해야 함 
 *   	-> 익명의 클래스로 만들자.
 *   	-> 근데 클래스 형식을 다 갖추는 모양으로 하면 코드가 너무 기니 람다식으로 바꾸자.
 *   
 * - 함수형 인터페이스의 제약
 * 	 함수형 인터페이스는 오직 하나의 추상 메서드만 정의되어 있어야 함 
 *   -> 람다식과 인터페이스가 1:1로 매핑되어 연결되기 때문
 *   
 *   하지만, static 메서드, default 메서드의 개수에는 제약 X
 *   
 *   @FunctionalInterface 사용 권장
 *   -> 함수형 인터페이스 형식을 따르고 있는지 확인해줌.
 *   
 * - 함수형 인터페이스 매개변수
 * 	 void aMethod(MyFunction f) {
 * 		f.max();
 * 	 }
 * 
 * 	 - 참조형 변수를 사용해서 메서드의 매개변수로 전달
 *   Myfunction f = (a, b) -> a > b? a : b;
 *   aMethod(f);
 *
 *   or
 *   
 *   - 바로 메서드의 매개변수로 람다식을 전달
 *   aMethod((a, b) -> a > b? a : b);
 *   
 * - 함수형 인터페이스의 반환형
 *   
 *   Myfunction myMethod() {
 *   	- 반환형을 변수로 받아서 전달
 *   	Myfunction f = () -> {};
 *   	return f;	
 *   
 *   	- 람다식으로 바로 전달
 *   	return () -> {};
 *   }
 *   
 */

@FunctionalInterface
interface MyFunction {
	public abstract void run();
}

public class LambdaEx1 {
	
	/* 함수형 매개변수를 활용하는 메서드 */
	static void exeucte(MyFunction f) {
		f.run();
	}
	
	/* 함수형 매개변수를 반환형으로 하는 메서드 */
	static MyFunction getMyFunction() {
		MyFunction f = () -> System.out.println("f.run()");
		return f;
	}

	public static void main(String[] args) {
		
		/* 1. 함수형 인터페이스로 람다식 선언 */
		MyFunction f1 = () -> System.out.println("f1.run()");
		
		/* 2. 익명 클래스 형태의 함수형 인터페이스 구현 */
		MyFunction f2 = new MyFunction() {
			
			// 함수형 인터페이스의 추상 메서드 구현
			@Override
			public void run() {
				System.out.println("f2.run()");
			}
		};
		
		/* 3. getMyFunction() 사용 */
		MyFunction f3 = getMyFunction();
		
		/* 4. 매개변수 메서드 execute() 사용 */
		// 4-1. 함수형 인터페이스를 매개변수로 전달 
		exeucte(f1);
		
		// 4-2. 함수형 인터페이스 없이 직접 람다식으로 전달
		exeucte(() -> System.out.println("run()"));

		
		f1.run();
		f2.run();
		f3.run();
	
	}

}
