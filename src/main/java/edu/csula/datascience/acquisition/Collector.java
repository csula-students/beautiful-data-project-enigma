package edu.csula.datascience.acquisition;

import java.util.Collection;

/**
 * Interface to define collector behavior
 *
 * It should be able to download data from source and save data.
 */
public interface Collector<T,R> {
    /**
     * Mungee method is to clean data. e.g. remove data rows with errors
     */
  /*  Collection<Status> mungee(Collection<Status> src);

    void save(Collection<Status> data);*/
    
	Boolean mungee(String src);

    void save(String username,String profilelocation,long tweetId,String post);
}
