package org.embulk.input.twitter_search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.Configuration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TwitterSearch.class, ConfigurationBuilder.class, Query.class})
public class TestTwitterSearch
{
    private Twitter twitter;
    private TwitterSearch twitterSearch;

    @Before
    public void setup() throws Exception {
        ConfigurationBuilder configurationBuilder = Mockito.mock(ConfigurationBuilder.class);
        PowerMockito.whenNew(ConfigurationBuilder.class).withNoArguments().thenReturn(configurationBuilder);
        Mockito.when(configurationBuilder.setDebugEnabled(true)).thenReturn(configurationBuilder);
        Mockito.when(configurationBuilder.setJSONStoreEnabled(true)).thenReturn(configurationBuilder);
        Mockito.when(configurationBuilder.setOAuthConsumerKey("consumer-***-key")).thenReturn(configurationBuilder);
        Mockito.when(configurationBuilder.setOAuthConsumerSecret("consumer-***-secret")).thenReturn(configurationBuilder);
        Mockito.when(configurationBuilder.setOAuthAccessToken("access-***-token")).thenReturn(configurationBuilder);
        Mockito.when(configurationBuilder.setOAuthAccessTokenSecret("access-***-secret")).thenReturn(configurationBuilder);
        Configuration configuration = Mockito.mock(Configuration.class);
        Mockito.when(configurationBuilder.build()).thenReturn(configuration);
        TwitterFactory twitterFactory = Mockito.mock(TwitterFactory.class);
        PowerMockito.whenNew(TwitterFactory.class).withArguments(configuration).thenReturn(twitterFactory);
        twitter = Mockito.mock(Twitter.class);
        Mockito.when(twitterFactory.getInstance()).thenReturn(twitter);
        twitterSearch = new TwitterSearch(
                "consumer-***-key",
                "consumer-***-secret",
                "access-***-token",
                "access-***-secret"
        );
        Mockito.verify(configurationBuilder).setDebugEnabled(true);
    }

    @Test
    public void testSearch() throws Exception {
        Query query = Mockito.mock(Query.class);
        QueryResult queryResult = Mockito.mock(QueryResult.class);
        PowerMockito.whenNew(Query.class).withArguments("from:@nishiogi_now").thenReturn(query);
        Mockito.when(twitter.search(query)).thenReturn(queryResult);
        List<Status> statuses = new ArrayList<>();
        Mockito.when(queryResult.getTweets()).thenReturn(statuses);
        twitterSearch.search("from:@nishiogi_now");
    }

    @Test
    public void testHasNext()
    {
        Status status = Mockito.mock(Status.class);
        LinkedList<Status> twitterStatuses = new LinkedList<>();
        twitterStatuses.add(status);
        Whitebox.setInternalState(twitterSearch, "twitterStatuses", twitterStatuses);
        QueryResult queryResult = Mockito.mock(QueryResult.class);
        Mockito.when(queryResult.hasNext()).thenReturn(true);
        Whitebox.setInternalState(twitterSearch, "queryResult", queryResult);
        boolean hasNext = twitterSearch.hasNext();
        Assert.assertTrue(hasNext);
    }

    @Test
    public void testNext()
    {
        Status status = Mockito.mock(Status.class);
        LinkedList<Status> twitterStatuses = new LinkedList<>();
        twitterStatuses.add(status);
        Whitebox.setInternalState(twitterSearch, "twitterStatuses", twitterStatuses);
        Status nextStatus = twitterSearch.next();
        Assert.assertEquals(status, nextStatus);
    }
}
