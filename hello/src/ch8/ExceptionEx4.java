package ch8;

/**
 * - Exception 처리 문제
 * 	 요구사항> 파일 처리 중 예외가 발생할 수 있음을 가정하고 프로그램 구현
 * 	 기능> 복사공간 여유 확인, 메모리 여유 확인, 파일 복사, 설치, 임시파일 삭제
 *   예외 처리> 1. 복사할 공간의 여유가 없을 때
 *   		 2. 메모리가 부족할 때
 *   프로그램 동작 순서>
 *   	1. 설치 시작
 *   	2. 복사공간 여유확인
 *   	3. 메모리 여유 확인
 *   	4. 파일 복사
 *   	5. 설치 완료
 *   	6. 임시파일 삭제
 *   좋은 코드로 작성하기 위한 기준
 *   	응집도 높다 == 관련있는 것끼리 묶음 -> 메서드 형태
 *   							   -> 설치 메서드, 파일복사, 파일삭제, 메모리 체크, 디스크 용량 체크
 *   	재사용성이 높다 == 예외클래스 작성
 */
public class ExceptionEx4 {
	
	public static void main(String[] args) {
		
		SpaceException se = new SpaceException("abla");
		MemoryException me = new MemoryException("dfndsf");
		
		// 설치할 파일 경로
		String orgFilePath = "D:/drive/orgFilePath";
		String saveFilePath = "D:/drive/saveFilePath";
		
		InstallAction install = new InstallAction();
		FileAction fileAction = new FileAction();
		
		try {
			// 1. 설치 메서드 호출
			install.runToInstall(saveFilePath);
			
			// 파일 복사 및 원래 파일 삭제
			fileAction.copyFile(orgFilePath, saveFilePath);
			fileAction.deleteFile(orgFilePath);
		} catch (Exception e) {
			// 실패시 만든 새 경로 파일 삭제
			e.printStackTrace();
			fileAction.deleteFile(saveFilePath);	
		} finally {
			fileAction.close();
		}
	}
	
}

class InstallAction {
	
	CheckOS checkOs = new CheckOsImpl();
	
	// 설치 시작
	public void runToInstall(String filePath) throws SpaceException {
		System.out.println("설치를 시작합니다.");
		
		// 디스크 및 메모리 용량 확인
		if(!checkOs.checkDisk(filePath)) {
			throw new SpaceException("Fail to Install : Disk size is not enough");
		}
		if(!checkOs.checkMemory()) {
			throw new MemoryException("Fail to Install : Memory size is not enough");
		}
	}
}

class FileAction {
	
	// 파일 복사
	public void copyFile(String oldfilePath, String newfilePath) {
		System.out.println(oldfilePath + " 파일을 " + newfilePath + "로 복사합니다.");
	}
	
	// 파일 삭제
	public void deleteFile(String oldfilePath) {
		System.out.println(oldfilePath + " 파일을 삭제합니다.");
	}
	
	// 파일 자원 해제
	public void close() {
		System.out.println("파일 자원 해제!");
	}
}

interface CheckOS {
	
	// 디스크 확인
	public boolean checkDisk(String newfilePath) throws SpaceException ;
	
	// 메모리 확인
	public boolean checkMemory() throws RuntimeException;
	
}

class CheckOsImpl implements CheckOS {

	@Override
	public boolean checkDisk(String newfilePath) throws SpaceException {
		
		/* 디스크 여유 공간 체크 로직 */
		
		System.out.println("디스크 용량 확인!");
		
		// false : 여유 공간 부족 -> 예외를 발생시켜야 하는 상황
		// true : 디스크의 설치 공간 충분 -> 예외 발생 필요 X
		return true;	
	}

	@Override
	public boolean checkMemory() throws RuntimeException {
		
		/* 메모리 여유 공간 체크 로직 */

		System.out.println("메모리 용량 확인");
		return false;
	}
	
}


/**
 * 예외클래스 정의
 * 
 * - 사용자 예외 : 100개
 * - 시스템 예외 : 100개
 * - 서비스 예외': 100개
 * - 운영 예외 : 100개
 * 
 * -> try-catch 400개를 만들어야 함.. 미친짓?
 * -> 운영 초반에는 엄격하게 하는게 맞을 수 있지만, 안정화되고 운영이 잘되고 있는 상황에서,
 * 	  신규개발시, 수정시 try-catch를 하지 않아도 되도록 개발 및 유지보수 정책을 수립할 수 있음
 * 
 * -> RuntimeException class를 사용하면 try-catch 사용할 필요 X
 * 
 * - checked, unchecked 옵션
 *   checked : 컴파일러가 검증하겠다 -> Java에서 제공하는 Exception 클래스와의 관계를 맺어주자..(상속)
 *   		   예외 처리를 강제하겠다는 의미 -> 직접 예외 처리 or 예외 위임 처리
 *   unchecked : 컴파일러가 검사 X. -> RuntimeException 클래스와의 관계를 맺어주자..(상속)  
 * 
 */
class SpaceException extends Exception {

	public SpaceException(String msg) {
		super(msg);
	}
}

class MemoryException extends RuntimeException {

	public MemoryException(String msg) {
		super(msg);
	}
}


