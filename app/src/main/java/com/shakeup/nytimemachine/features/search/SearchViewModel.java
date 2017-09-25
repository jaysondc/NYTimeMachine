package com.shakeup.nytimemachine.features.search;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.shakeup.nytimemachine.NytApplication;
import com.shakeup.nytimemachine.commons.models.Article;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jayson on 9/20/2017.
 * <p>
 * ViewModel to hold data for search results. AndroidViewModels survive rotation and persists data
 * as a lifecycle aware component.
 */

public class SearchViewModel extends AndroidViewModel {

    public final String TAG = this.getClass().getSimpleName();
    @Inject
    public SearchRepository mSearchRepo;
    private String mQuery;

    public SearchViewModel(Application application) {
        super(application);

        ((NytApplication) getApplication()).getApiComponent().inject(this);
    }

    public LiveData<List<Article>> getNewSearchResults(String query) {
        mQuery = query;
        return getSearchResults(mQuery, null, null, null, null);
    }

    public LiveData<List<Article>> getAdditionalSearchResults(int page) {
        return getSearchResults(mQuery, page, null, null, null);
    }

    public LiveData<List<Article>> getNewFilteredSearchResults(String query,
                                                               String sortBy,
                                                               String beginDate,
                                                               String newsDesks) {
        mQuery = query;
        return getSearchResults(mQuery, null, sortBy, beginDate, newsDesks);
    }

    public LiveData<List<Article>> getAdditionalFilteredSearchResults(int page,
                                                                      String sortBy,
                                                                      String beginDate,
                                                                      String newsDesks) {
        return getSearchResults(mQuery, page, sortBy, beginDate, newsDesks);
    }

    /**
     * Fetches a List of Articles from SearchRepo and wraps it in a LiveData object to be
     * observed by the SearchActivity.
     *
     * @param query search term for the NytSearchApi
     * @return a LiveData list of articles that can be observed for changes
     */
    private LiveData<List<Article>> getSearchResults(String query,
                                                     Integer page,
                                                     String sortBy,
                                                     String startDate,
                                                     String newsDesks) {

        final MutableLiveData<List<Article>> data = new MutableLiveData<>();

        Observable<List<Article>> observable =
                mSearchRepo.getSearchArticles(
                        query,
                        page,
                        sortBy,
                        startDate,
                        newsDesks);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Article>>() {
                    @Override
                    public void onNext(List<Article> articleList) {
                        Log.d(TAG, "onNext: Next!");
                        data.setValue(articleList);
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: Error!");
                        System.out.println(e.toString());
                    }
                });

        return data;
    }

}
