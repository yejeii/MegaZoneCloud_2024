package ch6;

/**
 * 클래스 변수를 이용한 인스턴스 변수 초기화
 */
public class Product {

	public static int count = 0;
	public int serialNo;	// 제품 시리얼 번호(식별자)
	
	// 인스턴스 초기화 블럭
	{
		++count;
		this.serialNo = count;
	}
	
	// 생성자
	public Product() {
		System.out.println("serailNo : " + this.serialNo);
	}
	
}
