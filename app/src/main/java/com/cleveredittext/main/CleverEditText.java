package com.cleveredittext.main;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Self-defined EditText with clear-text button.
 *
 * @Created on Sep 8nd,2020
 */
public class CleverEditText extends LinearLayout implements View.OnFocusChangeListener {

    private EditText mEditText;
    private ImageView mClearTextImageView;
    private static final float TEXT_SIZE = 14f;
    private static final int TEXT_COLOR = Color.parseColor("#888888");

    private float mTextSize;
    private int mTextColor;

    public CleverEditText(Context context) {
        super(context);
    }

    public CleverEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CleverEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CleverEditText);
        mTextSize = typedArray.getDimension(R.styleable.CleverEditText_textSize, TEXT_SIZE);
        mTextColor = typedArray.getColor(R.styleable.CleverEditText_textColor, TEXT_COLOR);
        init();
    }

    private void init() {

        LayoutInflater.from(getContext()).inflate(R.layout.layout_edittext, this);
        mEditText = findViewById(R.id.layout_edittext);
        mEditText.setTextColor(mTextColor);
        mEditText.setTextSize(mTextSize);
        mClearTextImageView = findViewById(R.id.layout_edittext_clearimg);
        mEditText.setOnFocusChangeListener(this);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    mClearTextImageView.setVisibility(VISIBLE);
                } else {
                    mClearTextImageView.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mClearTextImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}