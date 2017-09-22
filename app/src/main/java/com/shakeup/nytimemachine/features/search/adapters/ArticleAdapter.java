package com.shakeup.nytimemachine.features.search.adapters;

import android.app.Activity;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;
import com.shakeup.nytimemachine.commons.models.Article;

import java.util.List;

/**
 * Created by Jayson on 9/20/2017.
 */

public class ArticleAdapter extends ListDelegationAdapter<List<Article>> {

    public ArticleAdapter(Activity activity, List<Article> articles) {
        // DelegatesManager is a protected Field in ListDelegationAdapter
        delegatesManager.addDelegate(new ArticleAdapterDelegate(activity));

        // Set the items from super class.
        setItems(articles);
    }

    public void addArticles(List<Article> articleList) {
        this.items.addAll(articleList);
        this.notifyDataSetChanged(); // TODO Change this to notify only those articles added
    }
}
