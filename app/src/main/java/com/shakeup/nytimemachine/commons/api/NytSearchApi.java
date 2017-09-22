package com.shakeup.nytimemachine.commons.api;

import com.shakeup.nytimemachine.commons.models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Jayson on 9/21/2017.
 *
 * Class for retrieving the NYTSearchAPI and initiating network requests.
 */

public class NytSearchApi {
    private String baseUrl;
    private String apiKey;
    private NytSearchApiEndpoints mSearchApi;

    public NytSearchApi(String url, String key) {
        this.baseUrl = url;
        this.apiKey = key;

        // Initialize retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        mSearchApi = retrofit.create(NytSearchApiEndpoints.class);
    }

    public Call<List<Article>> getSearchResults(String query) {
        return mSearchApi.getSearchedArticles(apiKey, query);
    }
}
