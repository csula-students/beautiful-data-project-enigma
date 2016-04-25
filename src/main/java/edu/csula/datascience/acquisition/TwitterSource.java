package edu.csula.datascience.acquisition;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * An example of Source implementation using Twitter4j api to grab tweets
 */
public class TwitterSource implements Source<Status> {
    private long minId;
    private final String searchQuery;

    public TwitterSource(long minId, String query) {
        this.minId = minId;
        this.searchQuery = query;
    }

    @Override
    public boolean hasNext() {
        return minId > 0;
    }

    @Override
    public Collection<Status> next() {
        List<Status> list = Lists.newArrayList();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(("kXzLwthD4WoBOlUi59t6m410M"))
            .setOAuthConsumerSecret(("DiPfltnNIFV0HVqRxnVbtqhlUYgtRBH6HzoEnYzGKj70kKy8Xo"))
            .setOAuthAccessToken(("1691505486-UlLCYLfHPpkz3vUUeRJT3LC0zcezJTZ0Y9zcgSC"))
            .setOAuthAccessTokenSecret(("FxczETXNLCphIilnkWk0DN1sBjZ7PwyECkoaJcoa7CNdN"));
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        Query query = new Query(searchQuery);
        query.setLang("EN");
        query.setSince("20140101");
        if (minId != Long.MAX_VALUE) {
            query.setMaxId(minId);
        }
        System.out.println();
        try {
			list.addAll(getTweets(twitter, query));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return list;
    }

    private List<Status> getTweets(Twitter twitter, Query query) throws InterruptedException {
        QueryResult result;
        List<Status> list = Lists.newArrayList();
        try {
            do {
                result = twitter.search(query);

                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    minId = Math.min(minId, tweet.getId());
                    System.out.println("data "+tweet.getText());
                }
                list.addAll(tweets);
                Thread.sleep(5000);
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException e) {
            // Catch exception to handle rate limit and retry
            e.printStackTrace();
            System.out.println("Got twitter exception. Current min id " + minId);
            try {
                Thread.sleep(e.getRateLimitStatus()
                    .getSecondsUntilReset() * 1000);
                list.addAll(getTweets(twitter, query));
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        return list;
    }
}
