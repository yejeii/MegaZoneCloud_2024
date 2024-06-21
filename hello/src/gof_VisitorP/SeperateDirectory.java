package gof_VisitorP;

import java.util.ArrayList;
import java.util.Iterator;

public class SeperateDirectory extends SeperateEntry {

	private String name;
	private ArrayList dir = new ArrayList<>();
	
	public SeperateDirectory(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		int size = 0;
		Iterator iterator = dir.iterator();
		while(iterator.hasNext()) {
			SeperateEntry entry = (SeperateEntry) iterator.next();
			size += entry.getSize();
		}
		return size;
	}
	
	@Override
	public void accept(String path) {
		String currentdir = "";
		
		if(path != "") {
			currentdir = path;
		} else {
			currentdir = "/" + this.getName();
		}
		System.out.println(currentdir);
		String saveDir = currentdir;
		
		Iterator iterator = this.iterator();
		while (iterator.hasNext()) {
			SeperateEntry entry = (SeperateEntry)iterator.next();
			if(entry instanceof SeperateDirectory) {
				currentdir = currentdir + "/" + entry.getName();
				entry.accept(currentdir);
			} else {
				currentdir = saveDir + "/" + entry.getName();
				entry.accept(currentdir);
			}
			currentdir = saveDir;
		}
	}
	
	public SeperateEntry add(SeperateEntry entry) {
		dir.add(entry);
		return this;
	}
	
	public Iterator iterator() {
		return dir.iterator();
	}
	
	
	
}
