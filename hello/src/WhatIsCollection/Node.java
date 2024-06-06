package WhatIsCollection;

/**
 * 데이터를 담을 Node 클래스
 */
class Node<E> {

	final int hash;
	final E key;
	
	Node<E> next;	
	
	Node<E> nextLink;	// for linked list
	Node<E> prevLink;
	
	public Node(int hash, E key, Node<E> next) {
		this.hash = hash;
		this.key = key;
		this.next = next;
		
		this.nextLink = null;
		this.prevLink = null;
	}
	
	
}
