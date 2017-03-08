package com.instamojo.sample.detail.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.instamojo.sample.R;
import com.instamojo.sample.common.dto.BaseItem;
import com.instamojo.sample.common.util.VolleyUtil;
import com.instamojo.sample.common.view.CircularNetworkImageView;
import com.instamojo.sample.common.view.ViewType;
import com.instamojo.sample.detail.dto.QuestionDetailDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */

public class QuestionDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<QuestionDetailDTO> mQuestionItemList = new ArrayList<>();


    public QuestionDetailAdapter(List<QuestionDetailDTO> newsItemList) {
        mQuestionItemList = newsItemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (ViewType.valueOf(viewType)) {
            case DETAIL:
                return new QuestionDetailHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.question_detail_item, parent, false));

            case ANSWER:
                return new AnswerViewHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.detail_list_item, parent, false));
            case HEADER_VIEW:
                return new HeaderViewHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.header_view, parent, false));
            default:
                return new QuestionDetailHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.question_detail_item, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -999;
        BaseItem item = getItemAtPosition(position);
        if (item != null)
            viewType = item.viewType.getValue();
        return viewType;
    }

    private QuestionDetailDTO getItemAtPosition(int position) {
        if (mQuestionItemList != null && !mQuestionItemList.isEmpty() && position >= 0 && position < mQuestionItemList.size())
            return mQuestionItemList.get(position);
        else
            return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (ViewType.valueOf(getItemViewType(position))) {
            case DETAIL:
                QuestionDetailHolder questionDetailHolder = (QuestionDetailHolder) holder;
                setQuestionLayout(questionDetailHolder, position);
                break;
            case ANSWER:
                AnswerViewHolder answerViewHolder = (AnswerViewHolder) holder;
                setAnswerViewLayout(answerViewHolder, position);
                break;
            case HEADER_VIEW:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                setHeaderLayout(headerViewHolder, position);
                break;
        }
    }

    private void setQuestionLayout(QuestionDetailHolder holder, int pos) {
        if (mQuestionItemList.size() > pos) {
            QuestionDetailDTO item = mQuestionItemList.get(pos);

            holder.mName.setText(item.owner.displayName);
            holder.mTitle.setText(item.questionTitle);

            if (Build.VERSION.SDK_INT >= 24) {
                holder.mQuestionDetail.setText(Html.fromHtml(item.body, Html.FROM_HTML_MODE_LEGACY));// for 24 api and more
            } else {
                holder.mQuestionDetail.setText(Html.fromHtml(item.body)); // or for older api
            }

            holder.mImage.setDefaultImageResId(R.drawable.placeholder_profile_pic);
            if (!TextUtils.isEmpty(item.owner.profileImage))
                holder.mImage.setImageUrl(item.owner.profileImage, VolleyUtil.getInstance(holder.mImage.getContext()).getImageLoader());
            else {
                holder.mImage.setImageUrl("", VolleyUtil.getInstance(holder.mImage.getContext()).getImageLoader());
                holder.mInitialName.setVisibility(View.VISIBLE);
                holder.mInitialName.setText(item.owner.displayName.substring(0, 1));
            }
        }
    }

    private void setAnswerViewLayout(AnswerViewHolder holder, int pos) {
        if (mQuestionItemList.size() > pos) {
            QuestionDetailDTO item = mQuestionItemList.get(pos);
            if (Build.VERSION.SDK_INT >= 24) {
                holder.mDetail.setText(String.valueOf(pos - 1) + Html.fromHtml(item.body, Html.FROM_HTML_MODE_LEGACY));// for 24 api and more
            } else {
                holder.mDetail.setText(String.valueOf(pos - 1) + ")" + " " + Html.fromHtml(item.body)); // or for older api
            }
        }
    }

    private void setHeaderLayout(HeaderViewHolder holder, int pos) {
        if (mQuestionItemList.size() > pos) {
            QuestionDetailDTO item = mQuestionItemList.get(pos);
            holder.mHeader.setText(item.header);
        }
    }

    private static class AnswerViewHolder extends RecyclerView.ViewHolder {
        TextView mDetail;

        AnswerViewHolder(View view) {
            super(view);
            mDetail = (TextView) view.findViewById(R.id.list_item);
        }
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView mHeader;

        HeaderViewHolder(View view) {
            super(view);
            mHeader = (TextView) view.findViewById(R.id.header_item);
        }
    }

    private static class QuestionDetailHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        TextView mQuestionDetail;
        TextView mName;
        TextView mInitialName;
        CircularNetworkImageView mImage;

        QuestionDetailHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.question_title);
            mName = (TextView) view.findViewById(R.id.user_name);
            mInitialName = (TextView) view.findViewById(R.id.profile_initial_text);
            mQuestionDetail = (TextView) view.findViewById(R.id.question_detail);
            mImage = (CircularNetworkImageView) view.findViewById(R.id.profile_pic);
        }
    }

    @Override
    public int getItemCount() {
        return mQuestionItemList.size();
    }

}