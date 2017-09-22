package com.shakeup.nytimemachine.features.search.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.shakeup.nytimemachine.R;
import com.shakeup.nytimemachine.commons.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jayson on 9/20/2017.
 *
 * Adapter delegate to handle view binding for Articles
 */

public class ArticleAdapterDelegate extends AdapterDelegate<List<Article>> {

    private final String TAG = this.getClass().getSimpleName();
    private LayoutInflater mInflater;

    public ArticleAdapterDelegate(Activity activity) {
        this.mInflater = activity.getLayoutInflater();
    }

    /**
     * Returns true if the {@link Article} has a valid image url.
     */
    @Override
    protected boolean isForViewType(@NonNull List<Article> items, int position) {
        return true;
        // TODO Reenable this once we have multiple adapter delegates
        // return !items.get(position).hasImages();
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ArticleViewHolder(
                mInflater.inflate(R.layout.listitem_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull List<Article> items,
            int position,
            @NonNull RecyclerView.ViewHolder holder,
            @NonNull List<Object> payloads) {
        ArticleViewHolder viewHolder = (ArticleViewHolder) holder;
        viewHolder.bind(items.get(position));
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_article_headline) public TextView headline;
        @BindView(R.id.textview_article_snippet) public TextView snippet;
        @BindView(R.id.image_article) public ImageView imageView;

        public ArticleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(Article article){
            headline.setText(article.getHeadline());
            snippet.setText(article.getSnippet());
            if (article.hasImages()) {
                String imgUrl = article.getImgUrlWide();
                Picasso.with(imageView.getContext())
                        .load(imgUrl)
                        .into(imageView);
            }
        }
    }

}
