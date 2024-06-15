package gof_FlyweightP;

/**
 * ConcreteFlyweight 
 *  - 공유 가능하여 재사용되는 객체 (Intrinsic state) 
 *    즉, 값이 고정되어 장소 혹은 상황에 의존하지 않고 공유되는 객체를 의미
 * 	
 * final + private : 불변 객체
 * - 나무 모델 객체는 중간에 메시와 텍스처 속성이 변경될 일이 없기 떄문.
 *   ConcreteFlyweight 객체 속성이 변경되면 모든 것에 영향을 준다!
 *    
 */
final class TreeModel {
	
	// 메시, 텍스처 총 사이즈
	private long objSize = 90;	// 90MB
	
	private String type;	// 나무 종류
	private Object mesh;	// 메쉬
	private Object texture;	// 나무 껍질 + 잎사귀 텍스처
	
	public TreeModel(String type, Object mesh, Object texture) {
		this.type = type;
		this.mesh = mesh;
		this.texture = texture;
	
		// 나무 객체를 생성하여 메모리에 적재했으니 메모리 사용 크기 증가
		Memory.size += objSize;
	}
}
