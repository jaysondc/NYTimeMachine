package com.shakeup.nytimemachine.features.search;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.shakeup.nytimemachine.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jayson on 9/24/2017.
 * <p>
 * Dialog fragment used to set filter options for the search activity
 */

public class FilterDialogFragment extends DialogFragment
        implements DatePickerCallback {

    FilterDialogViewModel mFilterViewModel;

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
    @BindView(R.id.button_apply)
    Button mApply;
    @BindView(R.id.button_cancel)
    Button mClear;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_filter, container);

        mFilterViewModel =
                ViewModelProviders.of(getActivity()).get(FilterDialogViewModel.class);

        ButterKnife.bind(this, view);

        attachViews();

        return view;
    }

    private void attachViews() {
        final FilterDialogFragment fragment = this;

        mDateEditText.setText(mFilterViewModel.getFriendlyDate());
        mDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDatePickerDialog datePicker = new SimpleDatePickerDialog();
                datePicker.setCallback(fragment);
                datePicker.show(getFragmentManager(), "date_picker");


            }
        });

        // Set ClickListeners
        mApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onApplyClicked();
            }
        });
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClearClicked();
            }
        });
    }

    /**
     * Once apply is clicked, store new button states to the ViewModel
     */
    public void onApplyClicked() {
        mFilterViewModel.setFilterEnabled(true);

        mFilterViewModel.setSortOrderIndex(
                mSortOrderSpinner.getSelectedItemPosition());
        mFilterViewModel.setArts(mArtsCheckbox.isChecked());
        mFilterViewModel.setFashionStyle(mFashionStyleCheckbox.isChecked());
        mFilterViewModel.setSports(mSportsCheckbox.isChecked());
        this.dismiss();
    }

    /**
     * If cancel is clicked, dismiss the fragment and do nothing.
     */
    public void onClearClicked() {
        mFilterViewModel.setFilterEnabled(false);
        this.dismiss();
    }

    @Override
    public void onDatePicked(long date) {
        mFilterViewModel.setDate(date);
    }

}

