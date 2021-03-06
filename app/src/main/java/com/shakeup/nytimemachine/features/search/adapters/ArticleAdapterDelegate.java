package com.shakeup.nytimemachine.features.search.adapters;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.shakeup.nytimemachine.R;
import com.shakeup.nytimemachine.commons.models.Article;

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
        final View mView;
        @BindView(R.id.textview_article_headline) public TextView headline;
        @BindView(R.id.textview_article_snippet) public TextView snippet;
        @BindView(R.id.image_article) public ImageView imageView;

        public ArticleViewHolder(View view) {
            super(view);
            this.mView = view;
            ButterKnife.bind(this, view);
        }

        public void bind(final Article article){
            headline.setText(article.getHeadline());
            snippet.setText(article.getSnippet());
            if (article.hasImages()) {
                imageView.setVisibility(View.VISIBLE);
                String imgUrl = article.getImgUrlWide();
                Glide.with(imageView.getContext())
                        .load(imgUrl)
                        .into(imageView);
            } else {
                imageView.setVisibility(View.GONE);
            }

            // Set click listener
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = article.getWebUrl();
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(mView.getContext(), Uri.parse(url));
                }
            });
        }
    }

}
