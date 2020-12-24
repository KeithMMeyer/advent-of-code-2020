package solutions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import solutions.Day23.DoubleList.Node;

public class Day23 {

	static HashSet<String> set = new HashSet<>();
	//static List<Integer> cups = new LinkedList<>();
	static List<Integer> held = new ArrayList<>();
	static DoubleList cups = new DoubleList();

	public static void main(String[] args) throws IOException {
		String start = "167248359";
		int min = 1;
		int max = 1000000;
		for (char c : start.toCharArray()) {
			String temp = "" + c;
			cups.add(Integer.valueOf(temp));
		}
		int rest = cups.size() + 1;
		for (int i = rest; i <= 1000000; i++) {
			cups.add(i);
		}

		Node current = cups.head;
		for (int i = 0; i < 10000000; i++) {
			//System.out.println(cups);
			Node removed = current.next;
			Node past = removed.next.next.next;
			current.next = past;
			past.previous = current;
			if (removed == cups.head || removed.next == cups.head || removed.next.next == cups.head)
				cups.head = past;
			if (removed == cups.tail || removed.next == cups.tail || removed.next.next == cups.tail)
				cups.tail = current;

			int tar = current.value - 1;
			while (tar < min || removed.value == tar || removed.next.value == tar || removed.next.next.value == tar) {
				tar--;
				if (tar < min)
					tar = max;
			}
			//System.out.println(": " + removed.value + " " + removed.next.value + " " + removed.next.next.value + " : " + tar);
			Node target = cups.findNode(tar);
			if (target == cups.tail) {
				cups.tail = removed.next.next;
			}
			past = target.next;
			target.next = removed;
			removed.previous = target;
			removed.next.next.next = past;
			past.previous = removed;
			//System.out.println("(" + target.next.value + ")");
			//System.out.println(cups + "\n");

			current = current.next;
		}
		while (current.value != 1) {
			current = current.next;
		}
		System.out.println(new Long(1) * current.next.value * current.next.next.value);

	}

	static class DoubleList {
		//A node class for doubly linked list
		class Node {
			int value;
			Node previous;
			Node next;

			public Node(int item) {
				this.value = item;
			}
		}

		static HashMap<Integer, Node> map = new HashMap<>();

		//Initially, head and tail is set to null
		Node head, tail = null;

		private int size = 0;

		//add a node to the list  
		public void add(int item) {
			//Create a new node  
			Node newNode = new Node(item);

			//if list is empty, head and tail points to newNode  
			if (head == null) {
				head = tail = newNode;
				//head's previous will be null  
				head.previous = tail;
				//tail's next will be null  
				tail.next = head;
			} else {
				//add newNode to the end of list. tail->next set to newNode  
				tail.next = newNode;
				//newNode->previous set to tail  
				newNode.previous = tail;
				//newNode becomes new tail  
				tail = newNode;
				//tail's next point to null  
				tail.next = head;
				head.previous = tail;
			}
			map.put(item, newNode);
			size++;
		}

		//print all the nodes of doubly linked list  
		public String toString() {
			//Node current will point to head 
			String out = "";
			Node current = head;
			if (head == null) {
				System.out.println("Doubly linked list is empty");
				return out;
			}
			tail.next = null;
			while (current != null) {
				//Print each node and then go to next.  
				out += current.value + " ";
				current = current.next;
			}
			tail.next = head;
			return out;
		}

		public int size() {
			return size;
		}

		public Node getNode(int i) {
			Node current = head;
			tail.next = null;
			while (current != null && i > 0) {
				current = current.next;
			}
			tail.next = head;
			return current;
		}

		public Node findNode(int i) {
			return map.get(i);
		}
	}

}
