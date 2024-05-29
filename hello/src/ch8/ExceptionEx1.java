package ch8;

/**
 * =================== Exception =======================
 * 
 * - 에러의 종류 : 컴파일 에러, 런타임 에러, 논리 에러
 * 	 컴파일 에러 : 컴파일시 발생하는 에러
 * 	 런타임 에러 : 실행시 발생하는 에러
 * 	 논리 에러 : 실행시 의도와 다르게 동작하는 에러
 * 
 * - ERROR vs Exception
 * 	 Error : 코드로 해결할 수 없는 심각한 오류
 * 	 Exception : 코드로 해결할 수 있는 다소 미약한 오류(컴파일 에러 X)
 * 				 일어날 수 있는 오류에 대비헤 JAVA에서 제공하는 문법을 통해 예외처리
 * 
 * - 예외 처리의 목적
 *   프로그램의 비정상 종료를 막고, 정상적인 실행상태를 유지(중요)
 *   
 * - 문법
 *   try-catch-finally
 *   
 * 
 * 
 */
public class ExceptionEx1 {
	
	public static void main(String[] args) {
		int num = 100;
		int result = 0;
		
		for(int i=0; i<10; i++) {
			try {
				result = num / (int)(Math.random()*10);	
				System.out.println(result);
			}
			catch (Exception e) {
				System.err.println("ERROR Occured");
				e.printStackTrace();
			} 
		}
	}

}
