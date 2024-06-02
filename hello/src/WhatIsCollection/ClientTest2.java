package WhatIsCollection;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.*;

/**
 * Collections class important methods_PART2
 * https://youtu.be/8w-TweaVZ8E?si=rSidjujFvU-k_vAR
 * 
 * - swap
 * - copy
 */
public class ClientTest2 {

	public static void main(String[] args) {
		
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(100);
		intList.add(200);
		intList.add(300);
		intList.add(400);
		
		System.out.println("Original content of initList:::: " + intList);

		
		/*
		 * 1. 해당 인덱스의 객체 위치 change.
		 *    If the specified positions are equal, invoking this method leaves the list unchanged.
		 */
		swap(intList, 0, 3);
		System.out.println("After calling swap intList content:::: " + intList);
		
		/*
		 * 2. copy(dest, src)
		 * 	  'src' list의 모든 요소를 'dest' list에 복사한다.
		 *    즉, src 인덱스로 접근, 각 요소의 주소값을 dest의 동일한 인덱스 요소로 복사한다.
		 */
		List<Integer> intList2 = new ArrayList<Integer>();
		intList2.add(5000);
		intList2.add(50000);
		copy(intList, intList2);
//		copy(intList2, intList);	ERROR :  java.lang.IndexOutOfBoundsException
		
		System.out.println("After calling copy content of intList and intList2::::");
		System.out.println("intList:::: " + intList);
		System.out.println("intList2:::: " + intList2);
	}

}
