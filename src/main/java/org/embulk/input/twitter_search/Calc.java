package org.embulk.input.twitter_search;

public class Calc {

  private int base;

  public Calc(int base) {
    this.base = base;
  }

  public int plus(int num) {
    return base + num;
  }

  public int minus(int num) {
    return base - num;
  }
}