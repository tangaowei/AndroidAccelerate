package demo.android.com.viewdemo.flowexample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import demo.android.com.viewdemo.R;

import static demo.android.com.viewdemo.flowexample.ViewUtils.dip2px;

/**
 * Created by tangaowei on 18/5/10.
 */

class FlowTextView extends FrameLayout {
    private float mItemMaxWidth = dip2px(155f);
    private float mItemMinWidth = dip2px(50f);

    private TextView mTextView;
    private ImageView mDeleteView = null;
    private boolean mEditableState = false;
    private int mPosition;

    private OnEventListener mListener;

    public FlowTextView(Context context) {
        this(context, null);
    }

    public FlowTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowTextView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.flowtextview_layout, this);
        mTextView = (TextView) findViewById(R.id.searchcontent_view);
        mDeleteView = (ImageView) findViewById(R.id.searchdelete_view);
        mDeleteView.setVisibility(GONE);
    }

    public void init(String text, int position, Context context) {
        mPosition = position;
        mTextView.setText(text);
        mTextView.setIncludeFontPadding(false);
        mTextView.setBackgroundResource(R.drawable.searchcraft_history_item_background_normal);

        mTextView.setMaxWidth((int)mItemMaxWidth);
        mTextView.setMinWidth((int)mItemMinWidth);

        mTextView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mEditableState) {
                    hideMaskAndDelete();
                } else {
                    showMaskAndDelete();
                }
                mEditableState = !mEditableState;
                return true;
            }
        });

        mTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(mPosition);
                }
            }
        });

        mDeleteView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditableState) {
                    if (mListener != null) {
                        mListener.onDelete(mPosition);
                    }
                }
            }
        });
    }

    private void showMaskAndDelete() {
        mDeleteView.setVisibility(VISIBLE);
    }

    private void hideMaskAndDelete() {
        mDeleteView.setVisibility(GONE);
    }

    public void setOnEventListener(OnEventListener listener) {
        mListener = listener;
    }

    public interface OnEventListener {
        public void onDelete(int position);
        public void onClick(int position);
    }
}