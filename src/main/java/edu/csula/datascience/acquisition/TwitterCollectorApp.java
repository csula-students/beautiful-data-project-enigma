package edu.csula.datascience.acquisition;


public class TwitterCollectorApp {
    public static void main(String[] args) {
    	// Instantiating object of StreamSourceTwitter 
    	StreamSourceTwitter sst=new StreamSourceTwitter();
    	// Calling StreamSourceTwitter method getStream()
    	sst.getStream();
        
    } 
}
