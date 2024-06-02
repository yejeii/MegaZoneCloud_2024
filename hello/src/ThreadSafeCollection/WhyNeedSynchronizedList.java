package ThreadSafeCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 14. How to convert arraylist into synchronized arraylist / Why we need synchronized arrayList
 * https://youtu.be/Bq68qatX9yY?si=FYcBoj9um7MrIPjd
 * 
 * 해결 안된 문제
 * 왜 temp~는 안되고 있는건지..
 * 
 */
public class WhyNeedSynchronizedList {

//	static List<String> syncList = new ArrayList<String>();
	static List<String> syncList = Collections.synchronizedList(new ArrayList<String>());	// synchronized version of ArrayList
	
	public static void main(String[] args) throws InterruptedException {
		
		ThreadDemo1 thread1 = new ThreadDemo1();
		ThreadDemo2 thread2 = new ThreadDemo2();
		
		// add
		for (int i = 0; i < 10; i++) {
			syncList.add("item"+i);
//			Thread.sleep(500);
		}
		
		// Runnable 상태로 대기
		thread2.start();	// Error
		thread1.start();	
		
		/* Error : ConcurrentModificationException
		 * Why it occured?
		 * 
		 * While iterating the list, thread2 tried to add new elements.
		 * -> 한 객체에 대한 데이터 일관성이 깨질 때 예외가 발생한 것.
		 * 
		 * To solve this problem, change ArrayList to synchronized ArrayList.
		 * We can use Collections.synchronizedList method and synchronized block.
		 * In other way, create new CopyOnWriteArrayList instance.
		 * 
		 * In this code, we will use synchronizedList.
		 */
	}

}

/* Create thread */
class ThreadDemo1 extends Thread {

	@Override
	public void run() {
		
		// add synchronized block
		synchronized (WhyNeedSynchronizedList.syncList) {
			Iterator<String> it = WhyNeedSynchronizedList.syncList.iterator();
			while(it.hasNext()) {
				System.out.println(it.next());
			}
		}
	}
}

class ThreadDemo2 extends Thread {
	
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			WhyNeedSynchronizedList.syncList.add("temp"+i);
		}
	}
}
