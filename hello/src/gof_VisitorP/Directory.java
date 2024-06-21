package gof_VisitorP;

import java.util.ArrayList;
import java.util.Iterator;

public class Directory extends Entry {

	private String name;
	private ArrayList dir = new ArrayList<>();
	
	public Directory(String name) {
		this.name = name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
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
			Entry entry = (Entry) iterator.next();
			size += entry.getSize();
		}
		return size;
	}
	
	public Entry add(Entry entry) {
		dir.add(entry);
		return this;
	}
	
	public Iterator iterator() {
		return dir.iterator();
	}

}
