package org.embulk.input.twitter_search;

import org.junit.Test;
//import static org.junit.Assert.assertEquals;

public class TestTwitterSearch
{
  @Test
  public void testSearch()
  {
    TwitterSearch twitter = new TwitterSearch();
    //twitter.search("from:@nishiogi_now exclude:retweets");
    twitter.search("");
    //assertEquals(5, calc.plus(3));
  }
}
