package gof_BridgeP;

public class SimpleDisplay implements Display {

	@Override
	public void title(Draft draft) {
		System.out.println(draft.getTitle());
	}

	@Override
	public void author(Draft draft) {
		System.out.println(draft.getAuthor());
	}

	@Override
	public void content(Draft draft) {
		var contents = draft.getContent();
		for(String content : contents) {
			System.out.println(content);
		}
	}

}
