package ch14;

/**
 * 3. 메서드 참조
 * 
 * 람다식을 더욱 간결하게 표현할 수 있는 방법
 * 
 * Function<String, String> f = s -> Integer.parseInt(s);
 * 
 * Integer wrapper(String s) {
 * 		return Integer.parseInt(s);
 * }
 * 
 * Function<String, String> f = Integer::parseInt;
 * 
 * -> 메서드 참조 스타일로 코드를 작성해도 문제가 없이 동작하는 배경(컴파일러 관점)
 * 	  1. parseInt 코드를 보면, Integer 의 parseInt() 메서드라고 추론
 *    2. Function 인터페이스의 지정된 제네릭 타입으로 매개변수 타입 및 반환 타입 정보 추론
 *    3. 'Integer::parseInt' 이 (String)s -> Integer.parseInt(s) 이라는 람다식 표현임을 아는 것!
 *    
 * Ex.
 * - 람다식 형태
 * BiFunction<String, String, Boolean> f
 * 		= (s1, s2) -> s1.equals(s2);
 * 
 * - 메서드 참조 형태
 * BiFunction<String, String, Boolean> f = s1::equals;
 * 
 * 
 * 메서드 참조 형태의 코드 작성(람다식 작성)은
 * 		"클래스이름::메서드명", 또는 "참조변수::메서드명" 로 줄여서 사용 가능
 * 
 * MyClass obj = new MyClass()
 * Function<String, Boolean> f = (s) -> obj.equals(s);
 * Function<String, Boolean> f = obj::equals;
 * 
 */
public class LambdaEx3 {

}
