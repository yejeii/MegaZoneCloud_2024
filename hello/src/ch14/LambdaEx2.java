package ch14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 2. 함수형 인터페이스의 JAVA API
 * 
 * 목적 : Stream 의 API 문서를 보고 사용하기 위함
 * 	     Stream 의 메서드를 사용해야하는데, 이때 각 메서드의 매개변수를 이해하고 사용하기 위함
 * 
 * - 패키지 : java.util.function
 * 
 * - 매개변수가 없거나 하나인 함수형 인터페이스 ( 종류 및 의미 )
 * 	 1. Supplier<T>	
 * 		T get();			// 매개변수 X, 반환값 O
 * 	 2. Consumer<T>
 * 		void accept(T t);	// 메개변수 O, 반환값 X
 *   3. Function<T, R>
 *   	R apply(T t); 		// 일반적인 함수. 하나의 매개변수 받아서 처리 후 결과 반환
 *   4. Predicate<T>
 *   	boolean test(T t);	// 조건식을 표현할 대 사용. 매개변수 O, 반환값 O
 * 
 * - 매개변수가 두 개인 함수형 인터페이스 ( 종류 및 의미 )
 * 	 1. BiConsumer<T,U>
 * 		void accept(T t, U u);	// 메개변수 O, 반환값 X
 *   2. BiFunction<T,U,R>
 *   	R apply(T t, U u);		// 매개변수 O, 반환값 O
 *   3. BiPredicate<T,U>
 *   	boolean test(T t, U u);	// 매개변수 O, 반환값 O
 *   
 * - Operator
 * 	 1. UnaryOperator<T> ( Unary : 단항 )
 *     	-> 매개변수가 하나
 *         Function<T> 와 동일하나, 차이가 있다면 매개변수와 결과의 타입이 동일함
 *     
 *   2. BinaryOperator<T> ( Binary : 이항 )
 *   	-> 매개변수가 2개
 *   	   Function<T> 와 동일하나, 차이가 있다면 매개변수와 결과의 타입이 동일함
 *   
 * - 함수형 API가 매개변수로 사용되는 JAVA API 메서드
 *  
 *   Map : key, value 로 구성된 자료형 -> 데이터 추가시 매개변수가 2개임
 *   void forEach(BiConsumer<K, V> action)
 *   -> Map의 모든 요소에 작업 action 수행
 *   -> BiConsumer<K, V> action : 함수형 인터페이스로 받은 것
 *   
 *   Collection
 *   boolean removeIf(Predicate<E> filter)
 *   -> 필터하여 조건에 해당 요소를 삭제
 *   
 */
public class LambdaEx2 {

	public static void main(String[] args) {

		/* 데이터 소스(ArrayList) */
		ArrayList<Integer> aList = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			aList.add(i);
		}
		
		// forEach()
		// 함수형 인터페이스 : Consumer<? super E> action
		// 매개변수 O, 반환값 X
		aList.forEach(i -> System.out.print(i+", "));
		
//		ArrayList<Integer> arrayList = (ArrayList<Integer>) aList.stream().filter(i -> i%2==0).toList();
//		arrayList.forEach(i -> System.out.print(i+", "));

		System.out.println();
		
		// removeIf()
		// 함수형 인터페이스 : Predicate<? super E> filter
//		aList.removeIf(new Predicate<Integer>() {
//			@Override
//			public boolean test(Integer i) {
//				return i%2==0;
//			}
//			
//		});	// 람다식을 익명 클래스로 표현한 것. 아래와 같은 의미
		aList.removeIf(i -> i%2==0);
		aList.forEach(i -> System.out.print(i+", "));
		
		System.out.println();
		
		// replaceAll()
		// 함수형 인터페이스 : UnaryOperator<E> operator
		// Function<T> 와 차이점 : 인자 타입과 반환 타입이 동일한 점
		aList.replaceAll(i -> i*10);
		aList.forEach(i -> System.out.print(i+", "));
		
		/* 데이터 소스(Map) */
		Map<String, String> map = new HashMap<>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		
		System.out.println();
		
		// forEach()
		// 함수형 인터페이스 : BiConsumer<? super K, ? super V> action
		// 두 개의 인자를 받고, 반환값 X
		map.forEach((k, v) -> System.out.printf("{k : %s, v : %s} \t", k, v));
		
		System.out.println();
		
		
		
	}

}
