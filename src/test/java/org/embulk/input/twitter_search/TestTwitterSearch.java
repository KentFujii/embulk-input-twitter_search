package org.embulk.input.twitter_search;

import org.junit.Test;
import twitter4j.Status;

public class TestTwitterSearch
{
    @Test
    public void testSearch()
    {
        String consumerKey = "***";
        String consumerSecret = "***";
        String accessToken = "***";
        String accessSecret = "***";
        TwitterSearch twitter = new TwitterSearch(consumerKey, consumerSecret, accessToken, accessSecret);
        twitter.search("from:@nishiogi_now");
        while (twitter.hasNext()) {
            Status status = twitter.next();
            System.out.println(status.getUser().getScreenName());
        }
    }
}
