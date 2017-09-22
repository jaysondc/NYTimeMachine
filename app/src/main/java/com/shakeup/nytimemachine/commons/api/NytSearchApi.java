package com.shakeup.nytimemachine.commons.api;

import android.util.Log;

import com.shakeup.nytimemachine.commons.models.Article;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Jayson on 9/21/2017.
 *
 * Class for retrieving the NYTSearchAPI and initiating network requests.
 */

public class NytSearchApi {
    private final String TAG = this.getClass().getSimpleName();
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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mSearchApi = retrofit.create(NytSearchApiEndpoints.class);
    }

    public Observable<List<Article>> getSearchResults(final String query) {
        return Observable.create(new Observable.OnSubscribe<List<Article>>() {
            @Override
            public void call(Subscriber<? super List<Article>> subscriber) {
                // Perform network call and response parsing here.
                try {
                    Response<NytSearchResponse> response =
                            mSearchApi.getSearchedArticles(apiKey, query).execute();
                    if (response.isSuccessful()) {
                        Log.d(TAG, "call: Successful request!");
                        // Parse the response into a List<Article>
                        List<ArticleResponse> articleResponseList =
                                response.body().response.docs;

                        List<Article> articleList = new ArrayList<>();
                        for (ArticleResponse articleResponse : articleResponseList) {
                            Article article = new Article(articleResponse);
                            articleList.add(article);
                        }

                        subscriber.onNext(articleList);
                        subscriber.onCompleted();
                    } else {
                        Log.d(TAG, "call: Failed request!");
                        subscriber.onError(new Throwable(response.message()));
                    }
                } catch (Exception e){
                    // Call our subscribers exception handler
                    subscriber.onError(e);
                }

            }
        });
    }
}
