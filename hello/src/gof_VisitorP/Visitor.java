package gof_VisitorP;

// 방문자(Visitor) 추상 c : 파일과 디렉터리에 방문
public abstract class Visitor {
	public abstract void visit(File file);	// File 을 방문했을 때 File 클래스가 호출
	public abstract void visit(Directory directory);
}
