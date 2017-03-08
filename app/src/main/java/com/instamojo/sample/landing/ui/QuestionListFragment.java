package com.instamojo.sample.landing.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.instamojo.sample.R;
import com.instamojo.sample.common.ui.RecyclerViewFragment;
import com.instamojo.sample.common.util.CommonUtil;
import com.instamojo.sample.common.util.Constants;
import com.instamojo.sample.landing.adapter.QuestionListAdapter;
import com.instamojo.sample.landing.dto.ItemDTO;
import com.instamojo.sample.landing.presenter.QuestionListPresenter;
import com.instamojo.sample.landing.presenter.QuestionListPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 7/3/17.
 */

public class QuestionListFragment extends RecyclerViewFragment implements QuestionListView, QuestionListAdapter.OnQuestionClickListener {

    private QuestionListPresenter mPresenter = new QuestionListPresenterImpl();
    private List<ItemDTO> mQuestionItemList = new ArrayList<>();
    private List<String> mTagList = new ArrayList<>();
    private String mUrl;
    private OnQuestionItemClicked mListener;

    public interface OnQuestionItemClicked {
        void onQuestionClicked(String questionId);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnQuestionItemClicked) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException("HomeActivity must implement OnQuestionItemClicked");
        }
    }

    @Override
    public boolean isSwipeToRefresh() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setView(this);
        mRequestTag = this.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        extractArguments();
        mActionBarIconListener.setActionBarTitle(getString(R.string.question_list));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (canLoadMore(mTagList.size(), mTotalItemCount)) {
                    mPageNum++;
                    fetchQuestionList();
                }
            }
        });

        setAdapter();
        fetchQuestionList();
    }

    private void extractArguments() {
        boolean isNoAnswerButton = getArguments().getBoolean(Constants.BundleKeys.IS_NO_ANSWER_BUTTON);
        mTagList = getArguments().getStringArrayList(Constants.BundleKeys.TAG_LIST);

        if (isNoAnswerButton)
            mUrl = getString(R.string.no_answered_question_url);
        else
            mUrl = getString(R.string.unanswered_question_url);
    }

    private void setAdapter() {
        QuestionListAdapter adapter = new QuestionListAdapter(mQuestionItemList, this);
        mRecyclerView.setAdapter(adapter);
    }

    private void fetchQuestionList() {
        showProgressBar();
        String url = CommonUtil.getFormattedListUrl(mUrl, mPageNum);
        mLoading = true;
        mPresenter.getQuestionList(getActivity(), mRequestTag, mTagList, url);
    }

    @Override
    public void onNetworkError() {
        hideProgressBar();
        setEmptyView(getString(R.string.no_internet));
    }

    @Override
    public void onAnsweredListDataFetchSuccess(List<ItemDTO> dataList) {
        hideProgressBar();
        mLoading = false;
        mQuestionItemList.addAll(dataList);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        if (mQuestionItemList.isEmpty()) {
            setEmptyView(getString(R.string.no_data));
        }
    }

    @Override
    public void onListDataFetchFailed() {
        hideProgressBar();
        mLoading = false;
        setEmptyView(getString(R.string.no_data));
    }

    @Override
    public void onQuestionClicked(int pos) {
        mListener.onQuestionClicked(mQuestionItemList.get(pos).questionId);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.cancelRequest(getActivity(), mRequestTag);
        mPresenter.onDestroy();
    }
}
