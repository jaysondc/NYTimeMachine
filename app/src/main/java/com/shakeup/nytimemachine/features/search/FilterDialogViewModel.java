package com.shakeup.nytimemachine.features.search;

import android.arch.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jayson on 9/24/2017.
 *
 * ViewModel used to retain and manage the state of {@link FilterDialogFragment} between rotation
 */

public class FilterDialogViewModel extends ViewModel {

    private final String SORT_BY_OLDEST = "oldest";
    private final String SORT_BY_NEWEST = "newest";
    private final String NEWS_DESK_BASE = "news_desk";
    private final String NEWS_DESK_ARTS = "Arts";
    private final String NEWS_DESK_FASHION_STYLE = "Fashion & Style";
    private final String NEWS_DESK_SPORTS = "Sports";

    private boolean mFilterEnabled = false;
    private int mSortOrderIndex = 0;
    private long mDate = System.currentTimeMillis();
    private boolean mArts = true;
    private boolean mFashionStyle = true;
    private boolean mSports = true;

    public boolean getFilterEnabled() {
        return mFilterEnabled;
    }

    public String getSortOrder() {
        if (mSortOrderIndex == 0) {
            return SORT_BY_OLDEST;
        } else {
            return SORT_BY_NEWEST;
        }
    }

    public String getDate() {
        Date date = new Date(mDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = format.format(date);

        return formattedDate;
    }

    public String getFriendlyDate() {
        Date date = new Date(mDate);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = format.format(date);

        return formattedDate;
    }

    /**
     * Returns a List of applicable News Desks the user wants to see.
     * @return a List of Strings representing news desks
     */
    public String getNewsDesks() {
        List<String> newsDesks = new ArrayList<>();

        if (mArts) newsDesks.add(NEWS_DESK_ARTS);
        if (mFashionStyle) newsDesks.add(NEWS_DESK_FASHION_STYLE);
        if (mSports) newsDesks.add(NEWS_DESK_SPORTS);

        if (newsDesks.isEmpty()) return null;

        StringBuilder sb = new StringBuilder();
        sb.append(NEWS_DESK_BASE);
        sb.append(":(");
        for (String s : newsDesks) {
            sb.append(s);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");

        return sb.toString();
    }

    public void setFilterEnabled(boolean enabled) {
        this.mFilterEnabled = enabled;
    }

    public void setSortOrderIndex(int index) {
        this.mSortOrderIndex = index;
    }

    public void setDate(long date) {
        this.mDate = date;
    }

    public void setArts(boolean checked) {
        this.mArts = checked;
    }

    public void setFashionStyle(boolean checked) {
        this.mFashionStyle = checked;
    }

    public void setSports(boolean checked) {
        this.mSports = checked;
    }
}
