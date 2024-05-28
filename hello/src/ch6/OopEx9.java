package ch6;

/**
 * 구문 분석 기능 구현
 * 
 * - xml, html 파싱 기능을 구현
 * - 여기서 중요한 것은 xml 1.0 버전의 구문 분석에서 2.0으로 변경되더라도 파싱 기능을 호출하는 쪽에서는 변경에 대한 영향이 없도록 구현하는 것이 중요
 * 
 * - xml 파싱 전용 클래스, html 파싱 전용 클래스
 * - 파싱 기능을 사용하는 클래스(main 메소드가 있는 쪽)
 * - main 메소드 쪽에서 파싱할 파일을 전달하면 해당 전용 클래스쪽에서 파싱한 결과를 알려주도록 하면 됨.
 * - 단, 파싱 전용 쪽에서 어떤 변화가 있더라도 호출한 쪽(main 메소드 쪽)에는 영향이 없도록 해야 함.
 * - 파싱 기능은 실제 파싱을 수행하지 않고, System.out.println() 을 사용해서 간단하게 출력되는 기능으로 구현하면 됨.
 * 
 * 1. main 메서드에서 xml 파싱 기능을 사용시,
 * 	  xml 파싱 버전이 변경되어 코드가 변경되더라도 main 메서드에는 영향이 없어야 함
 * 	  -> 인터페이스의 반환형 다형성을 적용
 * 
 * 2. main 메서드에서 xml파싱에서 html 파싱으로 변경이 되더라도 쉽게 파싱기능을 사용할 수 있어야 함
 * 	  -> 인터페이스의 반환형 다형성을 적용
 * 
 * 3. 파싱 기능에 대해선 표준화가 되도록 해야 함
 * 	  향후 JSON 파싱 등의 추가적인 요구사항이 발생할 수 있음
 * 	  개발 팀 멤버가 변경될 수 있음
 * 	  -> 기능에 대한 인터페이스
 */

public class OopEx9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// -------------- Parser 기능 동작 테스트 ----------------
		// XML, HTML 관련 Parser class를 직접 호출해서 사용하지 않으면 : 약한 결합!
		// 인스턴스 생성을 대리자(별도의 클래스)를 통해서 수행하면
		// main() 메서드와의 직접적인 관계는 없어짐 : 약한 결합
		
		// 인터페이스 형태로 반환이 되도록 해야함 : 약한 결합
		
		
		// 현재는 Parseable 타입으로 반환되므로, NewXMLParser가 되든 XmlParser가 되던 사용하는 쪽에선 모름
		Parseable parser = ParserManager.getParser();
		parser.parse("Some files");
	}

}

// 표준화 용도. main 메서드에서 쉽게 사용되도록 하는 용도
interface Parseable {
	public abstract void parse(String fileName);
}

// 파싱 전용 클래스
// 1.0 전용 XML Parser
class XmlParser implements Parseable{
	

	@Override
	public void parse(String fileName) {
		System.out.println(fileName + "구문 분석 완료");
	}
}

// 2.0 전용 XML Parser
// main 메서드에서 반환되는 인스턴스 1.0이 될지, 2.0이 될지 몰라도
// 최신 Parser가 사용되도록 해야 함 -> XMLParser의 변경이 main 메서드에서 코드레벨로 영향이 가지 않는다는 것을 의미함 : 약한 결합
// : 인터페이스의 반환형 다형성
class NewXmlParser implements Parseable{
	
	@Override
	public void parse(String fileName) {
		System.out.println(fileName + "구문 분석 완료");
	}
}

// 응집도는 높게 구현함
class HtmlParser implements Parseable {
	
	@Override
	public void parse(String fileName) {
		// TODO Auto-generated method stub
		System.out.println(fileName + "구문 분석 완료");
	}
	
}

// Parser 인스턴스 생성 대리자 클래스 
// 안의 코드가 수정되더라도 main에 영향 X : 약한 결합
class ParserManager {
	public static Parseable getParser() {
//		return new XmlParser();
//		return new NewXmlParser();
		return new HtmlParser();
	}
}