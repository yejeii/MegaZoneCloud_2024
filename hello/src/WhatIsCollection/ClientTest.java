package WhatIsCollection;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.*;
import java.util.List;

/**
 * Collections class important methods_PART1
 * https://youtu.be/L3FHQ3c2-f0?si=CHGcv7yDiD27s9VG
 * 
 * - sort
 * - binarySearch
 * - unmodifiableCollection
 * - synchronizedList
 * - checkedCollection
 * - singletonList
 */
public class ClientTest {

	public static void main(String[] args) {
		
		List<String> personList = new ArrayList<String>();
		personList.add("Sam");
		personList.add("Andy");
		personList.add("Sean");
		personList.add("Jash");
		personList.add("Amy");
		personList.add("Martin");
		personList.add("Joseph");
		personList.add("Amy");
		
		System.out.println("Original list : " + personList);
		
		sort(personList);
		System.out.println("Sorted alphabetically list : " + personList);
		
		/* 
		 * 1. Specified object using the binary search algorithm
		 * 	  이때, The list must be sorted into ascending.
		 * 	  -> 인자 List의 제네릭 타입은 Comparable을 상속해야 한다.
		 * 	  	 String은 Comparable을 상속하기 때문에 가능
		 */
		int binarySearch = binarySearch(personList, "Martin");
		System.out.println("Index of the searched key : " + binarySearch);
		
		/*
		 * 2. Returns an unmodifiable view of the spcified collection.
		 * 	  -> "Read-Only" 
		 *    -> Attempts to modify the returned collection, whether direct or via its iterator, 
		 *       result in an UnsupportedOperationException.
		 * 
		 */
		Collection<String> unmodifiableCllCollection = unmodifiableCollection(personList);
		System.out.println("Unmodifiable Collection:::" );
		System.out.println(unmodifiableCllCollection);
		
		// 변경 시도
//		unmodifiableCllCollection.add("KK"); ERROR
		
		/*
		 * 3. Returns a synchronized (thread-safe) list backed by the specified list.
		 *    -> All operation will be thread-safe.
		 *    
		 *    단, Iterator, Spliterator 또는 Stream을 통한 탐색 시, 사용자는 반환된 목록을 수동으로 동기화해야 한다.
		 *    synchronized (list) {
	     *    	Iterator i = list.iterator(); // Must be in synchronized block
	     *      while (i.hasNext())
	     *      	foo(i.next());
	     *    }
		 * 
		 */
		List<String> synchronizedList = synchronizedList(personList);
		System.out.println("Synchronized List:::");
		System.out.println(synchronizedList);
		
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println();
		
		
		List list = new ArrayList();
		list.add("KK");
		list.add("PK");
		list.add("MP");
		list.add(21);
		list.add(34.00);
		
		/*
		 * 4. Returns a dynamically typesafe view of the specified collection.
		 *    Any attempt to insert an element of the wrong type will result in an immediate ClassCastException.
		 *    
		 *    list 생성 시, Generic을 명시하지 않았기에 다양한 타입의 데이터가 저장됨
		 *    checkCollection(list, String.class) -> list 요소를 String.class 타입으로 변환한 새 Collection을 리턴
		 *    후에 이 Collection에 String 형이 아닌 타입의 데이터를 넣으면 예외 발생
		 */
		Collection checkedCollection = checkedCollection(list, String.class);
		System.out.println("Checked list content : " + checkedCollection);
		
		// Can add any type of elements to list object
		list.add(190);
		
//		checkedCollection.add(180);	ERROR
		
		/*
		 * 5. 지정된 객체만 포함하는 변경할 수 없는 list 반환 
		 * 	  -> 오직 하나의 요소만 가지고 있음
		 *    반환된 list 는 직렬화가 가능하다.
		 * 
		 */
		List<String> singletonList = singletonList("Admin");
//		singletonList.add("Admin");	Error : UnsupportedOperationException
//		singletonList.add("Manager");	Error : UnsupportedOperationException
		System.out.println(singletonList);
		
		
		
	}
}
