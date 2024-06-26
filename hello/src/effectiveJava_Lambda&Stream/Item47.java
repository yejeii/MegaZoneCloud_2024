package effectJava7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Item 47
 * 
 * 스트림 패러다임의 핵심 : 계산을 일련의 변환으로 재구성한다.
 * 이때, 각 변환 단계는 가능한 한 이전 단계의 결과를 받아 처리하는 순수 함수여야 한다.
 * 순수 함수란, 오직 입력만이 결과에 영향을 주는 함수를 말한다.
 * 		     다른 가변 상태를 참조하지 않고, 함수 스스로도 다른 상태를 변경하지 않는다.
 * 이렇게 하려면, 스트림 연산에 건네는 함수 객체는 모두 부작용이 없어야 한다.
 * 
 * forEach 종단 연산은 계산 자체에는 이용하지 말자. "보고용"으로만 사용하자.
 */
public class Item47 {

	/*
	 * 문제의 코드 : 스트림, 람다, 메서드 참조를 사용하고는 있으나 절대 스트림 코드라 할 수 없다!
	 * 
	 * Why?
	 * forEach 에서 내부적으로 외부 상태(freq)를 수정하는 람다를 실행하고 있다.
	 * forEach 가 그저 스트림이 수행한 연산 결과를 보여주는 일 이상을 하고 있기 때문!
	 * 
	 * for-each 반복문은 forEach 종단 연산과 비슷하게 생겼다.
	 * 하지만 forEach 연산은 종단 연산 중 기능이 가장 적고 가장 '덜' 스트림답다.
	 * 대놓고 반복적이라서 병렬화할 수도 없다.
	 * 
	 * -> forEach 연산은 스트림 계산 결과를 "보고"할 떄만 사용하고, 계산하는 데는 쓰지 말자.
	 */
	public static void wrongEx(String[] args) {
		
		Map<String, Long> freq = new HashMap<String, Long>();
		try(Stream<String> words = new Scanner(new File("path")).tokens()){
			
//			words.forEach(new Consumer<String>() {
//				@Override
//				public void accept(String word) {
//					freq.merge(word.toLowerCase(), 
//								1L, 
//								new BiFunction<Long, Long, Long>() {
//									@Override
//									public Long apply(Long word, Long l) {
//										return Long.sum(word, l);
//									}
//								});
//				}
//			});

//			words.forEach(word -> {
//				freq.merge(word.toLowerCase(), 1L, (l1, l2) -> Long.sum(l1, l2));
//			});
			
			words.forEach(word -> {
				freq.merge(word.toLowerCase(), 1L, Long::sum);
			});
		} catch (Exception e) {
		}
		
	}
	
	/*
	 * 위의 잘못된 코드 수정 후
	 * 
	 * Stream.collect 사용 : 스트림의 원소들을 객체 하나에 취합한다.
	 * 수집기를 사용하면 스트림의 원소를 쉽게 컬렉션으로 모을 수 있다.
	 * 
	 * Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier,
     *                                       Collector<? super T, A, D> downstream)
	 * 	- classifier : 분류 기준
	 * 	- downstream : 집계 방식
	 */
	public static void main(String[] args) {
		Map<String, Long> freq;
		
		try(Stream<String> words = new Scanner(new File("path")).tokens()){
			freq = words.collect(groupingBy(String::toLowerCase, counting()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
