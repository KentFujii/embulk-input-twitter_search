package org.embulk.input.twitter_search;

import org.junit.Test;
import twitter4j.Status;

public class TestTwitterSearch
{
    @Test
    public void testTwitterSearch()
    {
        TwitterSearch twitterSearch = new TwitterSearch(
                "consumer-***-key",
                "consumer-***-secret",
                "access-***-token",
                "access-***-secret"
        );
        twitterSearch.search("#Embulk");
        while (twitterSearch.hasNext()) {
            Status status = twitterSearch.next();
            System.out.println(status.getText());
        }
    }

    @Test
    public void testSearch()
    {}

    @Test
    public void testHasNext()
    {}

    @Test
    public void testNext()
    {}

    //@Test
    //public void testSearch()
    //{
    //    String consumerKey = "consumer-***-key";
    //    String consumerSecret = "consumer-***-secret";
    //    String accessToken = "access-***-token";
    //    String accessSecret = "access-***-secret";
    //    TwitterSearch twitter = new TwitterSearch(consumerKey, consumerSecret, accessToken, accessSecret);
    //    twitter.search("from:@nishiogi_now");
    //    while (twitter.hasNext()) {
    //        Status status = twitter.next();
    //        System.out.println(status.getUser().getScreenName());
    //    }
    //}
}
