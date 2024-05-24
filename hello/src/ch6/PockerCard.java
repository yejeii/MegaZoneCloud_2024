package ch6;

public class PockerCard {

	
	public static final int KIND_MAX = 4;		// 카드 무늬 수
	public static final int NUM_MAX = 13;		// 카드 무늬별 총 수량
	
	public static final int SPADE = 4;		
	public static final int DIAMOND = 3;	
	public static final int HEART = 2;
	public static final int CLOVER = 1;
	
	public int kind;	// 카드의 무늬이자 화면 출력용 데이터 배열의 인덱스 정보
	public int number;
	
	// 생성자 : 인스턴스 변수 초기화를 동일하게 하기 위함
	public PockerCard() {
		this(SPADE, 13);
	}
	
	public PockerCard(int kind, int number) {
		this.kind = kind;
		this.number = number;
	}
	
	/*
	 * 현재 인스턴스 카드 한 장의 정보를 출력
	 * 1. 화면 출력용 데이터를 한 군데서 관리
	 * 2. 코드 작성시 가독성과 사용 편리성을 높이기 위함
	 */
	public String toString() {
		String[] kinds = {"","CLOVER","HEART","DIAMOND","SPADE"};
		String numbers = "0123456789XJQK";

		return "kind : " + kinds[this.kind] +
				", number : " + numbers.charAt(this.number);
	}
}
