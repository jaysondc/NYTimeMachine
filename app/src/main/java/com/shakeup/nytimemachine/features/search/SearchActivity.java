package com.shakeup.nytimemachine.features.search;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.shakeup.nytimemachine.R;
import com.shakeup.nytimemachine.commons.interfaces.EndlessRecyclerViewScrollListener;
import com.shakeup.nytimemachine.commons.models.Article;
import com.shakeup.nytimemachine.features.search.adapters.ArticleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.recycler_search_results)
    public RecyclerView mRecyclerSearch;
    private SearchViewModel mSearchViewModel;
    private EndlessRecyclerViewScrollListener mScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.shakeup.nytimemachine.R.layout.activity_search);

        // Bind our views with Butterknife
        ButterKnife.bind(this);
        // Create our ViewModel
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        initAdapter();
        requestSearchResults("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_activity, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItem filterItem = menu.findItem(R.id.action_filter);
        searchItem.getIcon().setTint(getColor(R.color.primaryTextColor));
        filterItem.getIcon().setTint(getColor(R.color.primaryTextColor));
        final SearchView searchView = (SearchView) searchItem.getActionView();

        // Set up search query listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
                requestSearchResults(query);

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        filterItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                FragmentManager fm = getSupportFragmentManager();
                FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
                filterDialogFragment.show(fm, "fragment_filter_dialog");

                return true;
            }
        });

        return true;
    }

    private void initAdapter() {
        // Setup our RecyclerView
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerSearch.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerSearch.setAdapter(new ArticleAdapter(this, new ArrayList<Article>()));

        // Set our endless scroll listener
        // Retain an instance so that you can call `resetState()` for fresh searches
        mScrollListener =
                new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        // Triggered only when new data needs to be appended to the list
                        // Add whatever code is needed to append new items to the bottom of the list
                        requestAdditionalResults(page);
                    }
                };
        // Adds the scroll listener to RecyclerView
        mRecyclerSearch.addOnScrollListener(mScrollListener);
    }

    /**
     * Initiates a search through the ViewHolder. The LiveData object allows us to listen for
     * changes to the ArticleList (there won't be any unless the user initiates a new search)
     */
    private void requestSearchResults(String query) {
        mScrollListener.resetState();
        mSearchViewModel.getNewSearchResults(query).observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articleList) {
                ((ArticleAdapter) mRecyclerSearch.getAdapter()).setArticles(articleList);
            }
        });
    }

    /**
     * Initiates a search through the ViewHolder. The LiveData object allows us to listen for
     * changes to the ArticleList (there won't be any unless the user initiates a new search)
     */
    private void requestAdditionalResults(int page) {
        mSearchViewModel.getAdditionalSearchResults(page).observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articleList) {
                ((ArticleAdapter) mRecyclerSearch.getAdapter()).appendArticles(articleList);
            }
        });
    }

    /**
     * Respond to clicks of the filter button
     *
     * @param item is the menu item that was clicked
     */
    public void onFilterClick(MenuItem item) {
        Toast.makeText(this, "Filter button clicked!", Toast.LENGTH_SHORT).show();
    }
}
