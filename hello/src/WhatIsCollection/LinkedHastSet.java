package WhatIsCollection;

import java.util.Arrays;

public class LinkedHastSet<E> implements Set<E> {

	// Node 배열의 기본 최소 용적(요소를 담을 배열 크기). 2^n 꼴 형태가 좋다
	private final static int DEFAULT_CAPACITY = 1 << 4;	// 0001 0000 (16)

	// 테이블의 크기에 비해 데이터양이 일정 이상 많아지면 성능이 저하됨 : 기준점을 0.75로 설정
	// 3/4 이상 채워질 경우 resize 하기 위한 임계값
	private final static float LOAD_FACTOR = 0.75f;
	
	Node<E>[] table;	// 요소의 정보를 담고 있는 Node 를 저장할 Node 타입 배열
	private int size;	// 요소의 개수
	
	// 들어온 순서를 유지할 LinkedList
	private Node<E> head; // 리스트의 가장 첫 노드를 가리키는 변수
	private Node<E> tail; // 리스트의 가장 마지막 노드를 가리키는 변수
	
	@SuppressWarnings("unchecked")
	public LinkedHastSet() {
		this.table = new Node[DEFAULT_CAPACITY];
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	// 보조 해시 함수 (상속 방지를 위해 private final static 선언)
	private final static int hash(Object key) {
		int hash;
		if(key == null) {
			return 0;
		}
		// hashCode() 의 경우 음수가 나올 수 있으므로 절댓값(abs)을 통해 양수로 변환
		else {
			return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
		}
	}

	private void linkLastNode(Node<E> o) {
		Node<E> last = tail;	// 마지막 노드를 가져온다.
		
		tail = o;	// tail 을 새로운 노드 o 가 가리키도록 갱신
		
		/*
		 * 만약 마지막 노드가 null 이면, 노드에 저장된 데이터가 없는 즉, head 노드도 null 인 상태라는 것
		 * -> head 도 새 노드인 o 를 가리키도록 한다.
		 * 만약 마지막 노드가 null 이 아니면, 새 노드 o 의 이전 노드를 가리키는 노드를 last 로 두고,
		 * last 의 다음 노드는 새 노드인 o 를 가리키도록 한다.
		 */
		if(last == null) { head = o; }
		else {
			o.prevLink = last;
			last.nextLink = o;
		}
		
	}
	
	@Override
	public boolean add(E e) {
		return add(hash(e), e) == null;
	}

	private Object add(int hash, E key) {
		int idx =  hash % table.length;
		
		Node<E> newNode = new Node<E>(hash, key, null);	// 새로운 노드
		
		if(table[idx] == null) {
			table[idx] = newNode;
		} else {
			Node<E> temp = table[idx];
			Node<E> prev = null;
			
			// 해당 인덱스의 마지막 체인 노드까지 탐색
			while(temp != null) {
				
				// 동일 객체라면 저장하지 않고 key 반납
				if((temp.hash == hash) && (temp.key == key || temp.key.equals(key))) {
					return key;
				}
				prev = temp;
				temp = temp.next;
			}
			
			prev.next = newNode;
		}
		size++;
		
		// table 에 저장이 끝났으면 해당 노드를 연결
		linkLastNode(newNode);	
		
		// 데이터 개수가 현재 table 용적의 75% 넘어가는 경우 용적 resize
		if(size >= LOAD_FACTOR * table.length) {
			resize();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		
		int newCapacity = table.length * 2;
		
		// 기존 데이터의 두배 용적으로 생성
		final Node<E>[] newTable = new Node[newCapacity];
		
		// 0 번째 index 부터 차례로 순회
		for(int i=0; i<table.length; i++) {
			
			// 각 인덱스의 첫 번째 노드(head)
			Node<E> value = table[i];
			
			// 해당 값이 없을 경우 다음으로 넘어감
			if(value == null) { continue; }
			
			table[i] = null;	// GC
			
			Node<E> nextNode;	// 현재 노드의 다음 노드를 가리키는 변수
			
			// 현재 인덱스에 연결된 노드들을 순회
			while(value != null) {
				
				int idx = value.hash % newCapacity;	// 새로운 인덱스 구한다
				
				/*
				 * 새로 담을 index 에 요소(노드)가 존재할 경우
				 * == 새로 담을 newTable 에 index 값이 겹칠 경우(해시 충돌)
				 */
				if(newTable[idx] != null) {
					Node<E> tail = newTable[idx];
					
					// 가장 마지막 노드로 간다
					while(tail.next != null) {
						tail = tail.next;
					}
					
					/*
					 * 반드시 value 가 참고하고 있는 다음 노드와의 연결을 끊어야 한다.
					 * 안하면 각 인덱스의 마지막 노드(tail)도 다른 노드를 참조하게 되어 잘못된 참조가 발생할 수 있다.
					 */
					nextNode = value.next;
					value.next = null;
					tail.next = value;
				} else {
					// 충돌 발생 X -> 빈 공간이라면 해당 노드 추가
					
					nextNode = value.next;
					value.next = null;
					newTable[idx] = value;
				}
				
				value = nextNode;	// 다음 노드로 이동
			}
		}
		
		table = null;
		table = newTable;	// 새로 생성한 table 을 table 변수에 할당
	}

	// 연결리스트의 링크를 끊어준다
	private void unlinkNode(Node<E> o) {
		Node<E> preNode = o.prevLink;	// 삭제하는 노드의 이전 노드
		Node<E> nextNode = o.nextLink;	// 삭제하는 노드의 이후 노드
		
		/*
		 * preNode 가 null 이라는 것은 삭제한 노드가 head 노드였다는 것을 의미
		 * -> 그 다음 노드인 nextNode 가 head 가 된다.
		 */
		if(preNode == null) {
			head = nextNode;
		} else {
			preNode.nextLink = nextNode;
			o.prevLink = null;
		}
		
		/*
		 * nextNode 가 null 이라는 것은 삭제한 노드가 tail 이라는 의미
		 * -> 이전 노드가 tail 이 된다.
		 */
		if(nextNode == null) {
			tail = preNode;
		} else {
			nextNode.prevLink = preNode;
			o.nextLink = null;
		}
	}
	
	
	@Override
	public boolean remove(Object o) {
		// null 이 아니라는 것은 노드가 삭제되었다는 의미
		return remove(hash(o), o) != null;
	}

	private Object remove(int hash, Object key) {
		
		int idx = hash % table.length;
		 
		Node<E> node = table[idx];
		Node<E> removedNode = null;
		Node<E> prev = null;
	 
		if (node == null) {
			return null;
		}
	 
		while (node != null) {
			// 같은 노드를 찾았다면
			if (node.hash == hash && (node.key == key || node.key.equals(key))) {
	 
				removedNode = node; // 삭제되는 노드를 반환하기 위해 담아둔다.
	 
				// 해당노드의 이전 노드가 존재하지 않는 경우 (= table에 첫번째 체인 노드인 경우)
				if (prev == null) {
					table[idx] = node.next;
				}
				// 그 외엔 이전 노드의 next를 삭제할 노드의 다음노드와 연결해준다.
				else {
					prev.next = node.next;
				}
					
				// table의 체인을 끊었으니 다음으로 순서를 유지하는 link를 끊는다.
				unlinkNode(node);
				node = null;
	 
				size--;
				break;	// table에서 삭제되었으니 탐색 종료
			}
			prev = node;
			node = node.next;
		}
	 
		return removedNode;
	}

	@Override
	public boolean contains(Object o) {
		int idx = hash(o) % table.length;
		Node<E> temp = table[idx];
	 
		/*
		 *  같은 객체 내용이어도 hash값은 다를 수 있다.
		 *  하지만, 내용이 같은지를 알아보고 싶을 때 쓰는 메소드이기에
		 *  hash값은 따로 비교 안해주어도 큰 지장 없다.  
		 *  단, o가 null인지는 확인해야한다.
		 */
		while (temp != null) {
			if (o == temp.key || (o != null && temp.key.equals(o))) {
				return true;
			}
			temp = temp.next;
		}
	 
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		if (table != null && size > 0) {
			for (int i = 0; i < table.length; i++) {
				table[i] = null;
			}
			size = 0;
		}
		head = tail = null;	// 마지막에 반드시 head와 tail을 끊어주어야 gc처리가 된다.
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
 
		// 만약 파라미터 객체가 현재 객체와 동일한 객체라면 true
		if(o == this) {
			return true;
		}
		// 만약 o 객체가 LinkedHashSet 계열이 아닌경우 false
		if(!(o instanceof LinkedHastSet)) {
			return false;
		}
			
		LinkedHastSet<E> oSet;
		
		/*
		 *  Object로부터 LinkedHashSet<E>로 캐스팅 되어야 비교가 가능하기 때문에
		 *  만약 캐스팅이 불가능할 경우 ClassCastException 이 발생한다.
		 *  이 경우 false를 return 하도록 try-catch문을 사용해준다.
		 */
		try {
				
			// LinkedHashSet 타입으로 캐스팅
			oSet = (LinkedHastSet<E>) o;
			// 사이즈가 다르다는 것은 명백히 다른 객체다.
			if(oSet.size() != size) {
				return false;
			}
				
			for(int i = 0; i < oSet.table.length; i++) {
				Node<E> oTable = oSet.table[i];
									
				while(oTable != null) {
					/*
					 * 서로 Capacity가 다를 수 있기 때문에 index에 연결 된 원소를을
					 * 비교하는 것이 아닌 contains로 원소의 존재 여부를 확인해야한다. 
					 */
					if(!contains(oTable)) {
						return false;
					}
					oTable = oTable.next;
				}
			}
				
		} catch(ClassCastException e) {
			return false;
		}
		// 위 검사가 모두 완료되면 같은 객체임이 증명됨
		return true;
 
	}

	public Object[] toArray() {
		 
		if (table == null || head == null) {
			return null;
		}
		Object[] ret = new Object[size];
		int index = 0;
 
		// 들어온 순서대로 head부터 tail까지 순회한다.
		for(Node<E> x = head; x != null; x = x.nextLink) {
			ret[index] = x.key;
			index++;
		}
 
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
 
		Object[] copy = toArray();
		if (a.length < size) {
			return (T[]) Arrays.copyOf(copy, size, a.getClass());
		}
		System.arraycopy(copy, 0, a, 0, size);
 
		return a;
	}


}
