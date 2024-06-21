package gof_VisitorP;

public class SeperateFile extends SeperateEntry{

	private String name;
	private int size;
	
	public SeperateFile(String name, int size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public void accept(String path) {
		System.out.println(path);
	}
	

}
