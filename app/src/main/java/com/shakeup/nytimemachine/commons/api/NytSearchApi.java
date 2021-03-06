package com.shakeup.nytimemachine.commons.api;

import android.util.Log;

import com.shakeup.nytimemachine.commons.models.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
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
    private String apiKey;
    private NytSearchApiEndpoints mSearchApi;

    public NytSearchApi(String url, String key) {
        this.apiKey = key;

        // Initialize retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mSearchApi = retrofit.create(NytSearchApiEndpoints.class);
    }

    public Observable<List<Article>> getSearchResults(final String query,
                                                      final Integer page,
                                                      final String sortBy,
                                                      final String beginDate,
                                                      final String newsDesks) {
        return Observable.create(new Observable.OnSubscribe<List<Article>>() {
            @Override
            public void call(Subscriber<? super List<Article>> subscriber) {
                // Perform network call and response parsing here.
                try {

                    Call<NytSearchResponse> call;
                    if ("".equals(query)) {
                        // Default search
                        call = mSearchApi.getDefaultArticles(apiKey, page);
                    } else {
                        // Explicit search
                        call = mSearchApi.getSearchArticles(
                                apiKey,
                                query,
                                page,
                                sortBy,
                                beginDate,
                                newsDesks);
                    }

                    Response<NytSearchResponse> response = call.execute();
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
                        if (response.raw().code() == 429) {
                            subscriber.onError(new Throwable("Error code 429: Too many requests."));
                        }
                        subscriber.onError(new Throwable(String.format(
                                Locale.getDefault(),
                                "Error code %d",
                                response.raw().code())));
                    }
                } catch (Exception e){
                    // Call our subscribers exception handler
                    subscriber.onError(e);
                }

            }
        });
    }
}
