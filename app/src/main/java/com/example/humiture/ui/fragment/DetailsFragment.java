package com.example.humiture.ui.fragment;


import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends BaseFragment {

    @BindView(R2.id.details_title)
    TextView title;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_details;
    }

    @Override
    protected void initView() {
        super.initView();
        String name = getArguments().getString("details");
        title.setText(name);
    }
}
