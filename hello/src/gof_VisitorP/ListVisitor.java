package gof_VisitorP;

import java.util.Iterator;

// 방문자(Visitor) 클래스 구현
// 데이터 구조(파일과 디렉터리)를 돌아다니면서 그 종류를 나타내는 클래스
public class ListVisitor extends Visitor {

	private String currentdir = "";	// 현재 주목하고 있는 디렉터리
	
	/*
	 * 파일 경로 보여주기
	 */
	@Override
	public void visit(File file) {
		System.out.println(currentdir + "/" + file);
	}

	/*
	 * 디렉토리 경로 보여주기
	 * 최상단 디렉토리에서 루프 돌면서 안에 있는 리스트 뿌리기
	 */
	@Override
	public void visit(Directory directory) {
		System.out.println(currentdir + "/" + directory);
		String saveDir = currentdir;
		currentdir = currentdir + "/" + directory.getName();
		Iterator iterator = directory.iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry)iterator.next();
			entry.accept(this);
		}
		currentdir = saveDir;
	}

}
