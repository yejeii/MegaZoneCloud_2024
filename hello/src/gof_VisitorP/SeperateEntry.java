package gof_VisitorP;

// File 클래스와 Directory 클래스의 상위 클래스
/**
 * abstract 클래스로 만든 이유
 * 
 * Entry 
 * 	- 폴더, 파일을 감싼 추상적 개념으로 만든 클래스로 
 * 
 * 	- 개발자는 아마 폴더, 파일의 다형성을 적용시킬 클래스가 필요했을 것이다.
 * 	  추상적 개념을 클래스화한 클래스로 만들기 위해, 
 * 	  즉, Entry 클래스가 new 로 객체화될 일이 없기 때문에 abstract class 로 만든 것
 * 
 * 	- 이와 함께, 클래스로 만들면 메서드를 무조건 구현해야 하기 떄문에 
 *    객체화할 일이 없는 클래스에 쓸데없는 코드 작성을 피하기로 한 것일 수도 있다.
 *    
 * 	- 추상 메서드를 부모 클래스로 둠으로써 기능 강제 + 다형성 + 추상화
 * 
 */
public abstract class SeperateEntry {
	
	public abstract String getName();		
	public abstract int getSize();
	
	public abstract void accept(String path);

	// 자식에서 재정의 안되면 해당 메서드를 찾아올 것
	public String toString() {
		return getName() + "[ " + getSize() + " ]";
	}
	
}
