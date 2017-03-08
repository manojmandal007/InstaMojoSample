package com.instamojo.sample.landing.tags.util;

import android.content.res.ColorStateList;
import android.text.style.TextAppearanceSpan;

/**
 * Subclass of TextAppearanceSpan just to work with how Spans get detected
 * <p>
 * Created on 2/3/15.
 *
 * @author mgod
 */

/**
 * Modified by manoj on 7/3/17.
 */

public class HintSpan extends TextAppearanceSpan {
     HintSpan(String family, int style, int size, ColorStateList color, ColorStateList linkColor) {
        super(family, style, size, color, linkColor);
    }
}
