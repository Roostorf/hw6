package edu.ics211.h06;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import edu.ics211.h02.Cheese;
import edu.ics211.h02.CheeseType;
import edu.ics211.h02.ManoaCheeseFromager;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


/**
 * Represents a JosephusCheesePlateTest.
 *
 * @author Cam Moore
 *
 */
public class JosephusCheesePlateTest {
  private ManoaCheeseFromager fromager;
  private Cheese[] cheeses;

  /**
   * Initialize member variables.
   * @throws java.lang.Exception if there is a problem.
   */
  @Before
  public void setUp() throws Exception {
    fromager = ManoaCheeseFromager.getInstance();
    cheeses = new Cheese[7];
    for (int i = 0; i < cheeses.length; i++) {
      cheeses[i] = fromager.makeCheese("Cheese" + i,  CheeseType.getRandomCheeseType());
    }
  }


  /**
   * Test method for {@link edu.ics211.h06.JosephusCheesePlate#JosephusCheesePlate(edu.ics211.h02.Cheese[])}.
   */
  @Test
  public void testJosephusCheesePlate() {
    JosephusCheesePlate plate = new JosephusCheesePlate(cheeses);
    assertNotNull(plate);
  }


  /**
   * Test method for {@link edu.ics211.h06.JosephusCheesePlate#tasteCheeses(int, int, boolean)}.
   */
  @Test
  public void testTasteCheeses() {
    JosephusCheesePlate plate = new JosephusCheesePlate(cheeses);
    assertNotNull(plate);
    List<Cheese> tasting = plate.tasteCheeses(1, 3, true);
    assertEquals("Got wrong size", cheeses.length, tasting.size());
    assertEquals(cheeses[2], tasting.get(0));
    assertEquals(cheeses[5], tasting.get(1));
    assertEquals(cheeses[1], tasting.get(2));
    assertEquals(cheeses[6], tasting.get(3));
    assertEquals(cheeses[4], tasting.get(4));
    assertEquals(cheeses[0], tasting.get(5));
    assertEquals(cheeses[3], tasting.get(6));
    tasting = plate.tasteCheeses(4, 2, false);
    assertTrue(tasting.size() == cheeses.length);
    assertEquals(cheeses[2], tasting.get(0));
    assertEquals(cheeses[0], tasting.get(1));
    assertEquals(cheeses[5], tasting.get(2));
    assertEquals(cheeses[3], tasting.get(3));
    assertEquals(cheeses[6], tasting.get(4));
    assertEquals(cheeses[1], tasting.get(5));
    assertEquals(cheeses[4], tasting.get(6));
    tasting = plate.tasteCheeses(1, 1, false);
    assertTrue(tasting.size() == cheeses.length);
    assertEquals(cheeses[0], tasting.get(0));
    assertEquals(cheeses[6], tasting.get(1));
    assertEquals(cheeses[5], tasting.get(2));
    assertEquals(cheeses[4], tasting.get(3));
    assertEquals(cheeses[3], tasting.get(4));
    assertEquals(cheeses[2], tasting.get(5));
  }

}
