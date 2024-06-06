package WhatIsStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java Streams Part 2 - Filter Method | Filtering Collection by using Stream | Hands-On
 * https://youtu.be/DvQVdJB4Va0?si=Zs_RiwjBziy_iDBJ
 * 
 * - filter().collect()
 * - filter().forEach()
 */
public class FilterDemo1 {

	public static void main(String[] args) {
		
		/* ArrayList 생성 1 */
//		ArrayList<Integer> numbersList = new ArrayList<Integer>();
//		numbersList.add(10);
//		numbersList.add(15);
//		numbersList.add(20);
//		numbersList.add(25);
//		numbersList.add(30);
		
		/* ArrayList 생성 2 
		 * Fixed-size List 반환 */
		List<Integer> numbersList = Arrays.asList(10,15,20,25,30);
		List<Integer> evenNumbersList = new ArrayList<Integer>();
		
		/* Without use streams */
//		for(int n:numbersList) {
//			if(n % 2 == 0) {
//				// 짝수
//				evenNumbersList.add(n);
//			}
//		}
		
		/* 
		 * With streams 
		 * 1. stream() : add numberList to Stream.
		 * 2. filter(Predicate<? > predicate) 
		 * 		Predicate : Return boolean value
		 * 		'n%2==0' is a condition.
		 * 		-> By the condition, it will return true/false.
		 * 3. collect() : After filter(by the result value(T/F)), collect data and put them toList.
		 *  - */
		evenNumbersList = numbersList.stream().filter(n -> n%2==0).collect(Collectors.toList());
		System.out.println(evenNumbersList);
		
		/* 
		 * forEach() : After filter, getting each and every element, do action(print it).
		 * 			   Doesn't return anything.
		 */
//		numbersList.stream().filter(n -> n%2==0).forEach(n -> System.out.println(n));
		numbersList.stream().filter(n -> n%2==0).forEach(System.out::println);
		
		System.out.println("--------------------------------------------------------");
		
		List<String> names = Arrays.asList("Melisandre", "Sansa", "구지영", "하동하", "Jon", "Daenerys", "Joffery");
		List<String> longNames = new ArrayList<String>();
		
		/*
		 * filter().collect()
		 * name 에 필터(filter)를 걸어 문자열 길이가 6과 8 사이에 있는 name 을 모아서(collect) list 로 반환
		 */
		longNames = names.stream().filter(name -> name.length()>6 && name.length()<8).collect(Collectors.toList());
		System.out.println(longNames);
		
		// forEach() 로 순회, print
		names.stream().filter(name -> name.length()>6 && name.length()<8).forEach(name -> System.out.println(name));

		names.stream().filter(name -> name.length()>6 && name.length()<8).forEach(System.out::println);
		
		System.out.println("--------------------------------------------------------");
		
		List<String> words = Arrays.asList("cup", null, "Forest", "sky", "book", null, "theater");
		
		/*
		 * If word is not null, 
		 */
		List<String> result = words.stream().filter(w -> w!=null).collect(Collectors.toList());
		System.out.println(result);
		
	}

}
