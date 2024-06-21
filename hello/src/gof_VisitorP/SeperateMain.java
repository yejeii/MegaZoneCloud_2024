package gof_VisitorP;

import java.util.Iterator;

public class SeperateMain {

	public static void main(String[] args) {
		System.out.println("root entries 들을 만들고 있습니다.");
		SeperateDirectory rootDir = new SeperateDirectory("root");
		SeperateDirectory binDir = new SeperateDirectory("bin");
		SeperateDirectory usrDir = new SeperateDirectory("usr");
		SeperateDirectory tmpDir = new SeperateDirectory("tmp");
		rootDir.add(binDir);
		rootDir.add(tmpDir);
		rootDir.add(usrDir);
		binDir.add(new SeperateFile("vim", 2000));
		binDir.add(new SeperateFile("start.sh", 300));
		Iterator it = rootDir.iterator();
		rootDir.accept("");
//		rootDir.accept(new ListVisitor());
		
		System.out.println("root entries 들을 만들고 있습니다.");
		SeperateDirectory apple = new SeperateDirectory("apple");
		SeperateDirectory banana = new SeperateDirectory("banana");
		SeperateDirectory grape = new SeperateDirectory("grape");
		usrDir.add(apple);
		usrDir.add(banana);
		usrDir.add(grape);
		apple.add(new SeperateFile("red.bmp", 400));
		apple.add(new SeperateFile("round.jpg", 500));
		banana.add(new SeperateFile("yellow.png", 600));
		grape.add(new SeperateFile("span.doc", 700));
		grape.add(new SeperateFile("over.txt",800));
//		rootDir.accept(new ListVisitor());
		rootDir.accept("");

	}
}
