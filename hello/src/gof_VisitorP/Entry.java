package gof_VisitorP;

import java.util.Iterator;

// File 클래스와 Directory 클래스의 상위 클래스
// Element 인터페이스를 implements -> Entry 클래스를 Visitor 패턴에 적용
public abstract class Entry implements Element {
	public abstract String getName();
	public abstract int getSize();
	
	public Entry add(Entry entry) throws FileTreatmentException {
		throw new FileTreatmentException();
	}
	
	public Iterator iterator() throws FileTreatmentException {
		throw new FileTreatmentException();
	}
	
	public String toString() {
		return getName() + "[ " + getSize() + " ]";
	}
}
