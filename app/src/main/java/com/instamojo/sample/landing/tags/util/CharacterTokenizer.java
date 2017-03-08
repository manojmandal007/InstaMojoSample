package com.instamojo.sample.landing.tags.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;

/**
 * Tokenizer with configurable array of characters to tokenize on.
 * <p>
 * Created on 2/3/15.
 *
 * @author mgod
 */

/**
 * Modified by manoj on 7/3/17.
 */

public class CharacterTokenizer implements MultiAutoCompleteTextView.Tokenizer {
    private ArrayList<Character> mSplitChar;

    CharacterTokenizer(char[] splitChar) {
        super();
        mSplitChar = new ArrayList<>(splitChar.length);
        for (char c : splitChar) mSplitChar.add(c);
    }

    public int findTokenStart(CharSequence text, int cursor) {
        int i = cursor;

        while (i > 0 && !mSplitChar.contains(text.charAt(i - 1))) {
            i--;
        }
        while (i < cursor && text.charAt(i) == ' ') {
            i++;
        }

        return i;
    }

    public int findTokenEnd(CharSequence text, int cursor) {
        int i = cursor;
        int len = text.length();

        while (i < len) {
            if (mSplitChar.contains(text.charAt(i))) {
                return i;
            } else {
                i++;
            }
        }

        return len;
    }

    public CharSequence terminateToken(CharSequence text) {
        int i = text.length();

        while (i > 0 && text.charAt(i - 1) == ' ') {
            i--;
        }

        if (i > 0 && mSplitChar.contains(text.charAt(i - 1))) {
            return text;
        } else {
            // Try not to use a space as a token character
            String token = (mSplitChar.size() > 1 && mSplitChar.get(0) == ' ' ? mSplitChar.get(1) : mSplitChar.get(0)) + " ";
            if (text instanceof Spanned) {
                SpannableString sp = new SpannableString(text + token);
                TextUtils.copySpansFrom((Spanned) text, 0, text.length(),
                        Object.class, sp, 0);
                return sp;
            } else {
                return text + token;
            }
        }
    }
}
