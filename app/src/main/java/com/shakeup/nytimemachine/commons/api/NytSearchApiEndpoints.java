package com.shakeup.nytimemachine.commons.api;

import com.shakeup.nytimemachine.commons.models.Article;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Jayson on 9/21/2017.
 * <p>
 * This interface is used by Retrofit to build requests for different NYTSearchAPI endpoints
 */

public interface NytSearchApiEndpoints {

    /**
     * Simple article search
     *
     * @param key   Api key parameter
     * @param query Basic search parameter
     * @return List of {@link Article} objects
     */
    @GET("articlesearch.json")
    Call<NytSearchResponse> getSearchedArticles(
            @Query("api_key") String key,
            @Query("q") String query,
            @Query("page") int page
    );

    /**
     * Default article search
     *
     * @param key API key parameter
     * @return
     */
    @GET("articlesearch.json")
    Call<NytSearchResponse> getDefaultArticles(
            @Query("api_key") String key,
            @Query("page") int page
    );
}
