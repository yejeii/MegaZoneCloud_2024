package gof_VisitorP;

public class WholeFile {

	private String name;
	private int size;
	
	public WholeFile(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public String toString() {
		return getName() + " [" + getSize() + "]";
	}
	
	public void accept(String path) {
		System.out.println(path + "/" + this);
	}
}
