package gof_FlyweightP;

/**
 * UnsharedConcreteFlyweight
 * 	- 공유 불가능한 객체 (extrinsic state)
 * 	- 장소나 상황에 의존 -> 값이 유동적인 객체
 */
class FlyweightTree {
	
	// 좌표값과 나무 모델 참조 객체 크기를 합친 사이즈
	long objSize = 10;
	
	// 위치변수
	double x;
	double y;
	
	// 나무 모델
	TreeModel model;

	public FlyweightTree(double x, double y, TreeModel model) {
		this.x = x;
		this.y = y;
		this.model = model;
		
		// 나무 객체를 생성하였으니 메모리 사용 크기 증가
		Memory.size += objSize;
	}
}
