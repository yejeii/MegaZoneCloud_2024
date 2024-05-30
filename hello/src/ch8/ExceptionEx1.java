package ch8;

/**
 * =================== Exception =======================
 * 
 * - 에러의 종류 : 컴파일 에러, 런타임 에러, 논리 에러 
 * 	 컴파일 에러 : 컴파일시 발생하는 에러 
 *   런타임 에러 : 실행시 발생하는 에러
 *   논리 에러 : 실행시 의도와 다르게 동작하는 에러
 * 
 * - ERROR vs Exception 
 * 	 Error : 코드로 해결할 수 없는 심각한 오류 
 *   Exception : 코드로 해결할 수 있는 다소 미약한 오류(컴파일 에러 X) 
 *   			 일어날 수 있는 오류에 대비헤 JAVA에서 제공하는 문법을 통해 예외처리
 * 
 * - 예외 처리의 목적
 *   프로그램의 비정상 종료를 막고, 정상적인 실행상태를 유지(중요)
 * 
 * - 문법 
 *   try-catch-finally
 * 
 *   try { 
 *   	예외 발생 가능성이 있는 코드 
 *   } catch(Exception e) { 
 *   	예외 처리 코드 작성 
 *   } finally { 
 *   	예외가 발생하던, 발생하지 않던 처리할 코드 작성 
 *      ex. 프로그램 설치시 사용했던 설치 파일 삭제 
 *          자원해제시 사용되는 메서드 close()
 * 
 *     모든 OS 입장에서의 자원에 대해서는 대부분 close() 함수 또는 메서드가 있음 
 *     자원 : File, Socket, DB 등
 * 
 * 	   고려사항> 
 *     자원 해제시, Finally의 복잡도가 높아질 가능성이 있음 
 *     -> 해결> try... with... resources...
 * 	   -> 해결을 위한 구문을 사용하기 위해선 AutoCloseable 인터페이스 구현해야 함
 * 	   -> ExceptionEx5.class 참고
 * 
 *     자원해제. 
 *     가능한 경우, 메모리에 자원 로딩이 되어 있는 상태인 경우 자원의 인스턴스가 null인지 체크한 후, null이면 close() 호출
 * 
 */
public class ExceptionEx1 {

	public static void main(String[] args) {
		
		int num = 100;
		
		ExceptionEx1.method1(num);
		System.out.println("method1() 호출 종료");
		System.out.println("method1() 정상 종료");
	}

	static void method1(int num) {
		System.out.println("method1() 호출됨");
		
		int result = 0;
		
		try {
			for (int i = 0; i < 10; i++) {
				result = num / (int) (Math.random() * 10);
				System.out.println(result);
			}
			
			// return 이 있어도 finally문 반드시 실행
			// 즉, 예외 유무와 관계없이 finally는 수행됨
			return;
		} catch (Exception e) {
			System.err.println("ERROR Occured");
			e.printStackTrace();
		} finally {
			System.out.println("method1(): Finally block");
		}
	}
	
	

}
