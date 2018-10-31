/*
 * Modified by Denys Zelenchuk on 4.6.2018.
 */

package com.example.android.architecture.blueprints.todoapp.settings;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;

/**
 * Created by Denys Zelenchuk on 04.06.18.
 */
public class AutocompletePreference extends DialogPreference {

    public AutocompletePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
