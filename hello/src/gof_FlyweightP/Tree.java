package gof_FlyweightP;

class Tree {
	
	long objSize = 100;	// 100MB
	
	String type; 	// 나무 종류
	Object mesh;	// 메쉬
	Object texture; // 나무 껍질 + 잎사귀 텍스처
	
	// 위치변수
	double x;
	double y;
	
	public Tree(String type, Object mesh, Object texture, double x, double y) {
		this.type = type;
		this.mesh = mesh;
		this.texture = texture;
		this.x = x;
		this.y = y;
		
		// 나무 객체를 생성하였으니 메모리 사용 크기 증가
		Memory.size += objSize;
	}
	
}
