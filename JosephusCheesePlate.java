package edu.ics211.h06;

import edu.ics211.h02.Cheese;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents a JosephusCheesePlate.
 *
 * @author Constantine Peros With help from Brandon Yee
 *
 */
public class JosephusCheesePlate implements ICheeseTasting {
  private Cheese[] cheeses;

  /**
   * Creates a new JosephusCheesePlate.
   * 
   * @param cheeses the cheeses to taste.
   *
   */
  public JosephusCheesePlate(Cheese[] cheeses) {
    // initialize member variables
    this.cheeses = cheeses;
  }


  /**
   * Returns a List of the cheeses in the order they were tasted. This method doesn't destroy the cheese plate.
   * 
   * @param start the position of where to start the tasting. This is a ones based index.
   * @param step the predetermined number for counting off.
   * @param isClockwise if true the counting occurs in a clockwise/increasing order, and vice versa
   * @return A List of Cheese in the order they were tasted.
   */
  @Override
  public List<Cheese> tasteCheeses(int start, int step, boolean isClockwise) {
    ArrayList<Cheese> finalList = new ArrayList<>(); // create a list to return new ArrayList<Cheese>
    // create a CircularDoublyLinkedList<Cheese> from cheeses
    CircularDoublyLinkedList<Cheese> cheeseList = new CircularDoublyLinkedList<>(cheeses);
    // cast circular list's iterator to a ListIterator
    ListIterator<Cheese> cheeseIterator = (ListIterator<Cheese>) cheeseList.iterator();

    // if clockwise, we need to be before start; if counterclockwise, we need to be after start
    final int startOffset = (isClockwise) ? -1 : 0; // set starting point offset based on above criteria
    // move iterator to start position
    for (int i = 0; i < start + startOffset; i++) {
      cheeseIterator.next();
    }

    // while circular list of cheeses still has remaining cheeses
    while (cheeseList.size() > 0) {
      Cheese temp = null; // remember the cheese that will be returned by the iterator
      for (int i = 0; i < step; i++) {
        // get the next (or previous) cheese n steps away
        temp = (isClockwise) ? cheeseIterator.next() : cheeseIterator.previous();
      }
      finalList.add(temp); // add cheese to the final list
      cheeseIterator.remove(); // remove cheese from circular list
    }

    return finalList; // return final list of cheeses in order
  }

}