package ch6;


/**
 * 포커 카드 실행 클래스
 */
public class OopEx3 {

	public void main(String[] args) {
		Deck d = new Deck(); // 52장의 포커 카드 준비 완료.
		PockerCard c = d.pickCard();
		System.out.println(c.toString());
		
		c = d.pickCard(1);
		System.out.println(c.toString());
		
		d.shuffle();
		c = d.pickCard(1);
		System.out.println(c.toString());
	}
}
