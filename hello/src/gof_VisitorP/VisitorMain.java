package gof_VisitorP;

public class VisitorMain {

	public static void main(String[] args) {
		try {
			System.out.println("root entries 들을 만들고 있습니다.");
			Directory rootDir = new Directory("root");
			Directory binDir = new Directory("bin");
			Directory usrDir = new Directory("usr");
			Directory tmpDir = new Directory("tmp");
			rootDir.add(binDir);
			rootDir.add(tmpDir);
			rootDir.add(usrDir);
			binDir.add(new File("vim", 2000));
			binDir.add(new File("start.sh", 300));
			rootDir.accept(new ListVisitor());
//			rootDir.accept(new 어떤비지터);
			
			System.out.println("root entries 들을 만들고 있습니다.");
			Directory apple = new Directory("apple");
			Directory banana = new Directory("banana");
			Directory grape = new Directory("grape");
			usrDir.add(apple);
			usrDir.add(banana);
			usrDir.add(grape);
			apple.add(new File("red.bmp", 400));
			apple.add(new File("round.jpg", 500));
			banana.add(new File("yellow.png", 600));
			grape.add(new File("span.doc", 700));
			grape.add(new File("over.txt",800));
			rootDir.accept(new ListVisitor());
		} catch(FileTreatmentException e) {
			e.getMessage();
		}
	}

}
