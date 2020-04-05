package com.moudjames23.coronanews.customs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.moudjames23.coronanews.R;

@SuppressLint("AppCompatCustomView")
public class FontTextView extends TextView {

    public FontTextView(Context context) {
        super(context);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);

    }

    public FontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            int fontFlag;
            Typeface typeface;
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.TextViewCustomFont,
                    0, 0);
            try {
                fontFlag = a.getInteger(R.styleable.TextViewCustomFont_custom_font, 0);
                Log.v("fontFlag", fontFlag + "");
            } finally {
                a.recycle();
            }

            if (fontFlag == 0) {
                typeface = Typeface.createFromAsset(getContext().getAssets(), FontConstants.NUNITO_SANS_BOLD);
            } else if (fontFlag == 1) {
                typeface = Typeface.createFromAsset(getContext().getAssets(), FontConstants.NUNITO_SANS_LIGHT);
            } else {
                typeface = Typeface.createFromAsset(getContext().getAssets(), FontConstants.NUNITO_SANS_REGULAR);
            }
            setTypeface(typeface);
        }
    }
}
