package gof_FlyweightP;

class Terrain {

	// 지형 타일 크키
	static final int CANVAS_SIZE = 10000;
	
	// 나무를 렌더링
	public void beforeFlyweightRender(String type, Object mesh, Object texture, double x, double y) {
		
		// 나무를 지형에 생성
		Tree tree = new Tree(
					type, 
					mesh, 
					texture, 
					Math.random() * CANVAS_SIZE,
					Math.random() * CANVAS_SIZE);
		
		System.out.println("x: " + tree.x + "\t y: " + tree.y + " 위치에 " + type + " 나무 생성 완료");
				
	}
	
	// 나무 렌더링 최적화
	public void afterFlyweightRender(String type, double x, double y) {
		
		// 1. 캐시되어 있는 나무 모델 객체 가져오기
		TreeModel model = TreeModelFactory.getInstace(type);
		
		// 2. 재사용한 나무 모델 객체와 변화하는 속성(좌표값)으로 나무 생성
		FlyweightTree tree = new FlyweightTree(x, y, model);
		
		System.out.println("x: " + tree.x + "\t y: " + tree.y + " 위치에 " + type + " 나무 생성 완료");
		
	}
}
