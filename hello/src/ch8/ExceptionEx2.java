package ch8;

/**
 * - Object
 *   - Throwable
 *     	- Exception : Checked 예외
 * 			- RuntimeException : Unchecked 예외
 * 
 * - 사용시 Exception의 분류
 *   - Exception : Checked 예외
 *   			   컴파일러가 예외처리 확인
 *   
 *   - RuntimeException : Unchecked 예외
 *   					  컴파일러가 예외처리 확인 X (코드가 실행 중에 에러를 만난 것)
 *   					  ex) 0으로 나누는 코드 
 *   
 */
public class ExceptionEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/* catch 분기 처리 : 다형성 처리 */
		try {
			System.out.println(3);
			System.out.println(0/0);
			System.out.println(4);
		} catch (ArithmeticException e) {
			if(e instanceof Exception) System.out.println("ArithmeticException can cast to Exception class");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		// try-catch문 : 정상종료를 목표 -> 에러가 발생해도 아래 코드까지 실행되는 것
		System.out.println(5);
		
		System.out.println("프로그램 정상 종료.");
		
		
		/* 개발자가 필요에 의해 예외를 발생시키는 경우 : 사용자 정의 예외
		 * 1. Exception 클래스 인스턴스 생성
		 * 2. 해당 인스턴스 던져.. throw
		 */
		try {
//			Exception ue = new Exception("고의로 예외 발생");
//			throw ue;
			
			throw new Exception("고의로 예외 발생!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* RuntimeException */
		try {
			throw new RuntimeException();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
