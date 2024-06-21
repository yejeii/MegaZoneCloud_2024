package gof_VisitorP;

import java.util.ArrayList;
import java.util.Iterator;

public class WholeDirectory {

	private String name;
	private ArrayList dir = new ArrayList<>();
	
	public WholeDirectory(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		int size = 0;
		Iterator iterator = dir.iterator();
		while(iterator.hasNext()) {
			Object object = iterator.next();
			if(object instanceof WholeDirectory) {
				WholeDirectory directory = (WholeDirectory) object;
				size += directory.getSize();
			} else {
				WholeFile file = (WholeFile) object;
				size += file.getSize();
			}
		}
		return size;
	}
	
	public WholeDirectory add(WholeDirectory entry) {
		dir.add(entry);
		return this;
	}
	
	public WholeDirectory add(WholeFile entry) {
		dir.add(entry);
		return this;
	}
	
	public Iterator iterator() {
		return dir.iterator();
	}
	
	public void accept(String path) {
		String currentdir = "";
		
		if(path != "") {
			currentdir = path;
		} else {
			currentdir = "/" + this.getName();
		}
		System.out.println(currentdir);
		
		Iterator iterator = this.iterator();
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if(obj instanceof WholeDirectory) {
				WholeDirectory dir = (WholeDirectory)obj;
//				System.out.println("Has directory : " + dir.toString());
				currentdir = currentdir + "/" + dir.getName();
				dir.accept(currentdir);	// bin - tmp - usr
			} else if (obj instanceof WholeFile) {
				WholeFile file = (WholeFile)obj;
				file.accept(currentdir);
			}
			
			if(obj instanceof WholeDirectory ) currentdir = "/root";
		}
	}
	
	public String toString() {
		return getName() + " [" + getSize() + "]";
	}
	
}
