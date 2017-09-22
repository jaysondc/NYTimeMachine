package com.shakeup.nytimemachine.features.search;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
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
 *
 * ViewModel to hold data for search results. AndroidViewModels survive rotation and persists data
 * as a lifecycle aware component.
 */

public class SearchViewModel extends AndroidViewModel {

    public final String LOG_TAG = this.getClass().getSimpleName();
    private List<Article> mArticles;
    @Inject
    public SearchRepository mSearchRepo;

    public SearchViewModel(Application application){
        super(application);

        ((NytApplication) getApplication()).getApiComponent().inject(this);
    }

    public List<Article> getSearchResults(){
        Observable<List<Article>> call = mSearchRepo.getSearchArticles("Books");
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Article>>() {
                    @Override
                    public void onNext(List<Article> articleList) {
                        Log.d(LOG_TAG, "onNext: Next!");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(LOG_TAG, "onCompleted: Completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(LOG_TAG, "onError: Error!");
                        System.out.println(e.toString());
                    }
                });


        return mSearchRepo.getDummySearchArticles();
    }

}
