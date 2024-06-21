package gof_VisitorP;

// Visitor 클래스의 생성자를 받아들이는 데이터 구조를 나타내는 인터페이스
// 방문 대상의 집합체
public interface Element {
	public abstract void accept(Visitor visitor);
}
