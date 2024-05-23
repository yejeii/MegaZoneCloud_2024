package ch2;

/**
 * 변수의 초기화
 * - 변수를 사용하기 전에 처음으로 값을 저장하는 것.
 * 
 * 변수의 명명
 * - 대소문자 구분, 길이 제한 X
 * - 예약어 사용 불가
 * 	ex. int
 * - 숫자로 시작 불가
 * - 특수문자는 _, $ 만 가능
 * 
 * 변수의 타입
 * - 기본형 : 값이 저장되는 변수 
 * 	 논리형 ( boolean ), 
 *   문자형 ( char ),
 * 	 정수형 ( byte(1), short(2byte), int(4byte, 약 20억), long(8byte) ),
 *   실수형 ( float(4byte), double(8byte) )
 * - 참조형 : 객체의 주소값이 저장되는 변수
 * 
 * 상수
 * - 변수와 달리 값을 한 번 저장하면 변경 불가
 * - 예약어 final 사용 : final int MAX_SPEED = 10;
 * 
 * 형변환(casting)
 * - 변수를 다른 타입의 변수로 변환하는 것
 * - 기본형에 대한 형변환보다 참조형에 대한 형변환이 중요!
 * - 자동 형변환의 규칙 : 버림 규칙..
 * 		기본 값을 최대한 보존할 수 있는 타입으로 자동 형변환이 이루어짐
 * 		byte > short > int > long > float > double
 * 		char > int
 */
public class VarEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/* 변수의 초기화 */
		int year = 0;
		int age = 14;
		
		// sysout 입력 후 ctrl + space bar
		System.out.println(year);
		System.out.println(age);
		
		year = age + 2000;
		age = age + 1;
		System.out.println(year);
		System.out.println(age);
		
		/* 상수 */
		final int MAX_SPEED = 10;
		System.out.println(MAX_SPEED);
//		MAX_SPEED = MAX_SPEED + 0;	// ERROR : 값을 보호
		
		/* char */
		char ch = 'A';		// single quotation - 한 문자 저장시 사용
//		char ch2 = 'AA';	// ERROR : 한 문자만 저장
//		char ch3 = "AA";	// ERROR : double quotation - 문자열을 의미
		String str = "AA";
		
		/* 실수 */
		float f = 9.12345678901234567890f;
		double d = 9.12345678901234567890d;
		System.out.println(f);
		System.out.println(d);
		
		/* casting */
		double d2 = 85.4d;
		int score = (int) d2;
		System.out.println(d2);
		System.out.println(score);	// 소수 버림
		
		double d3 = 85.6d;
		int score2 = (int) d3;
		System.out.println(d3);
		System.out.println(score2);
		
		/* 아스키 코드 casting */
		char ch3 = 'A';	// 65
		int intA = (int) ch3;
		System.out.println(ch3);
		System.out.println(intA);
		
		
	}

}
