package edu.ics211.h06;

//import edu.ics211.h02.Brie;
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
  /*
  public interface ListIterator<E> {
    void add(E e); // Inserts the specified element to the list. (Optional for this assignment)
    boolean hasNext(); // Returns true if this list iterator has more elements while traversing in the forward direction
    boolean hasPrevious(); // Returns true if this list iterator has more elements while traversing reverse direction
    E next(); // Returns the next Element.
    int nextIndex(); // Returns the index of the next element.
    E previous(); // Returns the previous Element
    int previousIndex(); // Returns the index of the previous element.
    void remove(); // Removes from the list the last element that was returned.
    void set(E e); // Replaces the last element returned. (Optional for this assignment)
  }*/
  
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
        throw new NoSuchElementException();  //not sure the right exception error
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
        throw new NoSuchElementException(); //not sure the right exception error
      }
      lastReturnedNode = nextNode;
      nextNode = nextNode.prev;
      if (nextIndex > 0) {
        nextIndex --;
      } else if (nextIndex == 0) {
        nextIndex = size - 1;
      }
      
      return lastReturnedNode.item;
    }

    @Override
    public int previousIndex() {
      return nextIndex - 2;
    }

    @Override
    public void remove() {
      if (lastReturnedNode != null) {
        if (size == 1) {
          head = null;
          tail = null;
        } else {
          if (lastReturnedNode == head) {
            head = head.next;
            makeListCirc();
          } else if (lastReturnedNode == tail.prev) {
            makeListCirc();
          } else {
            //update lastReturned.next.prev and lastReturned.prev.next
          }
          
        }
        size--;
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
    // checkIndex
    // traverse to index *******************************************************************
    // return item at index
    
    return null;
  }

  @Override
  public E set(int index, E element) {
    
    // check index
    // traverse to index
    // remember item
    // set item
    // return item
    
    return null;
  }

  @Override
  public int indexOf(Object obj) {
    // loop from size 0 to size -1
    // if item at index is equal to obj return index
    // update temp
    // return -1
    return 0;
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
    // Check index size ok
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException(); //Out of Bounds
    }
    if (head == null || size == 0) {
      DLinkedNode node = new DLinkedNode(element, head, tail);
      head = node;
      tail = head; // point list to newNode head = newNode, tail = newNode ************************************
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
    size++;
  }

  /**
   * Makes list circular.
   */
  private void makeListCirc() {
    tail.next = head;
    head.prev = tail;
    
  }

  @Override
  public E remove(int index) {
    // check index
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException(); //Out of Bounds
    }
    // traverse to index ***********************************************************************
    // if index is 0
    if (index == 0) {
      //    update head
      
      makeListCirc(); //    MakeListCirc
    }

    // else if index is size - 1
    //    update tail
    //    makeListCirc
    // else
    //    make list not point to node
    // decrement size
    // return temp.item
    
    return null;
  }

}
