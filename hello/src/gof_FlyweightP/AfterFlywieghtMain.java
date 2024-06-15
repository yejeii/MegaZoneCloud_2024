package gof_FlyweightP;

public class AfterFlywieghtMain {

	public static void main(String[] args) {

		// 지형 생성
		Terrain terrain = new Terrain();

		// 지형에 Oak 나무 5 그루 생성
		createTree5(terrain, "Oak");

		// 지형에 Acacia 나무 5 그루 생성
		createTree5(terrain, "Acacia");

		// 지형에 Jungle 나무 5 그루 생성
		createTree5(terrain, "Jungle");

		// 총 메모리 사용률 출력
		Memory.print();
	}

	public static Terrain createTree5(Terrain terrain, String type) {

		// 지형에 나무 5 그루 생성
		for (int i = 0; i < 5; i++) {
			terrain.afterFlyweightRender(
					type, 
					Math.random() * Terrain.CANVAS_SIZE,
					Math.random() * Terrain.CANVAS_SIZE);
		}

		return terrain;
	}
}
