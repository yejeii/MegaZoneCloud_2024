package ch12;

import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * ==================== 와일드 카드 적용 =======================
 * 
 * 메서드의 중복 선언을 막기 위한 용도로 사용
 * 
 * 메서드 중복의 해결 방법: 기반 타입으로 매개변수 타입이 되도록 하는 것.
 * <? super T> : 하한 제한. T와 T의 기반(조상)들만 가능  (위로만 가능)
 * <? extends T> : 상한 제한. T와 그 파생(자식)들만 가능 (아래로만 가능)
 * <?> : 제한 X. 모든 타입 가능. <? extends Object>
 * 
 * <? super T> : down casting 을 위한 문법적 의미
 * <? extends T> : up casting 을 위한 문법적 의미
 * 
 * 와일드 카드의 상한 제한 관련 코드
 * 
 * - Generic 메서드 : 메서드 선언부에 Generic 타입이 지정된 메서드
 * 
 *   static 멤버에서는 Generic 사용 불가.
 *   그러나 Generic 메서드에서는 Generic 타입은 지역 변수처럼 메서드 내에서만 지역적으로 사용됨.
 * 
 *   public static <T extends Comparable<? super T>> void sort(List<T> list) {
 *   	list.sort(null);
 *   }
 *   
 *   <T extends Comparable>
 *   제한된 Generic 클래스 -> 관계성 적용 -> Comparable 와의 관계를 적용
 *   -> "Comparable 을 구현한 클래스"
 *   
 *   <T extends Comparable> void sort(List<T> list)
 *   -> "List<T> 의 요소는 Comparable 을 구현한 것이어야 함"
 *   
 * 
 * 
 * 
 */
public class GenericsEx3 {
	
	public static void main(String[] args) {
		
		FruitBox2<Apple3> aList = new FruitBox2();
		aList.add(new Apple3("1 Apple", 29));
		aList.add(new Apple3("2 Apple", 56));
		aList.add(new Apple3("3 Apple", 54));
		
		FruitBox2<Grape3> gList = new FruitBox2();
		gList.add(new Grape3("1 Grape", 19));
		gList.add(new Grape3("2 Grape", 36));
		gList.add(new Grape3("3 Grape", 34));
		
		/*
		 * sort(List<T> list, Comparator<? super T> c)
		 * 1. list, Comparator<Apple3>
		 * 2. list, Comparator<Grape3>
		 * 3. list, Comparator<Fruit3>
		 * 
		 * >생각해보기<
		 * Java API인 Collections.sort() 
		 * - 두 번째 매개변수가 왜 하한제한 와일드 카드가 적용되어 있는가?
		 * 
		 * sort(List<T> list, Comparator<T> c) 의 경우,
		 * AppleComparator, GrapeComparator, FruitComparator 구현이 모두 필요
		 * 
		 * -> 다형성을 적용하면 Fruit 만 있으면 됨
		 * -> FruitComparator 만 구현 필요하기 위해 <? super T> 로 설정..
		 * 
		 */
//		Collections.sort(aList.getList(), new AppleComparator());
//		Collections.sort(gList.getList(), new GrapeComparator());
//		System.out.println(aList);
//		System.out.println(gList);
		
		System.out.println();
		
		Collections.sort(aList.getList(), new FruitComparator());
		// -> 컴파일 후
		// Collections.sort(aList.getList(), new AppleComparator());
		
		Collections.sort(gList.getList(), new FruitComparator());
		System.out.println(aList);
		System.out.println(gList);
		
		
	}

}

class Fruit3 {
	private String name;	// 과일 이름
	private int weight;		// 과일 무게
	
	public Fruit3(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return name + "( " + weight +" )";
	}
}

class Apple3 extends Fruit3 {

	public Apple3(String name, int weight) {
		super(name, weight);
	}
}

class Grape3 extends Fruit3 {

	public Grape3(String name, int weight) {
		super(name, weight);
	}
}

class FruitBox2<T extends Fruit3 > extends Box3<T> {

}

class Box3<T> {
	ArrayList<T> list = new ArrayList<>();
	
	void add(T item) { list.add(item); }
	T get(int i) { return list.get(i); }
	public ArrayList<T> getList() { return list; }

	int size() { return list.size(); }
	public String toString() { return list.toString(); }
}

/*
 * Collections.sort() 의 두 번째 매개변수로 사용될 Comparator 구현
 */
class AppleComparator implements Comparator<Apple3> {

	public int compare(Apple3 o1, Apple3 o2) {
		
		return o1.getWeight() - o2.getWeight();
	}
	
}

class GrapeComparator implements Comparator<Grape3> {

	public int compare(Grape3 o1, Grape3 o2) {
		
		return o1.getWeight() - o2.getWeight();
	}
	
}

class FruitComparator implements Comparator<Fruit3> {

	public int compare(Fruit3 o1, Fruit3 o2) {
		
		return o1.getWeight() - o2.getWeight();
	}
	
}










