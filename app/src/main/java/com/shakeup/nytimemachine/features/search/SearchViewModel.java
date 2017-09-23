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

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Jayson on 9/20/2017.
 *
 * ViewModel to hold data for search results. AndroidViewModels survive rotation and persists data
 * as a lifecycle aware component.
 */

public class SearchViewModel extends AndroidViewModel {

    public final String LOG_TAG = this.getClass().getSimpleName();
    private LiveData<List<Article>> mArticles;
    @Inject
    public SearchRepository mSearchRepo;

    public SearchViewModel(Application application){
        super(application);

        ((NytApplication) getApplication()).getApiComponent().inject(this);
    }

    /**
     * Fetches a List of Articles from SearchRepo and wraps it in a LiveData object to be
     * observed by the SearchActivity.
     * @param query search term for the NytSearchApi
     * @return a LiveData list of articles that can be observed for changes
     */
    public LiveData<List<Article>> getSearchResults(String query){

        final MutableLiveData<List<Article>> data = new MutableLiveData<>();

        mSearchRepo.getSearchArticles(query).subscribeOn(Schedulers.io())
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
