package edu.ics211.h06;

import edu.ics211.h02.Cheese;
import java.util.List;

/**
 * Represents a ICheeseTasting.
 *
 * @author Cam Moore
 *
 */
public interface ICheeseTasting {
  /**
   * Returns a List of the cheeses in the order they were tasted. This method
   * doesn't destroy the cheese plate.
   * @param start the position of where to start the tasting. This is a ones based index.
   * @param step the predetermined number for counting off.
   * @param isClockwise if true the counting occurs in a clockwise/increasing order.
   *        If false the counting occurs in a counter clockwise/decreasing order.
   * @return A List of Cheese in the order they were tasted.
   */
  List<Cheese> tasteCheeses(int start, int step, boolean isClockwise);
}
