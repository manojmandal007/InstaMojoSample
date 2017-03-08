/*
 * Copyright © 2016, Dubber
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package com.instamojo.sample.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.instamojo.sample.R;
import com.instamojo.sample.common.util.NetworkUtil;
import com.instamojo.sample.landing.tags.TagCompletionView;


/**
 * Created by manoj on 7/3/17.
 */

/**
 * This class is used as a base class for all listing page
 */
public abstract class RecyclerViewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView mRecyclerView;
    protected TextView mEmptyView;
    protected boolean mLoading = false;
    protected int mTotalItemCount = 100;

    protected int mPageNum = 1, mTotalPages;
    /**
     * The {@link SwipeRefreshLayout} that detects
     * swipe gestures and triggers callbacks in the app.
     */
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract boolean isSwipeToRefresh();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_recycler_view);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEmptyView = (TextView) view.findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.black, android.R.color.darker_gray, android.R.color.holo_orange_dark, android.R.color.black);
        mRecyclerView.setLayoutManager(getLayoutManager());
        enableSwipeToRefresh(isSwipeToRefresh());

    }

    protected void setEmptyView(String emptyText) {
        if (mRecyclerView.getAdapter() != null && mRecyclerView.getAdapter().getItemCount() == 0) {
            mEmptyView.setText(emptyText);
        }
    }

    private int getFistVisibleItemPosition() {
        RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
        int firstVisibleItemPos;
        if (manager instanceof GridLayoutManager)
            firstVisibleItemPos = ((GridLayoutManager) manager).findFirstVisibleItemPosition();
        else
            firstVisibleItemPos = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
        return firstVisibleItemPos;
    }

    /*    protected boolean canLoadMore(boolean isEndOfList) { //int availableItems, long totalItemCount
            handleSwipeToRefresh();
            if (!NetworkUtil.isAvailable(getActivity())) {
                return false;
            }
            boolean lastItem = getFistVisibleItemPosition() + mRecyclerView.getChildCount() == mRecyclerView.getLayoutManager()
                    .getItemCount();
            //boolean moreRows = mPageNum < mTotalPages;
            return !isEndOfList && lastItem && !mLoading;
        }*/
    protected boolean canLoadMore(int availableItems, int totalItemCount) {
        if (!NetworkUtil.isAvailable(getActivity())) {
            return false;
        }
        boolean lastItem = getFistVisibleItemPosition() + mRecyclerView.getChildCount() == mRecyclerView.getLayoutManager()
                .getItemCount();
        boolean moreRows = availableItems < totalItemCount;
        return moreRows && lastItem && !mLoading;
    }

    protected void handleSwipeToRefresh() {
        boolean enable = false;
        if (mRecyclerView != null && mRecyclerView.getChildCount() == 0) {
            enable = true;
        } else if (mRecyclerView != null && mRecyclerView.getChildCount() > 0) {
            // check if the first item of the list is visible
            boolean firstItemVisible = getFistVisibleItemPosition() == 0;
            // check if the top of the first item is visible
            boolean topOfFirstItemVisible = mRecyclerView.getChildAt(0).getTop() == 0;
            // enabling or disabling the refresh layout
            enable = firstItemVisible && topOfFirstItemVisible;
        }
        enableSwipeToRefresh(enable);
    }


    @Override
    public void onRefresh() {
        setRefreshing(true);
    }


    private void enableSwipeToRefresh(boolean status) {
        if (!isSwipeToRefresh()) {
            mSwipeRefreshLayout.setEnabled(false);
            return;
        }
        mSwipeRefreshLayout.setEnabled(status);

    }

    protected boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    protected void setRefreshing(boolean status) {
        mSwipeRefreshLayout.setRefreshing(status);
    }
}
