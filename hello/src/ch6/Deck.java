package ch6;

/** 
 * 포커카드 한 세트(벌)를 위한 클래스
 * 포커카드 한 벌은 52장의 포커 카드를 가지고 있음(포함관계 => 멤버변수 => 객체배열)
 */
public class Deck {

	// 클래스 변수
	public static final int CARD_NUM = 52;
	
	// 52장의 포커카드 인스턴스 변수(참조변수)
	PockerCard cardArr[] = new PockerCard[CARD_NUM];
	
	/* 
	 * 생성자 : 52장의 포커카드 인스턴스를 생성, 객체배열에 저장 
	 * */
	public Deck() {
		// 무늬 4개, 각 13장 => 중첩문 => cardArr[]에 저장
		
		// 배열의 인덱스 변수
		int i = 0;
		
		// outer for : 무늬 담당, inner for : 무늬별 카드 숫자 담당
		for (int j = PockerCard.KIND_MAX; j > 0; j--) {	// 4, 3, 2, 1
			for(int n=0; n<PockerCard.NUM_MAX; n++) {
				cardArr[i++] = new PockerCard(j,n);
			}
		}
	}

	/*
	 * 카드 뽑기
	 */
	public PockerCard pickCard(int idx) {
		return cardArr[idx];
	}
	
	/*
	 * 카드 봅기 
	 * 난수를 이용해 임의의 카드를 뽑을 수 있도록 배열의 위치정보를 생성
	 */
	public PockerCard pickCard() {
		int idx = 0;
		idx = (int)(Math.random() * CARD_NUM);	// 0.0 ~ 1.0 -> 0 ~ 52
		
		return pickCard(idx);
	}
	
	/*
	 * 카드 섞기
	 * 난수를 이용해 임의의 카드를 뽑을 수 있도록 배열의 위치정보 생성
	 */
	public void shuffle() {
		for(int i=0; i<cardArr.length; i++) {
			int idx = (int)(Math.random() * CARD_NUM);
			
			PockerCard tmPockerCard = cardArr[i];
			cardArr[i] = cardArr[idx];
			cardArr[idx] = tmPockerCard;
		}
	}
}
