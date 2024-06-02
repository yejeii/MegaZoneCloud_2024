package WhatIsCollection;

import static java.util.Collections.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Collections class important methods_PART3
 * https://youtu.be/ttVcUdmS6TQ?si=z8TH3DWKFdfpD55C
 * 
 * - disjoint
 * - nCopies
 * - replaceAll
 * - EMPTY_LIST / SET / MAP
 */
public class ClientTest3 {

	public static void main(String[] args) {

		List<Integer> intList = new ArrayList<Integer>();
		intList.add(100);
		intList.add(200);
		intList.add(300);
		intList.add(400);
		
		List<Integer> intList2 = new ArrayList<Integer>();
		intList2.add(100);
		intList2.add(100);
		intList2.add(30);
		intList2.add(40);
		
		/*
		 * 1. 두 컬렉션 사이에 공통된 요소가 없으면 true 반환
		 */
		boolean disjoint = disjoint(intList, intList2);
		System.out.println(disjoint);
		
		/*
		 * 2. 새로운 배열 생성 후 모두 20000로 초기화
		 */
		List<Integer> list3 = nCopies(5, 20000);
		list3.forEach(System.out::println);
		
		/*
		 * 3. 100의 값을 5000으로 replace
		 */
		replaceAll(intList2, 100, 5000);
		System.out.println("After replaceAll : " + intList2);
		
		/*
		 * 4. Create new EmtpyList.
		 *    The empty list immutable and serializable.
		 */
		List emptyList = EMPTY_LIST;
		Set emptySet = EMPTY_SET;
		Map emptyMap = EMPTY_MAP;
		
		
	}

}
