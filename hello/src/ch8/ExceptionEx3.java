package ch8;

/**
 * Exception 위임 처리
 * 
 */
public class ExceptionEx3 {

	static void method1() throws Exception {	// 예외처리 위임
		method2();
	}
	
	static void method2() throws Exception {	// 예외처리 위임
		throw new Exception("method2(): 에러 발생");
	}
	
	public static void main(String[] args) {
		
//		method1(); Error : 비정상 종료 -> JVM에서 예외를 처리함
		
		// 예외처리 해결 : 위임 받은 예외를 처리함
		try {
			method1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
