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
		
		// 설치할 파일 경로
		String orgFilePath = "D:/drive/orgFilePath";
		String saveFilePath = "D:/drive/saveFilePath";
		
		InstallAction install = new InstallAction();
		FileAction fileAction = new FileAction();
		
		try {
			// 1. 설치 메서드 호출
			install.runToInstall(saveFilePath);
			
			// 파일 복사 및 임시 파일 삭제
			fileAction.copyFile(orgFilePath, saveFilePath);
			fileAction.deleteFile(orgFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fileAction.deleteFile(orgFilePath);
		} 
		
		
	}
	
}

class InstallAction {
	
	CheckOS checkOs = new CheckOsImpl();
	
	// 설치 시작
	public void runToInstall(String filePath) throws Exception {
		System.out.println("설치를 시작합니다.");
		
		// 디스크 및 메모리 용량 확인
		checkOs.checkDisk(filePath);
		checkOs.checkMemory();
		
	}
}

class FileAction {
	
	// 파일 복사
	public void copyFile(String oldfilePath, String newfilePath) {
		System.out.println("파일을 복사합니다.");
	}
	
	// 파일 삭제
	public void deleteFile(String oldfilePath) {
		System.out.println("파일을 삭제합니다.");
	}
}

interface CheckOS {
	
	// 디스크 확인
	public void checkDisk(String newfilePath) throws Exception ;
	
	// 메모리 확인
	public void checkMemory() throws Exception;
	
}

class CheckOsImpl implements CheckOS {

	@Override
	public void checkDisk(String newfilePath) throws Exception {
		System.out.println("디스크 용량 확인!");
	}

	@Override
	public void checkMemory() throws Exception {
		System.out.println("메모리 용량 확인");
	}
	
}



