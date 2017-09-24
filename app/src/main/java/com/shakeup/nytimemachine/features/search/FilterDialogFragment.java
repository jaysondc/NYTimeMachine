package com.shakeup.nytimemachine.features.search;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.shakeup.nytimemachine.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jayson on 9/24/2017.
 *
 * Dialog fragment used to set filter options for the search activity
 */

public class FilterDialogFragment extends DialogFragment {

    FilterDialogViewModel mFilterDialogViewModel;

    @BindView(R.id.spinner_sort_order)
    Spinner mSortOrderSpinner;
    @BindView(R.id.edittext_date)
    EditText mDateEditText;
    @BindView(R.id.checkbox_arts)
    CheckBox mArtsCheckbox;
    @BindView(R.id.checkbox_fashion_style)
    CheckBox mFashionStyleCheckbox;
    @BindView(R.id.checkbox_sports)
    CheckBox mSportsCheckbox;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_filter, container);

        mFilterDialogViewModel = ViewModelProviders.of(this).get(FilterDialogViewModel.class);

        ButterKnife.bind(this, view);

        attachViews();

        return view;
    }

    private void attachViews() {
        mFilterDialogViewModel.getSortOrderIndex().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer index) {
                mSortOrderSpinner.setSelection(index);
            }
        });

        mFilterDialogViewModel.getDate().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long date) {
                mDateEditText.setText("No date yet!");
            }
        });

        mFilterDialogViewModel.getArts().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean checked) {
                mArtsCheckbox.setChecked(checked);
            }
        });
        mFilterDialogViewModel.getFashionStyle().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean checked) {
                mFashionStyleCheckbox.setChecked(checked);
            }
        });
        mFilterDialogViewModel.getSports().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean checked) {
                mSportsCheckbox.setChecked(checked);
            }
        });
    }

}
