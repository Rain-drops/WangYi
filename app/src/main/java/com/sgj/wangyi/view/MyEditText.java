package com.sgj.wangyi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by John on 2016/5/13.
 */
public class MyEditText extends EditText {
    public MyEditText(Context context) {

        this(context, null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEditView(context, attrs);
    }

    private void initEditView(Context context, AttributeSet attrs) {

    }

}
