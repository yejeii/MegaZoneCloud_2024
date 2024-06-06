package WhatIsCollection;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

abstract class TestPerformance<T> {

    protected final int MAX_VALUE = 100000;

    abstract protected void add(T object);

    abstract protected void remove(T object);

    abstract protected void contains(T object);

    abstract protected void get(T object);

    abstract public void run();


    /**
     * functionName 조회 함수
     *
     * @return string
     */
    protected String getInheritanceMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    /**
     * 성능 측정 결과를 양식에 맞추는 함수
     *
     * @param methodName 실행 되고 있는 className
     * @param startTime  시작 시간
     * @param endTime    종료 시간
     * @return result
     */
    protected String testResultToString(String methodName, String collectionName, long startTime, long endTime) {
        return String.format("[%s] %s 실행 시간: %f 초", collectionName, methodName, (double) (endTime - startTime) / 1000000000);
    }

}

class ArrayPerformance extends TestPerformance<List<Integer>> {
    final ArrayList<Integer> arrayList = new ArrayList<>();
    final LinkedList<Integer> linkedList = new LinkedList<>();
    final Vector<Integer> vector = new Vector<Integer>();

    @Override
    protected void add(List<Integer> object) {
        long startTime = System.nanoTime();
        for (int i = 0; i < MAX_VALUE; i++) {
            object.add(i);
        }
        long endTime = System.nanoTime();

        String methodName = getInheritanceMethodName();
        String collectionName = object.getClass().getSimpleName();
        System.out.println(testResultToString(methodName, collectionName, startTime, endTime));
    }

    @Override
    protected void remove(List<Integer> object) {
        Iterator<Integer> iterator = object.iterator();
        long startTime = System.nanoTime();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        long endTime = System.nanoTime();
        String methodName = getInheritanceMethodName();
        String collectionName = object.getClass().getSimpleName();
        System.out.println(testResultToString(methodName, collectionName, startTime, endTime));
    }

    @Override
    protected void contains(List<Integer> object) {
        long startTime = System.nanoTime();
        for (int i = 0; i < MAX_VALUE; i++) {
            object.contains(i);
        }
        long endTime = System.nanoTime();

        String methodName = getInheritanceMethodName();
        String collectionName = object.getClass().getSimpleName();
        System.out.println(testResultToString(methodName, collectionName, startTime, endTime));
    }

    @Override
    protected void get(List<Integer> object) {
        long startTime = System.nanoTime();
        for (int i = 0; i < MAX_VALUE; i++) {
            object.get(i);
        }
        long endTime = System.nanoTime();

        String methodName = getInheritanceMethodName();
        String collectionName = object.getClass().getSimpleName();
        System.out.println(testResultToString(methodName, collectionName, startTime, endTime));
    }

    /**
     * test 로직 실행
     */
    public void run() {
        add(arrayList);
        add(linkedList);
        add(vector);
        get(arrayList);
        get(linkedList);
        get(vector);
        contains(arrayList);
        contains(linkedList);
        contains(vector);
        remove(arrayList);
        remove(linkedList);
        remove(vector);
    }
}

class SetPerformance extends TestPerformance<Set<Integer>> {
    final HashSet<Integer> hashSet = new HashSet<>();
    final TreeSet<Integer> treeSet = new TreeSet<>();
    final LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
    

    @Override
    protected void add(Set<Integer> object) {
        long startTime = System.nanoTime();
        for (int i = 0; i < MAX_VALUE; i++) {
            object.add(i);
        }
        long endTime = System.nanoTime();

        String methodName = getInheritanceMethodName();
        String collectionName = object.getClass().getSimpleName();
        System.out.println(testResultToString(methodName, collectionName, startTime, endTime));
    }

    @Override
    protected void remove(Set<Integer> object) {
    	
        Iterator<Integer> iterator = object.iterator();
        long startTime = System.nanoTime();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        long endTime = System.nanoTime();
        String methodName = getInheritanceMethodName();
        String collectionName = object.getClass().getSimpleName();
        System.out.println(testResultToString(methodName, collectionName, startTime, endTime));
    }

    @Override
    protected void contains(Set<Integer> object) {
        long startTime = System.nanoTime();
        for (int i = 0; i < MAX_VALUE; i++) {
            object.contains(i);
        }
        long endTime = System.nanoTime();

        String methodName = getInheritanceMethodName();
        String collectionName = object.getClass().getSimpleName();
        System.out.println(testResultToString(methodName, collectionName, startTime, endTime));
    }

    @Override
    protected void get(Set<Integer> object) {

    }

    /**
     * test 로직 실행
     */
    public void run() {
        add(treeSet);
        add(hashSet);
        add(linkedHashSet);
        contains(treeSet);
        contains(hashSet);
        contains(linkedHashSet);
        remove(treeSet);
        remove(hashSet);
        remove(linkedHashSet);
    }
}

class Test {


    public void run() {
        ArrayPerformance arrayPerformance = new ArrayPerformance();
        arrayPerformance.run();

        SetPerformance setPerformance = new SetPerformance();
        setPerformance.run();
    }

}

public class PerformanceTest {

    public static void main(String[] args) {
        new Test().run();

    }
}
