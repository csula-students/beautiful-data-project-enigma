package edu.csula.datascience.acquisition;


/**
 * Twitter Collector App for Enigma BigData Project.
 */
public class TwitterCollectorApp {
	public static void main(String[] args) {
		// Instantiating object of StreamSourceTwitter
		StreamSourceTwitter streamSourceTwitter = new StreamSourceTwitter();
		// Calling StreamSourceTwitter method getStream()
		streamSourceTwitter.getStream();

	}
}
