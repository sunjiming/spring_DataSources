package com.one.three;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * �ղεģ�˫��ѭ������
 * 
 * */

public class DoubleLinkList3<Item> implements Iterable<Item>{
	
	private Node<Item> begin;
	private Node<Item> end;
	int n = 0;
	
	// �ڲ���
	private static class Node<Item>{
		public Item item;
		private Node<Item> prev;
		private Node<Item> next;
	}
	//��ʼ��
	public DoubleLinkList3(){
		begin = new Node<Item>(); 
		end = new Node<Item>(); 
		
		begin.prev = end;
		begin.next = null;
		end.prev =null;
		end.next = begin;
	}
	// ��β�����
	public void add(Item item){
		if(begin.next == null){// ˵����һ�����Ԫ��
			Node<Item> node =new Node<Item>();
			node.item = item;
			
			begin.next = node;
			node.prev = begin;
			end = node;
			begin.prev = end;
			end.next = begin;
			n++;
		}else{
			Node<Item> node =new Node<Item>();
			node.item = item;
			
			end.next = node;
			node.prev = end;
			end = node;
			begin.prev = end;
			end.next = begin;
			n++;
		}
	}
	// ��β��ɾ��
	public Item delete(){
		if(n==0){
			System.out.println("����Ϊ��");
			return null;
		}
		Item item = end.item;// ����ɾ���Ķ���
		
		Node<Item> node = end.prev;	//��Ϊ�µ�β��	
		//�ɵ�β�ͶϿ�
		end.prev = null;
		end.next = null;	
		//�µ���β����
		node.next = begin;
		begin.prev = node;
		end = node;
		n--;
		return item;
	}
	//�ж������Ƿ�Ϸ�
	public void valiIndex(int index){
		if(index<=0 || index>n){
			throw new IndexOutOfBoundsException();
		}
	}
	//��ȡָ��λ�õĽڵ����
	public Node<Item> getNode(int index){
		valiIndex(index);
		Node<Item> cur;
		// ��bengin��ʼ����ѯ������
		if(index <= n/2){
			cur = begin.next;
			for(int i = 0 ;i < index-1;i++)
				cur = cur.next;
			return cur;
		}
		// ��end��ʼ����ѯ������
		cur = end;
		for(int i = 0 ;i < n-index;i++){
			cur = cur.prev;
		}
		return cur;
	}
	//��ȡָ��������Ӧ��item ֵ
	public Item getItem(int index){
		Node<Item> node = getNode(index);
		return node.item;
	}
	// �ж��Ƿ�Ϊ��
	public boolean isEmpty(){
		return n ==0;
	}
	// �����б���
	public int size(){
		return n;
	}
	// ��ָ��λ�����Item����������bengin�����Ԫ�ء�
	public void addIndex(int index,Item item){
		Node<Item> newNode = new Node<Item>();//�½ڵ�
		newNode.item = item;
		Node<Item> oldNode = getNode(index);//�ɽڵ�
		
		newNode.prev = oldNode.prev;// �½ڵ㣬ǰ������
		oldNode.prev.next = newNode;// �ɽڵ㣬��̱�Ϊ�½ڵ�
		oldNode.prev = newNode;// �ɽڵ㣬ǰ��Ϊ��Ϊ�½ڵ�
		newNode.next = oldNode;// �½ڵ㣬��̱�Ϊ�ɽڵ�
		n++;
	}
	// ɾ��ָ��λ�õ�Ԫ��
	public Item deleteIndex(int index){
		Node<Item> node = getNode(index);
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		node.next = null;//�Ͽ����
		node.prev = null;//�Ͽ�ǰ��
		
		n--;
		return node.item;
	}
	// ��ȡ��һ���ڵ��Item
	public Item getBegin(){
		return begin.next.item;
	}
	// ��ȡ���һ���ڵ��Item
	public Item getEnd(){
		return end.item;
	}
	@Override
	public Iterator<Item> iterator() {
		
		return new DoubleLinkListIterator(begin);
	}
	class DoubleLinkListIterator implements Iterator<Item>{
		Node<Item> current;
		int x = n; 
		public DoubleLinkListIterator(Node<Item> current) {
				this.current = current;
		}
		@Override
		public boolean hasNext() {
			return x--!=0;
		}
		@Override
		public Item next() {
			/*if (!hasNext()) 
				throw new NoSuchElementException();*/
			current = current.next;
			Item item = current.item;		
			return item;
		}
	}
}
 