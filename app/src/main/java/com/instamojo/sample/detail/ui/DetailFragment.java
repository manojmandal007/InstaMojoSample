package com.instamojo.sample.detail.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.instamojo.sample.R;
import com.instamojo.sample.common.ui.BaseFragment;
import com.instamojo.sample.common.util.Constants;
import com.instamojo.sample.common.view.ViewType;
import com.instamojo.sample.detail.adapter.QuestionDetailAdapter;
import com.instamojo.sample.detail.dto.QuestionDetailDTO;
import com.instamojo.sample.detail.presenter.DetailPresenter;
import com.instamojo.sample.detail.presenter.DetailPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */

public class DetailFragment extends BaseFragment implements DetailView {

    private DetailPresenter mPresenter = new DetailPresenterImpl();
    private RecyclerView mRecyclerView;
    private TextView mEmptyView;
    private String mQuestionId;
    private List<QuestionDetailDTO> mQuestionItemList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setView(this);
        mRequestTag = this.toString();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_detail);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mEmptyView = (TextView) view.findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.detail_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        extractArguments();
        mActionBarIconListener.setActionBarTitle(getString(R.string.question_detail));
        setAdapter();
        downloadQuestionDetail();
    }

    private void setAdapter() {
        QuestionDetailAdapter adapter = new QuestionDetailAdapter(mQuestionItemList);
        mRecyclerView.setAdapter(adapter);
    }

    private void downloadQuestionDetail() {
        showProgressBar();
        mPresenter.getQuestionDetail(getActivity(), mRequestTag, mQuestionId);
    }

    private void downloadComment() {
        mPresenter.getCommentList(getActivity(), mRequestTag, mQuestionId);
    }


    private void downloadAnswer() {
        mPresenter.getAnswerList(getActivity(), mRequestTag, mQuestionId);
    }

    private void extractArguments() {
        mQuestionId = getArguments().getString(Constants.BundleKeys.QUESTION_ID_KEY);
        // mQuestionId = "42649441";
    }

    @Override
    public void onNetworkError() {
        hideProgressBar();
        setEmptyView();
    }

    private void setEmptyView() {
        if (mRecyclerView.getAdapter() != null && mRecyclerView.getAdapter().getItemCount() == 0) {
            mEmptyView.setText(getString(R.string.no_internet));
        }
    }

    @Override
    public void onDetailDataFetchSuccess(List<QuestionDetailDTO> list) {
        hideProgressBar();
        for (QuestionDetailDTO dto : list) {
            dto.viewType = ViewType.DETAIL;
        }
        mQuestionItemList.addAll(list);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        downloadAnswer();
    }

    @Override
    public void onDetailDataFetchFailed() {
        hideProgressBar();
        setEmptyView();
    }

    @Override
    public void onCommentListDataFetchSuccess(List<QuestionDetailDTO> list) {
        if (list.isEmpty())
            return;
        QuestionDetailDTO detailDTO = new QuestionDetailDTO(ViewType.HEADER_VIEW);
        detailDTO.header = getString(R.string.comments);
        list.add(0, detailDTO);
        mQuestionItemList.addAll(list);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onCommentListDataFetchFailed() {

    }

    @Override
    public void onAnswerListDataFetchSuccess(List<QuestionDetailDTO> list) {
        if (list.isEmpty())
            return;
        QuestionDetailDTO detailDTO = new QuestionDetailDTO(ViewType.HEADER_VIEW);
        detailDTO.header = getString(R.string.answers);
        list.add(0, detailDTO);
        mQuestionItemList.addAll(list);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        downloadComment();
    }

    @Override
    public void onAnswerListDataFetchFailed() {
        downloadComment();
    }


}
