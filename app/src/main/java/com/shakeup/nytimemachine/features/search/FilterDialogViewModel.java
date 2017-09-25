package com.shakeup.nytimemachine.features.search;

import android.arch.lifecycle.ViewModel;

/**
 * Created by Jayson on 9/24/2017.
 *
 * ViewModel used to retain and manage the state of {@link FilterDialogFragment} between rotation
 */

public class FilterDialogViewModel extends ViewModel {

    private boolean mFilterEnabled = false;
    private int mSortOrderIndex = 0;
    private long mDate = System.currentTimeMillis();
    private boolean mArts = true;
    private boolean mFashionStyle = true;
    private boolean mSports = true;

    public boolean getFilterEnabled() {
        return mFilterEnabled;
    }

    public int getSortOrderIndex() {
        return mSortOrderIndex;
    }

    public long getDate() {
        return mDate;
    }

    public boolean getArts() {
        return mArts;
    }

    public boolean getFashionStyle() {
        return mFashionStyle;
    }

    public boolean getSports() {
        return mSports;
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
