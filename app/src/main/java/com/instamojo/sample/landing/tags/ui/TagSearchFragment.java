package com.instamojo.sample.landing.tags.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.instamojo.sample.R;
import com.instamojo.sample.common.ui.BaseFragment;
import com.instamojo.sample.common.util.CommonUtil;
import com.instamojo.sample.landing.tags.TagCompletionView;
import com.instamojo.sample.landing.tags.util.TokenCompleteTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */

public class TagSearchFragment extends BaseFragment implements View.OnClickListener, TokenCompleteTextView.TokenListener {

    protected TagCompletionView mTagCompletionView;
    private List<String> mTagList = new ArrayList<>();
    private OnTagSearchListener mListener;

    public interface OnTagSearchListener {
        void onTagSearchClicked(List<String> tags);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnTagSearchListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException("HomeActivity must implement OnTagSearchListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_tag_search);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mTagCompletionView = (TagCompletionView) view.findViewById(R.id.searchView);
        mTagCompletionView.setTokenListener(this);
        view.findViewById(R.id.search_button).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionBarIconListener.setActionBarTitle(getString(R.string.enter_tag));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                launchNewScreen();
                break;
        }
    }

    private void launchNewScreen() {
        CommonUtil.hideKeyboard(getActivity());
        mTagList = mTagCompletionView.getObjects();
        if (mTagList.size() > 0) {
            mListener.onTagSearchClicked(mTagList);
            mTagCompletionView.getObjects().clear();
            mTagCompletionView.clear();
            mTagCompletionView.setText("");
        } else
            Toast.makeText(getActivity(), getString(R.string.no_tag_entered), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActionSearchButtonClicked() {
        launchNewScreen();
    }
}
