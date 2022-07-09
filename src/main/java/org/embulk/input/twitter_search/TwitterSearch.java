package org.embulk.input.twitter_search;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TwitterSearch implements Iterator<Status>
{
    private final Twitter twitter;
    private QueryResult queryResult;
    private LinkedList<Status> twitterStatuses;
    private static final Logger logger = LoggerFactory.getLogger(TwitterSearch.class);

    public TwitterSearch(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        System.out.println(cb);
        cb.setDebugEnabled(true)
                .setJSONStoreEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        Configuration configuration = cb.build();
        TwitterFactory twitterFactory = new TwitterFactory(configuration);
        twitter = twitterFactory.getInstance();
    }

    public void search(String queryString)
    {
        try {
            Query query = new Query(queryString);
            queryResult = twitter.search(query);
            System.out.println(queryResult);
            List<Status> statuses = queryResult.getTweets();
            twitterStatuses = new LinkedList<>(statuses);
        } catch (TwitterException te) {
            waitTillReset(te);
            search(queryString);
        }
    }

    public boolean hasNext()
    {
        if (twitterStatuses.isEmpty() && queryResult.hasNext()) {
            try {
                queryResult = twitter.search(queryResult.nextQuery());
                twitterStatuses.addAll(queryResult.getTweets());
            } catch (TwitterException te) {
                waitTillReset(te);
                return hasNext();
            }
        }
        return !twitterStatuses.isEmpty();
    }

    public Status next()
    {
        return twitterStatuses.removeFirst();
    }

    private void waitTillReset(TwitterException te)
    {
        if (te.getErrorMessage().equals("Rate limit exceeded")) {
            try {
                //TODO: exp backeff and full-jitter
                int seconds = te.getRateLimitStatus().getSecondsUntilReset();
                logger.warn(String.format("%ss waiting...", seconds));
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        } else {
            te.printStackTrace();
            logger.warn(String.format("Messages: (%s)", te.getMessage()));
            System.exit(-1);
        }
    }
}
