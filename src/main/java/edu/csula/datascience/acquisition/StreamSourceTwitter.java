package edu.csula.datascience.acquisition;


import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class StreamSourceTwitter 
{
	TwitterCollector twitterCollector=new TwitterCollector();
	
	public void getStream()
	{
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
	    configurationBuilder.setDebugEnabled(true);
	    configurationBuilder.setOAuthConsumerKey("kXzLwthD4WoBOlUi59t6m410M");
	    configurationBuilder.setOAuthConsumerSecret("DiPfltnNIFV0HVqRxnVbtqhlUYgtRBH6HzoEnYzGKj70kKy8Xo");
	    configurationBuilder.setOAuthAccessToken("1691505486-UlLCYLfHPpkz3vUUeRJT3LC0zcezJTZ0Y9zcgSC");
	    configurationBuilder.setOAuthAccessTokenSecret("FxczETXNLCphIilnkWk0DN1sBjZ7PwyECkoaJcoa7CNdN");
	    
	    TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();

	    StatusListener listener = new StatusListener() {

	        @Override
	        public void onException(Exception arg0) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void onStatus(Status status) {
	            User user = status.getUser();
	            
	            // gets User Name
	            String username = status.getUser().getScreenName();
	            System.out.println(username);
	            // gets Location
	            String profileLocation = user.getLocation();
	            System.out.println(profileLocation);
	            // gets TweetID
	            long tweetId = status.getId(); 
	            System.out.println(tweetId);
	            // gets Posts
	            String content = status.getText();
	            System.out.println(content +"\n");
	            
	         // Calling MUNGEE
	          //  Boolean check=twitterCollector.mungee(profileLocation);
	         //check if it have valid location	            
	     /*       if(!check)
	            {
	            	twitterCollector.save(username, profileLocation, tweetId, content);
	            	System.out.println("SAVING!!!!!!!!");
	            }*/
	        }

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub
				
			}

	      

	    };
	    FilterQuery fq = new FilterQuery();

	    String keywords[] = {"#python","#swift","#SQL","#mongodb","#android","#ios","#NoSQL","#BgData","#Ruby","#PSP","#Xbox360","#JS"};

	    fq.track(keywords);
	    
	    twitterStream.addListener(listener);
	    twitterStream.filter(fq);  	
	}
	
}
