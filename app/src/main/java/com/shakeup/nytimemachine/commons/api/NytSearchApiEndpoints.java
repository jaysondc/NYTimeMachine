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
     * Filtered article search
     *
     * @param key       Api key parameter
     * @param query     Basic search parameter
     * @param page      page number
     * @param orderBy   sort_by argument
     * @param beginDate begin_date argument
     * @param newsDesks news_desk argument
     * @return A call that returns a list of {@link Article} objects
     */
    @GET("articlesearch.json")
    Call<NytSearchResponse> getSearchArticles(
            @Query("api_key") String key,
            @Query("q") String query,
            @Query("page") Integer page,
            @Query("sort") String orderBy,
            @Query("begin_date") String beginDate,
            @Query("fq") String newsDesks
    );

    /**
     * Default article search
     *
     * @param key API key parameter
     * @return A call that returns a list of {@link Article} objects
     */
    @GET("articlesearch.json")
    Call<NytSearchResponse> getDefaultArticles(
            @Query("api_key") String key,
            @Query("page") Integer page
    );
}
