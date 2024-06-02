package WhatIsCollection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * java.util.Arrays class important methods
 * https://youtu.be/VJcK-n6G0zw?si=NXSbdBXr3ErrO2o5
 * 
 * - asList
 * - sort
 * - binarySearch
 * - stream
 */
public class ClientTest4 {

	public static void main(String[] args) {

		/* 1. Fixed-size List 반환 */
		List<Integer> list = Arrays.asList(23,4,67,78,89,1000,12754);
//		list.add(34); ERROR
		list.forEach(System.out::println);
		System.out.println("-----------------------------------------");
		
		int arr[] = {1,4,5,6,7,70,89};
		System.out.println("-----------------------------------------");
		
		/* 2. Sorts the specified array nto ascending numerical order. */
		Arrays.sort(arr);
		for (int i : arr) {
			System.out.println(i);
		}
		
		System.out.println("-----------------------------------------");
		
		/* 3. Searches the specified array of ints for the specified value using the binary search algorith. */
		int binarySearch = Arrays.binarySearch(arr, 5);
		System.out.println("binarySearch : " + binarySearch);
		
		System.out.println("-----------------------------------------");
		
		/* 4. Returns a sequential Intstream with the specified array as its source 
		 *    Stream을 이용해 요소에 순차적으로 접근 가능 */
		IntStream stream = Arrays.stream(arr);
		stream.forEach(System.out::println);
		
	}

}
