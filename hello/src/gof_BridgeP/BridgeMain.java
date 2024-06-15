package gof_BridgeP;

public class BridgeMain {

	public static void main(String[] args) {
		var title ="복원된 지구";
		var author = "김형준";
		String[] content = {
				"플라스틱 사용의 감소와",
				"바다 생물 어획량 감소로 인하여",
				"지구는 복원되었다."
		};
		
		Draft draft = new Draft(title, author, content);
		
		// 1. 심플하게 표시할래
		Display display = new SimpleDisplay();
		draft.print(display);
		
		System.out.println();
		
		// 2. 캡션을 달아서 표시할래 
		Display display2 = new CaptionDisplay();
		draft.print(display2);
		
		System.out.println();
		
		/* 새로운 요구사항 등장 */
		var publisher = "복악출판";
		var cost  = 100;
		
		Publication publication = new Publication(title, author, content, publisher, cost);
		publication.print(display);
		System.out.println();
		publication.print(display2);
	}

}
