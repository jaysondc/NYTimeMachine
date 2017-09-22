package com.shakeup.nytimemachine.features.search;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toolbar;

import com.shakeup.nytimemachine.R;
import com.shakeup.nytimemachine.commons.models.Article;
import com.shakeup.nytimemachine.features.search.adapters.ArticleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.search_toolbar) public Toolbar mToolbar;
    @BindView(R.id.recycler_search_results) public RecyclerView mRecyclerSearch;
    private SearchViewModel mSearchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.shakeup.nytimemachine.R.layout.activity_search);

        // Bind our views with Butterknife
        ButterKnife.bind(this);
        // Set our custom action bar
        this.setActionBar(mToolbar);
        // Create our ViewModel
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        initAdapter();
        requestSearchResults(); // TODO Replace this with a user initiated search
    }

    private void initAdapter() {
        // Setup our RecyclerView
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerSearch.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerSearch.setAdapter(new ArticleAdapter(this, new ArrayList<Article>()));
    }

    private void requestSearchResults() {
        mSearchViewModel.getSearchResults().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Article>>() {
                    @Override
                    public void onNext(List<Article> articleList) {
                        Log.d(TAG, "onNext: Next!");
                        ((ArticleAdapter) mRecyclerSearch.getAdapter()).addArticles(articleList);
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
    }
}
