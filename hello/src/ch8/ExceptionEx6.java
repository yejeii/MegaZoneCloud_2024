package ch8;

/**
 * - 예외 되던지기 : 예외 처리 후 예외 생성, throw -> 직접 처리 or 위임 처리
 *   vs
 *   예외 던지기(기존) -> 예외 생성, throw : 직접 처리 or 위임 처리
 *   
 * - 예외 되던지기 분류
 * 	- 연관된 시스템이 자바가 아닌 경우
 * 	  별도의 A, B 분리된 시스템이 있다고 가정
 * 	  A : JAVA로 개발
 * 	  B : C#으로 개발
 * 
 *    이때, A <-> B 는 연결되어야 함	
 *    통신규약(네트워크 통신) -> 전문(문자열)
 *    
 *    같은 서버의 A, D가 있는 경우 -> main()에서 반환형을 정수/문자(char)로 하면 됨
 *    -> batch system(ex. Spring batch)
 * 
 * 
 * - 연결된 예외(Chained Exception)
 *   예외 A가 예외 B를 발생시켰다면, A를 B의 예외 원인이라고 함
 *   
 *   큰 분류의 예외로 묶어서 관리하고 싶은 경우와 checked 예외를 unchecked로 변경하려고 하는 경우 주로 사용 가능성이 높음
 *   
 *   ex. 파일 설치 예제
 *   SpaceException, MemoryException : 설치 치 발생할 수 있는 예외 
 *   -> 상위 예외를 InstallException이라 할 때,  
 *   -> SpaceException, MemoryException은 InstallException의 예외 원인이라고 할 수 있음
 *   -> Has-A(포함) 관계(정립하기 나름), 다형성 성립.
 *   
 *   try {
 *   	startInstall();
 *   } catch(SpaceException e) {
 *   	...
 *   } catch(MemoryException e) {
 *   	...
 *   }
 *   
 *   ->
 *   
 *   try {
 *   	startInstall();	// 예외 발생
 *   } catch(SpaceException e) {
 *   	InstallException ie = new InstallException(); // 예외 생성
 *   	ie.initCause(e);	// InstallException의 원인 예외를 SpaceException으로 지정
 *   	throw ie;	// InstallException 발생
 *   
 *   	// initCause(Throwable cause) : Throwable의 메서드 
 *   									Throwable에는 cause 변수 존재, type Throwable
 *   									-> 현재 예외 인스턴스를 원인 예외로 등록하게 됨
 *   									cause의 type이 Throwable이므로, 모든 예외는 발생한 예외의 원인 예외를 등록 가능
 *   } catch(MemoryException e) {
 *   	...
 *   }
 *   
 *   사용 이유>
 *   - 하나의 큰 분류의 예외로 묶어서 관리하고 싶은 경우.
 *     큰 분류의 예외로 catch 처리 시, 실제로 발생한 예외를 알 수 없게 됨.
 *     
 *     목적 : 추상화 또는 상속을 통한 다형성을 통해 관리의 편리성을 높이기 위함
 *     		 -> 반복되는 코드가 줄어들게 됨(다형성이 적용된 매개변수, 반환타입)
 *     
 *   - 상속 관계로 Exception을 정의하면 casting이 필요해짐
 *     파생된 Exception이 많아지면 casting의 부담이 높아지게 됨.
 *     
 *     casting 대신 initCause(), getCause()
 *     
 *   - checked 예외를 unchecked로 변경하려고 하는 경우
 *     new RuntimeException(new MemoryException()); -> unchecked. 
 *     
 */

/* 예외 되던지기 */
//public class ExceptionEx6 {
//	public static void main(String[] args) {
//		try {
//			method1();
//		} catch (Exception e) {
//			System.out.println("main()에서 예외 처리됨");
//		}
//	}
//	
//	static void method1() throws Exception {
//		try {
//			throw new Exception();
//		} catch (Exception e) {
//			System.out.println("method1()에 의해 예외 처리됨");
//			throw e;
//		}
//	}
//}

/* 연결된 예외(Chained Exception) 
 * - 하나의 큰 분류의 예외로 묶어서 관리하고 싶은 경우. */
public class ExceptionEx6 {
	public static void main(String[] args) {
		try {
			install();
		} catch (InstallException e) {
			if(e.getCause() instanceof SpaceException) {
				System.out.println("InstallException의 원인 : SpaceException");
			}
			if(e.getCause() instanceof MemoryException) {
				System.out.println("InstallException의 원인 : MemoryException");
			}
			
			System.out.println("에러 메세지 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			deleteTempFiles();
		}
		
		/* - checked 예외를 unchecked로 변경하려고 하는 경우 
		 *   Exception -> RunException으로 형변환처럼 되는 것 같지만, 실제론 연결된 예외임 */
		MemoryException met = new MemoryException("unchecked 예외용");
		
		// Exception -> RuntimeException으로 변경됨
		// RuntimeException(met)
		// 	 -> super((Throwable)met)
		// 		-> Exception((Throwable)met)
		// 			-> super((Throwable)met)
		// 				-> Throwable((Throwable)met)
		// 					-> this.cause = (Throwable)met;
		
		// Throwable class의 cause 인스턴스 변수에 저장됨
		// 자기 자신의 원인이 되는 예외 -> 연결된 예외라고 함
		
		// 결과 : checked -> unchecked 로 변경됨. 컴파일 체크를 하지 않게 됨
		RuntimeException rte = new RuntimeException(met);
	}
	
	 /* Exception을 묶어서 처리하고 싶음 
	  * throws InstallException 
	  */
	static void install() throws InstallException{
		try {
			startInstall();
		} catch (SpaceException e) {
			InstallException ie = new InstallException("설치 중 에러");
			
			// InstallException의 원인 예외를 등록할 수 잇음
			// 원인 예외를 InstallExcetion의 인스턴스 멤버변수로 관리하고 있으니 "연결된 예외"라고 할 수 있음
			
			// 원인 예외(cause)의 실제 소유 클래스는 Throwable임
			// 즉, cause는 Throwable 클래스의 멤버변수로써, 원인 예외를 저장하는 변수라고 할 수 있음
			
			// Throwable은 모든 예외의 최상위 부모이므로 
			// 모든 예외 클래스는 자신을 발생시킨 원인 예외를 Throwable의 cause 변수에 저장(가질) 수 있음
			ie.initCause(e);	
			
			throw ie;
		} catch (MemoryException e) {
			InstallException ie = new InstallException("설치 중 에러");
			ie.initCause(e);
			throw ie;
		}
		
		
	}
	
	/* 프로그램 설치와 관련된 메서드 작성 */
	static void startInstall() throws SpaceException, MemoryException {
		if(!enoughSpace()) throw new SpaceException("설치 실패 : 설치공간 부족");
		if(!enoughMemory()) throw new MemoryException("설치 실패 : 메모리 여유 부족");
	}
	
	static void deleteTempFiles() {
		System.out.println("설치 파일 삭제 완료");
	}

	private static boolean enoughMemory() {
		return false;
	}

	private static boolean enoughSpace() {
		return false;
	}
}

/*
 * 설치 예외
 */
class InstallException extends Exception {

	public InstallException(String msg) {
		super(msg);
	}
	
}

/* 설치 예외의 원인 예외(디스크 용량) */
class SpaceException extends Exception {

	public SpaceException(String msg) {
		super(msg);
	}
}

/* 설치 예외의 원인 예외(메모리 용량) */
class MemoryException extends Exception {

	public MemoryException(String msg) {
		super(msg);
	}
}
