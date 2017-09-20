package com.shakeup.nytimemachine.features.search;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import com.shakeup.nytimemachine.R;
import com.shakeup.nytimemachine.features.search.adapters.ArticleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler_search_results) RecyclerView mRecyclerSearch;
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

        // Setup our RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        ArticleAdapter adapter = new ArticleAdapter(this, mSearchViewModel.getSearchResults());
        mRecyclerSearch.setLayoutManager(gridLayoutManager);
        mRecyclerSearch.setAdapter(adapter);
    }
}
