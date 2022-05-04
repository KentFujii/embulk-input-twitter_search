package org.embulk.input.twitter_search;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;
import java.util.List;

//https://twitter4j.org/en/code-examples.html#search
//https://twitter4j.org/ja/configuration.html
//https://twitter4j.org/oldjavadocs/4.0.0/index.html
public class TwitterSearch
{
    public void search(String query) {

        if (query.length() < 1) {
            System.out.println("java twitter4j.examples.search.SearchTweets [query]");
            System.exit(-1);
        }
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey("*")
            .setOAuthConsumerSecret("*")
            .setOAuthAccessToken("*")
            .setOAuthAccessTokenSecret("*");
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        Query q = new Query(query);
        QueryResult result;
        try {
            do {
                result = twitter.search(q);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((q = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}

//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//public class IteratorDemo {
//
//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("alpha");
//        list.add("bravo");
//        list.add("charlie");
//
//        System.out.println("-----");
//        printIterable(list.iterator());
//        System.out.println("-----");
//    }
//
//    private static void printIterable(Iterator<String> iterator) {
//        while (iterator.hasNext()) {
//            String s = iterator.next();
//            System.out.println(s);
//        }
//    }
//}