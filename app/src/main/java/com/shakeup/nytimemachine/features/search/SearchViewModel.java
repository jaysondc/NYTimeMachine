package com.shakeup.nytimemachine.features.search;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.shakeup.nytimemachine.NytApplication;
import com.shakeup.nytimemachine.commons.models.Article;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

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

    public Observable<List<Article>> getSearchResults(){
        return mSearchRepo.getSearchArticles("Books");
    }

}
