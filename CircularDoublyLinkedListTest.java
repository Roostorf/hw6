package edu.ics211.h06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import edu.ics211.h02.Cheese;
import edu.ics211.h02.CheeseType;
import edu.ics211.h02.ManoaCheeseFromager;
import java.util.Iterator;
import java.util.ListIterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Represents a CircularDoublyLinkedListTest.
 *
 * @author Cam Moore
 *
 */
public class CircularDoublyLinkedListTest {
  private CircularDoublyLinkedList<Cheese> list;
  private ManoaCheeseFromager fromager;
  private Cheese[] cheeses;
  private static final int NUM_CHEESES = 15;

  /**
   * Initialize the member variables.
   *
   * @throws java.lang.Exception if there is a problem.
   */
  @Before
  public void setUp() throws Exception {
    // initialize the member variables
    this.fromager = ManoaCheeseFromager.getInstance();
    this.cheeses = new Cheese[NUM_CHEESES];
    for (int i = 0; i < NUM_CHEESES; i++) {
      cheeses[i] = fromager.makeCheese("Cheese" + i, CheeseType.getRandomCheeseType());
    }
    this.list = new CircularDoublyLinkedList<Cheese>(cheeses);
  }


  /**
   * Test method for {@link edu.ics211.h06.CircularDoublyLinkedList#CircularDoublyLinkedList(E[])}.
   */
  @Test
  public void testCircularDoublyLinkedList() {
    // check that list is not null
    assertNotNull(list);
    // check its size
    assertEquals(NUM_CHEESES, list.size());
  }


  /**
   * Test method for {@link edu.ics211.h06.CircularDoublyLinkedList#get(int)}.
   */
  @Test
  public void testGet() {
    // check bad indexes
    try {
      list.get(-1);
      fail("Can't get at -1");
    } catch (IndexOutOfBoundsException ioob) {
      // this is expected
    }
    try {
      list.get(list.size());
      fail("Can't get at size");
    } catch (IndexOutOfBoundsException ioob) {
      // this is expected
    }
    // check at index 0
    Cheese c = list.get(0);
    assertNotNull(c);
    // check at index size - 1
    c = list.get(list.size() - 1);
    assertNotNull(c);
  }


  /**
   * Test method for {@link edu.ics211.h06.CircularDoublyLinkedList#set(int, java.lang.Object)}.
   */
  @Test
  public void testSet() {
    // create new pizza to set
    Cheese c = fromager.makeCheese("New Cheese", CheeseType.getRandomCheeseType());
    // try bad indexes
    try {
      list.set(-1, c);
      fail("Can't set at -1");
    } catch (IndexOutOfBoundsException ioob) {
      // this is expected
    }
    try {
      list.set(list.size(), c);
      fail("Can't set at size");
    } catch (IndexOutOfBoundsException ioob) {
      // this is expected
    }
    // try good indexes
    Cheese old = list.set(0, c);
    assertNotEquals(c, old);
    assertEquals(c, list.get(0));
    old = list.set(list.size() - 1, c);
    assertNotEquals(c, old);
    assertEquals(c, list.get(list.size() - 1));
  }


  /**
   * Test method for {@link edu.ics211.h06.CircularDoublyLinkedList#indexOf(java.lang.Object)}.
   */
  @Test
  public void testIndexOf() {
    // check for pizza not in list
    Cheese notInList = fromager.makeCheese("New Cheese", CheeseType.getRandomCheeseType());
    assertEquals(-1, list.indexOf(notInList));
    // check for pizza in list
    assertEquals(1, list.indexOf(cheeses[1]));
    assertEquals(6, list.indexOf(cheeses[6]));
    assertEquals(13, list.indexOf(cheeses[13]));
  }


  /**
   * Test method for {@link edu.ics211.h06.CircularDoublyLinkedList#size()}.
   */
  @Test
  public void testSize() {
    assertEquals(cheeses.length, list.size());
  }


  /**
   * Test method for {@link edu.ics211.h06.CircularDoublyLinkedList#add(java.lang.Object)}.
   */
  @Test
  public void testAddE() {
    // create a pizza to add
    Cheese c = fromager.makeCheese("New Cheese", CheeseType.getRandomCheeseType());
    // add
    list.add(c);
    // check the size
    assertEquals(cheeses.length + 1, list.size());
    // check that it is added to the end
    assertEquals(c, list.get(list.size() - 1));
    // clean up
    list.remove(list.size() - 1);
  }


  /**
   * Test method for {@link edu.ics211.h06.CircularDoublyLinkedList#add(int, java.lang.Object)}.
   */
  @Test
  public void testAddIntE() {
    // create a pizza to add
    Cheese c = fromager.makeCheese("New Cheese", CheeseType.getRandomCheeseType());
    // try bad indexes
    try {
      list.add(-1, c);
      fail("Can't add at -1");
    } catch (IndexOutOfBoundsException ioob) {
      // this is expected
    }
    try {
      list.add(list.size() + 1, c);
      fail("Can't add at past size");
    } catch (IndexOutOfBoundsException ioob) {
      // this is expected
    }
    // try good indexes
    // add at 0
    list.add(0, c);
    // check size
    assertEquals(cheeses.length + 1, list.size());
    // check pizza at 0
    assertEquals(c, list.get(0));
    // clean up
    list.remove(0);
    // add at size
    list.add(list.size(), c);
    // check size
    assertEquals(cheeses.length + 1, list.size());
    // check pizza at size - 1
    assertEquals(c, list.get(list.size() - 1));
    // clean up
    list.remove(list.size() - 1);
  }


  /**
   * Test method for {@link edu.ics211.h06.CircularDoublyLinkedList#remove(int)}.
   */
  @Test
  public void testRemove() {
    // try remove bad indexes
    try {
      list.remove(-1);
      fail("Can't remove at -1");
    } catch (IndexOutOfBoundsException ioob) {
      // this is expected
    }
    try {
      list.remove(list.size());
      fail("Can't remove at size");
    } catch (IndexOutOfBoundsException ioob) {
      // this is expected
    }
    // try good indexes
    // remove at 0
    list.remove(0);
    // check size
    assertEquals(cheeses.length - 1, list.size());
    // remove at size - 1
    list.remove(list.size() - 1);
    // check size
    assertEquals(cheeses.length - 2, list.size());
    // middle
    Cheese c = list.remove(8);
    // check size
    assertEquals(cheeses.length - 3, list.size());
    // check got right pizza
    assertEquals(cheeses[9], c);
  }


  /**
   * Test method for {@link edu.ics211.h06.CircularDoublyLinkedList#iterator()}.
   */
  @Test
  public void testIterator() {
    Iterator<Cheese> iter = list.iterator();
    assertNotNull("Didn't create an iterator", iter);
    assertTrue("Got wrong type of iterator", iter instanceof ListIterator);
    ListIterator<Cheese> listIter = (ListIterator<Cheese>) iter;
    assertTrue("wrong hasNext", listIter.hasNext());
    assertTrue("wrong hasPrevious", listIter.hasPrevious());
    assertEquals("Wrong nextIndex", 0, listIter.nextIndex());
    assertEquals("Wrong previousIndex", list.size() - 1, listIter.previousIndex());
    Cheese first = listIter.next();
    assertEquals("Wrong cheese", list.get(0), first);
    listIter.next();
    Cheese second = listIter.previous();
    assertEquals("Wrong cheese", list.get(1), second);
  }

}
