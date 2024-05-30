package ch8;

/**
 * - 자동 자원 반환
 *   문법 : try..with..resources
 *   목적 : finally의 복잡도가 높아지는 경우를 해소하기 위함
 *   	  자원해제 시 사용 가능성이 높음(ex. 파일 입출력)
 *   사용 : AutoCloseable Interface 구현
 *   	   인터페이스를 구현해야만 자동 자원 반환 문법을 적용시킬 수 있음
 *
 * 	 >생각해보기<
 *   try{} block에서도 예외가 발생하고, AutoCloseable를 구현한 close()에서도 예외가 발생하면???
 *   -> 사용자 정의 Exception을 두 개 만든 후, 두 개의 Exception을 throw하면 됨
 *   
 *   기본적으로 Exception은 하나만 발생. 
 *   그러나 Closeable 인터페이스를 구현한 클래스를 사용할 경우, close()에 의해 예외가 2번 발생할 수도 있음
 *   -> 실제로 발생한 Exception은 ExecException으로 하고,
 *      CloseException은 Suppressed Exception(억제된 예외)로 두면 됨
 */
public class ExceptionEx5 {

	public static void main(String[] agrs) {
		
		try (CloseableReource cr = new CloseableReource()) {
			// true : 코드 실행 중 예외 발생
			// false : 코드 실행 중 예외 발생 X
//			cr.resourceWork(false);
			cr.resourceWork(true);
		} catch (ExecException e) {	// resourceWork에 대한 catch
			e.printStackTrace();
		} catch (CloseException e) {// close에 대한 catch
			e.printStackTrace();
		} catch (Exception e) {	
			e.printStackTrace();
		} 
		
		System.out.println("정상 종료됨");
	}	
}

/**
 * AutoCloseable을 구현한 클래스 
 * 객체 생성시, close() 메서드 자동 호출하여 자원 해제.
 * -> Finally 를 대신하는 메서드이므로 예외 처리 후 작업하고자 하는 코드를 try-catch보단 여기에 작성하면 됨.
 */
class CloseableReource implements AutoCloseable {


//	@Override
//	public void close() throws Exception {
//		
//		System.out.println("CloseableResource.close()");
//	}
	
	/* 
	 * Exception 의 확장 
	 * 사용자 정의 Exception 적용 후
	 */
	@Override
	public void close() throws CloseException {
		
		System.out.println("close() 호출");
		throw new CloseException("CloseException 발생");
	}
	
	/*
	 * 자원을 사용한다고 가정한 메서드
	 */
	public void resourceWork(boolean exceptionFlag) throws ExecException {
		System.out.println("resourceWork() 호출");
		
		// 발생한 예외는 위임처리 -> 메서드 정의부분에 throws
		if(exceptionFlag) // 코드 처리 중 Exception 발생
			throw new ExecException("ExecException 발생");
	}
	
}

/* 자원 해제(close) 시 발생하는 예외 처리 클래스 
 * CloseableReource cr = new CloseableReource() 시 발생하는 예외를 처리하기 위한 예외 처리 클래스
 * */
class CloseException extends Exception {

	public CloseException(String msg) {
		super(msg);
	}
}

/* try 블럭에서의 예외 처리 클래스 */
class ExecException extends Exception {
	
	public ExecException(String msg) {
		super(msg);
	}
}











