package ThreadSafeCollection;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * How to Synchronize (ThreadSafe) ArrayList in Java | What is CopyOnWriteArrayList class in Java
 * ( WhatIsCollection 패키지의 ClientTest 클래스 참고 )
 * https://youtu.be/StBSynRWI_U?si=jQBRzQiBqRjCJlRB
 * 
 * 멀티스레드 프로그래밍은 동시에 여러 스레드가 실행되는 환경에서 프로그램을 개발하는 것을 의미한다.
 * 이는 병렬처리를 통해 성능을 향상시킬 수 있지만, 동시에 동기화 문제와 스레드 안전성 문제를 해결해야 하는 어려움이 있다.
 * 
 * 멀티스레드 프로그래밍에서는 여러 스레드가 동시에 접근할 수 있는 공유 데이터에 대한 접근 동기화해야 한다.
 * 그렇지 않으면 데이터의 일관성이 깨질 수 있다.
 * 
 * 컬렉션 프레임워크에서는 스레드 안전성을 보장하기 위한 여러 클래스를 제공한다. 
 * Ex. ConcurrentHashMap, ConcurrentLinkedQueue, CopyOnWriteArrayList 등
 * 
 * 이러한 스레드 안전한 컬렉션들은 내부적으로 동기화 메커니즘을 사용하여 여러 스레드에 의한 동시 접근을 제어한다.
 * 
 * 그러나 ArrayList는 기본적으로 동기화를 지원하지 않는다.
 * 아래는 ArrayList를 동기화하는 방법이다.
 * 
 * 1. Collections.synchronizedList (method) - return synchronized list
 *    block : synchronized
 * 
 * 2. CopyOnWriteArrayList (class) - Thread Safe variant of ArrayList
 */
public class MakeSynchronizedArrayList {

	public static void main(String[] args) {

		/* 1 : Use Collections.synchronizedList 
		 *     Collections.synchronizedList를 사용하여 스레드 안전한 리스트를 생성 후,
		 *     synchronized block을 이용하여 여러 스레드에서 동시에 접근하는 부분을 동기화 */
		List<String> namesList = Collections.synchronizedList(new ArrayList<String>());
		namesList.add("Java");
		namesList.add("Python");
		namesList.add("Ruby");
		
		// For add or remove action, we don't need explicit synchronization
		
		// But to fetch(값 가져오기) or traverse(탐색) the values form this list, We have to use explicit synchronization
		synchronized (namesList) {
			Iterator<String> it =  namesList.iterator();
			
			while(it.hasNext())  {
				System.out.println(it.next());
			}
		}
		
		/* 2 : Use CopyOnWriteArrayList - Thread-Safe/Synchronized already. */
		CopyOnWriteArrayList<String> empList = new CopyOnWriteArrayList<String>();
		empList.add("Tom");
		empList.add("Steve");
		empList.add("Naveen");
		
		// Don't need any explicit synchronization for any operation : add/remove/traverse
		
		// iterate
		Iterator<String> it = empList.iterator();
		
		while(it.hasNext())  {
			System.out.println(it.next());
		}
		
		
		
	}

}
