package org.embulk.input.twitter_search;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCalc
{
  @Test
  public void testPlus()
  {
    System.out.println("testPlus");
    Calc calc = new Calc(2);
    assertEquals(5, calc.plus(3));
  }

  @Test
  public void testMinus()
  {
    System.out.println("testMinus");
    Calc calc = new Calc(5);
    assertEquals(3, calc.minus(2));
  }
}
