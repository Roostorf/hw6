package edu.ics211.h06;

import edu.ics211.h04.IList211;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Represents a CircularDoublyLinkedList.
 *
 * @author Constantine Peros
 * @param <E> the generic type
 * 
 */
public class CircularDoublyLinkedList<E> implements IList211<E>, Iterable<E> {
  
  private DLinkedNode head;
  private DLinkedNode tail;
  private int size;
  
  private class DLinkedNode {
    E item;
    DLinkedNode next;
    DLinkedNode prev;
    
    
    public DLinkedNode(E item, DLinkedNode next, DLinkedNode prev) {
      this.item = item;
      this.next = next;
      this.prev = prev;
    }
  }
  
  private class MyListIterator implements ListIterator<E> {
    private DLinkedNode nextNode;
    private int nextIndex;
    private DLinkedNode lastReturnedNode;
    
    /**
    * Creates a New CircularDoublyLinkedList.MyListIterator.
    *
    */
    public MyListIterator() {
      // Initialize member variables
      nextNode = head;
      nextIndex = 0;
      lastReturnedNode = null;      
    }

    @Override
    public boolean hasNext() {
      return size != 0;
    }

    @Override
    public boolean hasPrevious() {
      return size != 0; // same as hasNext
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();  //exception error
      }
      lastReturnedNode = nextNode;
      nextNode = nextNode.next; // update nextNode ??
      nextIndex = (nextIndex + 1) % size;
      return lastReturnedNode.item;
    }
    

    @Override
    public int nextIndex() {
      return nextIndex;
    }

    @Override
    public E previous() {
      if (!hasPrevious()) {
        throw new NoSuchElementException(); //exception error
      }
      lastReturnedNode = nextNode.prev;
      nextNode = nextNode.prev;
      
      nextIndex = (nextIndex + size - 1) % size; // circular
      
      return lastReturnedNode.item;
    }

    @Override
    public int previousIndex() {
      return (nextIndex + size - 1) % size;
    }

    @Override
    public void remove() {
      if (lastReturnedNode != null) {
        if (size == 1) {
          // special case, reset state of list back to original
          head = null;
          tail = null;
          size = 0;
        } else if (lastReturnedNode == head) {
          head = head.next; // update head
          makeListCirc(); // make the list circular
        } else if (lastReturnedNode == tail) {
          tail = tail.prev; // update tail
          makeListCirc(); // make the list circular
        } else {
          // make the list not point to lastReturnedNode
          lastReturnedNode.next.prev = lastReturnedNode.prev;
          lastReturnedNode.prev.next = lastReturnedNode.next;
        }
        size--;
        lastReturnedNode = null;
      }
    }


    @Override
    public void set(E e) {
      throw new UnsupportedOperationException();      
    }
    
    @Override
    public void add(E e) {
      throw new UnsupportedOperationException();      
    }
    
  }
  
  /**
   * Creates a CircularDoublyLinkedList.
   */
  public CircularDoublyLinkedList() {
    // initialize member variables
    size = 0;
    head = null;
    tail = null;
  }
  
  /**
   * Creates a CircularDoublyLinkedList.
   * @param items is generic data
   */
  public CircularDoublyLinkedList(E[] items) {
    // initialize member variables
    this();
    for (E item: items) {
      add(item);
    }
  }
  
  

  @Override
  public Iterator<E> iterator() {
    return new MyListIterator();
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(); //Out of Bounds
    } // check index
    DLinkedNode temp = traverse(index);
    return temp.item; // return item at index
    
  }

  @Override
  public E set(int index, E element) {   
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(); //Out of Bounds
    } // check index
    DLinkedNode node = traverse(index);
    E prevItem = node.item; // remember the previous item
    node.item = element;
    return prevItem; // return the remembered previous item
  }

  @Override
  public int indexOf(Object obj) {
    DLinkedNode temp = head;    // temp = head
    for (int i = 0; i < size - 1; i++) {     // loop from size 0 to size -1
      if (temp.item == obj) {    // if item at index is equal to obj return index
        int index = i;
        return i;   // return index
      }
      temp = temp.next;     // update temp
    }
    return -1;  //not found

  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean add(E e) {
    add(size, e);
    return true;
  }

  @Override
  public void add(int index, E element) {
    // Check index size
    checkIndex(index);
    if (index == 0) { // New add
      // update head and keep circularity
      DLinkedNode newNode = new DLinkedNode(element, head, tail);
      if (head != null) {
        // other elements already exist in the list
        head.prev = newNode;
      } else {
        // first element to add in the list, set tail
        tail = newNode;
      }
      // point list to new node
      head = newNode;
      makeListCirc();
    } else if (index == size) {
      // Update tail and keep circularity
      DLinkedNode newNode = new DLinkedNode(element, head, tail);
      // point list to new node
      tail.next = newNode;
      tail = newNode;
      makeListCirc();
    } else {
      // adding a node somewhere in the middle of the list
      DLinkedNode prevNode = traverse(index - 1);
      DLinkedNode newNode = new DLinkedNode(element, prevNode.next, prevNode);
      // point list to new node
      prevNode.next = newNode;
      newNode.next.prev = newNode;
    }
    size++; // increment size
    
    /*
    if (head == null || size == 0) {
      DLinkedNode node = new DLinkedNode(element, head, tail);
      head = node;
      tail = head; // point list to newNode head = newNode, tail = newNode
      makeListCirc(); // make list circular 
    } else if (index == 0) { // else if index is 0
      DLinkedNode node = new DLinkedNode(element, head, tail);
      head = node;// update head
      makeListCirc();// MakeListCircular
    } else if (index == size) {
      DLinkedNode node = new DLinkedNode(element, head, tail);
      tail.next = node;
      tail = node;
      makeListCirc();// MakeListCircular
      
    } else {
      DLinkedNode node = head;
      for (int i = 0; i < index - 1; i++) {
        node = node.next;
      }
      DLinkedNode add = new DLinkedNode(element, node.next, node);
      node.next = add;
      add.next.prev = add;
    }
    size++; */
  }

  /**
   * Makes list circular.
   */
  private void makeListCirc() {
    tail.next = head;
    head.prev = tail;
    
  }
  
  private DLinkedNode traverse(int index) {
    checkIndex(index); // check index
    DLinkedNode node = head; // start at the head
    for (int i = 0; i < index && node != null; i++) {
      // traverse to index
      node = node.next;
    }
    return node; // return the node at index
  }

  /**
  * Creates a New CircularDoublyLinkedList.MyListIterator.
  * @param index is the index.
  */
  private void checkIndex(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException(); //Out of Bounds
    }
    
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(); //Out of Bounds
    } // check index
    DLinkedNode nodeToRemove = traverse(index);
    if (size == 1) { // special case if size == 1
      head = null;
      tail = null;
    }
    if (index == 0) {
      // update head
      head = nodeToRemove.next;
      makeListCirc();
    } else if (index == size - 1) {
      // update tail
      tail = nodeToRemove.prev;
      makeListCirc();
    } else {
      // removing an element somewhere in the middle of the list
      // have the list not point to nodeToRemove
      nodeToRemove.next.prev = nodeToRemove.prev;
      nodeToRemove.prev.next = nodeToRemove.next;
    }

    size--; // decrement size
    return nodeToRemove.item; // return stored item
  }
  

}
