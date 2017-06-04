package com.konel.adaanahmed.videoken.base;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 9:30 PM
 */

public class ExpandableTextView extends AppCompatTextView {
    private static final int MAX_LINES = 2;
    private int currentMaxLines = Integer.MAX_VALUE;
    private OnTextEllipsisedListener listener;
    private boolean isEllipsised = false;

    public ExpandableTextView(Context context) {
        super(context);
    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        /* If text longer than MAX_LINES set DrawableBottom - I'm using '...' icon */
        post(new Runnable() {
            public void run() {
                if (getLineCount() > MAX_LINES) {
                    isEllipsised = true;
                    if (listener != null)
                        listener.onTextEllipsised();
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    isEllipsised = false;
                }

                setMaxLines(MAX_LINES);
            }
        });
    }


    @Override
    public void setMaxLines(int maxLines) {
        currentMaxLines = maxLines;
        super.setMaxLines(maxLines);
    }

    /* Custom method because standard getMaxLines() requires API > 16 */
    public int getMyMaxLines() {
        return currentMaxLines;
    }

    public void toggleExpand() {
        /* Toggle between expanded collapsed states */

        if (getMyMaxLines() == Integer.MAX_VALUE) {
            setMaxLines(MAX_LINES);
            isEllipsised = true;
        } else {
            setMaxLines(Integer.MAX_VALUE);
            isEllipsised = false;
        }
    }

    public void setEllipsisedListener(OnTextEllipsisedListener listener) {
        this.listener = listener;
    }

    public boolean isEllipsised() {
        return isEllipsised;
    }

    public interface OnTextEllipsisedListener {
        void onTextEllipsised();
    }

}
