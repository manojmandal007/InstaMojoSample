package com.instamojo.sample.landing.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.instamojo.sample.R;
import com.instamojo.sample.common.ui.BaseFragment;
import com.instamojo.sample.common.util.Constants;
import com.instamojo.sample.common.util.FragmentUtil;

/**
 * Created by manoj on 8/3/17.
 */

public class LandingFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private static final int UNANSWERED_POSITION = 0, NO_ANSWER_POSITION = 1;
    private RadioButton mUnAnsweredRadioBtn, mNoAnswerRadioBtn;
    private RadioGroup mRadioGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_landing);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mUnAnsweredRadioBtn = (RadioButton) view.findViewById(R.id.unanswer_radio_btn);
        mNoAnswerRadioBtn = (RadioButton) view.findViewById(R.id.no_answer_radio_btn);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
    }

    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRadioGroup.setOnCheckedChangeListener(mCheckedChangeListener);
        mUnAnsweredRadioBtn.setChecked(true);
    }

    private RadioGroup.OnCheckedChangeListener mCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.unanswer_radio_btn:
                    setUpRecentPlaybacksFragment(UNANSWERED_POSITION);
                    break;

                case R.id.no_answer_radio_btn:
                    setUpRecentPlaybacksFragment(NO_ANSWER_POSITION);
                    break;
            }
        }
    };

    private void setUpRecentPlaybacksFragment(int position) {
        Fragment fragment = new QuestionListFragment();
        Bundle bundle = new Bundle();
        switch (position) {
            case UNANSWERED_POSITION:
                bundle.putBoolean(Constants.BundleKeys.IS_NO_ANSWER_BUTTON, false);
                break;
            case NO_ANSWER_POSITION:
                bundle.putBoolean(Constants.BundleKeys.IS_NO_ANSWER_BUTTON, true);
                break;
        }
        bundle.putStringArrayList(Constants.BundleKeys.TAG_LIST, getArguments().getStringArrayList(Constants.BundleKeys.TAG_LIST_KEY));
        fragment.setArguments(bundle);
        FragmentUtil.replaceChildFragment(this, R.id.child_fragment_container, fragment);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case UNANSWERED_POSITION:
                mUnAnsweredRadioBtn.setChecked(true);
                break;
            case NO_ANSWER_POSITION:
                mNoAnswerRadioBtn.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
