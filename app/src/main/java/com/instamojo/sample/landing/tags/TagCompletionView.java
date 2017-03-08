package com.instamojo.sample.landing.tags;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.instamojo.sample.R;
import com.instamojo.sample.landing.tags.util.TokenCompleteTextView;
/**
 * Created by mgod on 5/27/15.
 * <p/>
 * Simple custom view example to show how to get selected events from the token
 * view. See ContactsCompletionView and contact_token.xml for usage
 */

/**
 * Modified by manoj on 7/3/17.
 */
public class TagCompletionView extends TokenCompleteTextView<String> {

    public TagCompletionView(Context context) {
        super(context);
    }

    public TagCompletionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagCompletionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected View getViewForObject(String person) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        TextView tag = (TextView) layoutInflater.inflate(R.layout.tag_token_layout, (ViewGroup) getParent(), false);
        tag.setText(person.trim());

        return tag;
    }

    @Override
    protected String defaultObject(String completionText) {
        return completionText;
    }

}

