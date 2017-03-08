package com.instamojo.sample.landing.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.instamojo.sample.R;
import com.instamojo.sample.common.util.VolleyUtil;
import com.instamojo.sample.common.view.CircularNetworkImageView;
import com.instamojo.sample.landing.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */

public class QuestionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemDTO> mQuestionItemList = new ArrayList<>();
    private OnQuestionClickListener mOnItemClickListener;

    public interface OnQuestionClickListener {
        void onQuestionClicked(int pos);
    }

    public QuestionListAdapter(List<ItemDTO> newsItemList, OnQuestionClickListener onNewsClickListener) {
        mQuestionItemList = newsItemList;
        mOnItemClickListener = onNewsClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View questionView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list, parent, false);
        return new QuestionListHolder(questionView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        QuestionListHolder newsHolder = ((QuestionListHolder) holder);
        setQuestionLayout(newsHolder, position);

    }

    private void setQuestionLayout(QuestionListHolder holder, int pos) {
        if (mQuestionItemList.size() > pos) {
            ItemDTO item = mQuestionItemList.get(pos);

            holder.mName.setText(item.owner.displayName);

            if (Build.VERSION.SDK_INT >= 24) {
                holder.mTitle.setText(Html.fromHtml(item.title, Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
            } else {
                holder.mTitle.setText(Html.fromHtml(item.title));// or for older api
            }
            holder.mTitle.setText(item.title);


            holder.mImage.setDefaultImageResId(R.drawable.placeholder_profile_pic);
            if (!TextUtils.isEmpty(item.owner.profileImage))
                holder.mImage.setImageUrl(item.owner.profileImage, VolleyUtil.getInstance(holder.mImage.getContext()).getImageLoader());
            else {
                holder.mImage.setImageUrl("", VolleyUtil.getInstance(holder.mImage.getContext()).getImageLoader());
                holder.mInitialName.setVisibility(View.VISIBLE);
                holder.mInitialName.setText(item.owner.displayName.substring(0, 1));
            }

            initListener(holder, pos);
        }
    }

    private void initListener(QuestionListHolder holder, final int pos) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onQuestionClicked(pos);
            }
        });

    }

    private static class QuestionListHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        TextView mName;
        TextView mInitialName;
        CircularNetworkImageView mImage;

        QuestionListHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.title);
            mName = (TextView) view.findViewById(R.id.name);
            mInitialName = (TextView) view.findViewById(R.id.profile_initial_text);
            mImage = (CircularNetworkImageView) view.findViewById(R.id.profile_pic);
        }
    }

    @Override
    public int getItemCount() {
        return mQuestionItemList.size();
    }

}