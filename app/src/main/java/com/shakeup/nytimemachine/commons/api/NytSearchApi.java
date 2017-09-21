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
    private static final String BASE_URL = "https://www.nytimes.com/";
    private static final String API_KEY = "aa082d6fd81f4f46a05d49462cd7b105";
    private NytSearchApiEndpoints mSearchApi;

    public NytSearchApi() {
        // Initialize retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        mSearchApi = retrofit.create(NytSearchApiEndpoints.class);
    }

    public Call<List<Article>> getSearchResults(String query) {
        mSearchApi.getSearchedArticles(API_KEY, query);
    }
}
