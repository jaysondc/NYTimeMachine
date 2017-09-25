package com.shakeup.nytimemachine.features.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by Jayson on 9/24/2017.
 *
 * ViewModel used to retain and manage the state of {@link FilterDialogFragment} between rotation
 */

public class FilterDialogViewModel extends ViewModel {

    private boolean mFilterEnabled = false;
    private MutableLiveData<Integer> mSortOrderIndex;
    private MutableLiveData<Long> mDate;
    private MutableLiveData<Boolean> mArts;
    private MutableLiveData<Boolean> mFashionStyle;
    private MutableLiveData<Boolean> mSports;

    public boolean getFilterEnabled() {
        return mFilterEnabled;
    }

    public LiveData<Integer> getSortOrderIndex() {
        if (mSortOrderIndex == null) {
            mSortOrderIndex = new MutableLiveData<>();
            mSortOrderIndex.setValue(0);
        }
        return mSortOrderIndex;
    }

    public LiveData<Long> getDate() {
        if (mDate == null) {
            mDate = new MutableLiveData<>();
            mDate.setValue(System.currentTimeMillis());
        }
        return mDate;
    }

    public LiveData<Boolean> getArts() {
        if (mArts == null) {
            mArts = new MutableLiveData<>();
            mArts.setValue(true);
        }
        return mArts;
    }

    public LiveData<Boolean> getFashionStyle() {
        if (mFashionStyle == null) {
            mFashionStyle = new MutableLiveData<>();
            mFashionStyle.setValue(true);
        }
        return mFashionStyle;
    }

    public LiveData<Boolean> getSports() {
        if (mSports == null) {
            mSports = new MutableLiveData<>();
            mSports.setValue(true);
        }
        return mSports;
    }

    public void setFilterEnabled(boolean enabled) {
        this.mFilterEnabled = enabled;
    }

    public void setSortOrderIndex(int index) {
        this.mSortOrderIndex.setValue(index);
    }

    public void setDate(long date) {
        this.mDate.setValue(date);
    }

    public void setArts(boolean checked) {
        this.mArts.setValue(checked);
    }

    public void setFashionStyle(boolean checked) {
        this.mFashionStyle.setValue(checked);
    }

    public void setSports(boolean checked) {
        this.mSports.setValue(checked);
    }
}
