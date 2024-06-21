package gof_VisitorP;

import java.util.Iterator;

public class WholeMain {

	public static void main(String[] args) {
		System.out.println("root entries 들을 만들고 있습니다.");
		WholeDirectory rootDir = new WholeDirectory("root");
		WholeDirectory binDir = new WholeDirectory("bin");
		WholeDirectory usrDir = new WholeDirectory("usr");
		WholeDirectory tmpDir = new WholeDirectory("tmp");
		rootDir.add(binDir);
		rootDir.add(tmpDir);
		rootDir.add(usrDir);
		binDir.add(new WholeFile("vim", 2000));
		binDir.add(new WholeFile("start.sh", 300));
		Iterator it = rootDir.iterator();
		rootDir.accept("");
//		rootDir.accept(new ListVisitor());
		
		System.out.println("root entries 들을 만들고 있습니다.");
		WholeDirectory apple = new WholeDirectory("apple");
		WholeDirectory banana = new WholeDirectory("banana");
		WholeDirectory grape = new WholeDirectory("grape");
		usrDir.add(apple);
		usrDir.add(banana);
		usrDir.add(grape);
		apple.add(new WholeFile("red.bmp", 400));
		apple.add(new WholeFile("round.jpg", 500));
		banana.add(new WholeFile("yellow.png", 600));
		grape.add(new WholeFile("span.doc", 700));
		grape.add(new WholeFile("over.txt",800));
//		rootDir.accept(new ListVisitor());
		rootDir.accept("");

	}

}
