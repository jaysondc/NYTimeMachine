package com.shakeup.nytimemachine.features.search;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.shakeup.nytimemachine.commons.models.Article;

import java.util.List;

/**
 * Created by Jayson on 9/20/2017.
 *
 * ViewModel to hold data for search results. AndroidViewModels survive rotation and persists data
 * as a lifecycle aware component.
 */

public class SearchViewModel extends AndroidViewModel {
    private final List<Article> mArticles;
    private final SearchRepository mSearchRepo;

    public SearchViewModel(Application application){
        super(application);

        mSearchRepo = new SearchRepository();
        mArticles = mSearchRepo.getDummySearchArticles();
    }

    public List<Article> getSearchResults(){
        return mArticles;
    }

}
