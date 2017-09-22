package com.shakeup.nytimemachine.commons.api;

import com.shakeup.nytimemachine.commons.models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Jayson on 9/21/2017.
 *
 * This interface is used by Retrofit to build requests for different NYTSearchAPI endpoints
 */

public interface NytSearchApiEndpoints {

    /**
     * Simple article search
     * @param key Api key parameter
     * @param query Basic search parameter
     * @return List of {@link Article} objects
     */
    @GET("articlesearch.json")
    Call<List<Article>> getSearchedArticles(
            @Query("api_key") String key,
            @Query("q") String query);
}
