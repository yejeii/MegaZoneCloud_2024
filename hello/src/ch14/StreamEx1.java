package ch14;

import java.util.stream.Stream;

/**
 * 1. 스트림의 개념 및 특징, 왜 스트림을 사용해야 하는지.
 * 
 * Collection 이 데이터 집합을 편리하게 사용할 수 있도록 표준화되어 
 * Collection FW 로 제공되고 있지만, 개선(코드의 재사용 -> 공용 메서드의 부재)해야 할 부분이 있음
 * 
 * List로 정렬하는 경우와 배열로 정렬하는 경우 (공용 메서드의 부재)
 * Collections.sort()
 * Arrays.sort()
 * 
 * Stream 을 사용하면 List, Array, File 종류 상관없이 동일한 인터페이스를 제공받으므로 코드의 "표준화" 목적 달성
 * 
 * - Stream 의 장점
 * 	 코드의 재사용성이 높아짐
 * 	 다양한 데이터 소스를 대상으로 동일한 방식으로 다룰 수 있다.
 * 
 * - Stream 의 특징
 *   1. 원본 데이터 소스를 변형시키지 않는다.
 *   2. 일회용이다.  
 *   3. 스트림의 작업은 내부 반복으로 처리함
 *   	ex. void forEach(Consumner<? super T> action)
 *   		-> forEach의 내부 구현부에는 반복문이 존재(내부 반복문)
 *   
 *			Consumer<T> : 하나의 매개변수를 받아서 처리만 함 (반환값 X)
 *						  ex. (T) -> System.out.println(T);
 *							  (T) -> System.out::println 
 * 
 * 2. 스트림의 연산
 * 	  1. 중간 연산
 * 		 스트림을 처리(연산) 후 스트림 반환 ( 중요 : 스트림 반환 )
 * 
 * 		 filter()
 * 		 map()
 * 
 * 	  2. 최종 연산
 * 		 스트림의 요소를 소모하면서 처리(연산)을 수행
 * 		 단 한번만 연산 가능( 중요 : 단 한번만 )
 * 
 * 		 collect()
 * 		 toList()
 * 
 * 	  스트림의 복합 연산의 예
 * 	  stream.distinct().sorted().forEach(System.out::println);
 * 		     <---- 중간 연산 ---->  <-------- 최종 연산 --------->
 * 			  : 꼬리에 꼬리를 물다 
 * 				(메서드 체인 형태)
 * 
 * 3. Stream 생성
 * 
 *    1. Collection
 *    	 Collection 에 stream() 을 사용
 *    	 List, Set 을 구현한 Collection 클래스들은 모두 stream() 을 이용하면 Stream 생성 가능
 *    	 
 *   	 // 데이터 소스 생성
 *    	 List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
 *    
 *    	 // Stream 생성
 *       Stream<Integer> intStream = list.stream();
 *       
 *       // 중간 연산 또는 최종 연산
 *       intStream.forEach(System.out::println);
 *       
 *       forEach 는 최종연산임으로 일회용.
 *       -> 다시 사용하려면 Stream 재생성
 *    
 *    2. Array
 *   	 배열을 기반으로 Stream 생성은 Stream 또는 Arrays 사용
 *       
 *       - 데이터 소스가 참조형 배열(요소가 String 과 같음)
 *         Stream<T> Stream.of(T... values) // 가변 인자
 *         Stream<T> Stream.of(T[]) 
 *         Stream<T> Arrays.stream(T[])
 *       
 *       - 데이터 소스가 기본형 배열ㄴ
 *         IntStream IntStream.of(int... values)
 *         IntStream IntStream.of(int[])
 *         IntStream Arrays.of(int[])
 *     
 *    3. File
 *    	 - Stream 대상에 대한 종류
 *    	   디렉토리 기준 : 디렉토리에 저장된 파일 목록
 *    			Stream<Path> Files.list(Path dir)
 *    	   파일 기준 : 파일 하나에 저장되어 있는 행 데이터 목록
 *    			Stream<String> Files.lines(Path path)
 *    	   파일의 한 행 기준 : 문자 목록
 *    			Stream<String> Files.lines()
 *    
 *    4. 빈 Stream 생성
 *    	 Stream emptyStream = Stream.empty();
 *    	 이때, 빈 Stream 은 null 이 아님!
 *     
 * 
 * 
 * 
 */
public class StreamEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
