package gof_FlyweightP;

import java.util.HashMap;
import java.util.Map;

/**
 * FlyweightFactory
 * 	- 경량 객체(여기선 나무 모델)를 만드는 공장 역할과 캐시 역할을 겸비하는 Flyeight 객체 관리 클래스
 * 
 * 	- Flyweight Pool
 * 	  HashMap 을 통해 key 와 나무 모델 객체를 저장하는 캐시 저장소 역할
 * 
 * 	- getInstace()
 *    Pool 에서 가져오고자 하는 객체가 있는지 확인.
 *    있으면 반환, 없으면 새로 생성 후 반환
 */
class TreeModelFactory {
	
	// Flyweight Pool - TreeModel 객체들을 Map 으로 등록, 캐싱
	private static final Map<String, TreeModel> cache = new HashMap<String, TreeModel>();
	
	// static factory method
	public static TreeModel getInstace(String key) {
		
		// 만약 캐시되어 있다면
		if(cache.containsKey(key)) {
			return cache.get(key);	// 그대로 가져와 반환
		} else {
			// 캐시 x -> 나무 모델 객체를 새로 생성, 반환
			TreeModel model = new TreeModel(
									key, 
									new Object(), 
									new Object()
			);
			
			System.out.println("-- 나무 모델 객체 새로 생성 완료 -- ");
			
			// 캐시에 적재
			cache.put(key, model);
			
			return model;
		}
	}
}
