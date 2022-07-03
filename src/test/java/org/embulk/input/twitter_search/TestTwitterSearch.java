package org.embulk.input.twitter_search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.Configuration;
import java.util.Arrays;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TwitterSearch.class, ConfigurationBuilder.class})
public class TestTwitterSearch
{
    private TwitterSearch twitterSearch;

    @Before
    public void setup() throws Exception {
        ConfigurationBuilder mockConfigurationBuilder = Mockito.mock(ConfigurationBuilder.class);
        PowerMockito.whenNew(ConfigurationBuilder.class).withNoArguments().thenReturn(mockConfigurationBuilder);
        Mockito.when(mockConfigurationBuilder.setDebugEnabled(true)).thenReturn(mockConfigurationBuilder);
        Mockito.when(mockConfigurationBuilder.setJSONStoreEnabled(true)).thenReturn(mockConfigurationBuilder);
        Mockito.when(mockConfigurationBuilder.setOAuthConsumerKey("consumer-***-key")).thenReturn(mockConfigurationBuilder);
        Mockito.when(mockConfigurationBuilder.setOAuthConsumerSecret("consumer-***-secret")).thenReturn(mockConfigurationBuilder);
        Mockito.when(mockConfigurationBuilder.setOAuthAccessToken("access-***-token")).thenReturn(mockConfigurationBuilder);
        Mockito.when(mockConfigurationBuilder.setOAuthAccessTokenSecret("access-***-secret")).thenReturn(mockConfigurationBuilder);
        Configuration mockConfiguration = Mockito.mock(Configuration.class);
        Mockito.when(mockConfigurationBuilder.build()).thenReturn(mockConfiguration);
        TwitterFactory mockTwitterFactory = Mockito.mock(TwitterFactory.class);
        PowerMockito.whenNew(TwitterFactory.class).withArguments(mockConfiguration).thenReturn(mockTwitterFactory);
        Twitter mockTwitter = Mockito.mock(Twitter.class);
        Mockito.doReturn(mockTwitter).when(mockTwitterFactory).getInstance();
        twitterSearch = new TwitterSearch(
                "consumer-***-key",
                "consumer-***-secret",
                "access-***-token",
                "access-***-secret"
        );
        Mockito.verify(mockConfigurationBuilder).setDebugEnabled(true);
    }

    @Test
    public void testSearch() throws TwitterException {
        //QueryResult mockQueryResult = PowerMockito.mock(QueryResult.class);
        //QueryResult mockQueryResult = Mockito.mock(QueryResult.class);
        //Mockito.doReturn(mockQueryResult).when(mockTwitter.search(Mockito.any(twitter4j.Query.class)));
        //Mockito.when(mockTwitter.search(new twitter4j.Query("test"))).thenReturn(mockQueryResult);
        twitterSearch.search("test");
        //Mockito.when(twitter.search(new twitter4j.Query("aaaaa"))).thenReturn(Mockito.mock(QueryResult.class));
    }

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
