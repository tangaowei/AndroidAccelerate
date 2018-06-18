package demo.android.com.viewdemo.flowexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import demo.android.com.viewdemo.R;

/**
 * Created by tangaowei on 18/5/9.
 */

public class FlowLayout extends ViewGroup {
    private Context mContext;
    private ArrayList<ChildLayout> mChildLayoutArr;
    private ArrayList<View> mChildViewArr;
    private int mLines;
    private IFlowAdapter mFlowAdapter;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.HisFlowLayout);
        mLines = typedArray.getInteger(R.styleable.HisFlowLayout_mlines, 3);
        typedArray.recycle();

        mContext = context;
        init();
    }

    private void init() {
        mChildLayoutArr = new ArrayList<>();
        mChildViewArr = new ArrayList<>();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else {
            setMeasuredDimension(widthSize, calculateLayout());
        }
    }

    private int calculateLayout() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; ++i) {
            getChildAt(i).setVisibility(VISIBLE);
        }

        mChildLayoutArr.clear();
        View child;
        int leftPos = getPaddingLeft();
        int topPos = getPaddingTop();
        int layoutHeight = topPos;

        int paddingLeft = getPaddingLeft();
        int measuredWidth = getMeasuredWidth();

        int lineCount = 1;
        int i = 0;
        for (; i < childCount; ++i) {
            child = getChildAt(i);
            MarginLayoutParams childMargin = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int remainWidth = measuredWidth - leftPos;

            if (remainWidth < (childWidth + childMargin.leftMargin)) {  // 换行
                ++lineCount;
                leftPos = paddingLeft;
                if (i > 0) {
                    View preChild = getChildAt(i - 1);
                    topPos += preChild.getMeasuredHeight();
                    topPos += ((MarginLayoutParams) preChild.getLayoutParams()).bottomMargin;
                }
            }
            if (lineCount > mLines) {
                layoutHeight = topPos;
                break;
            }
            int leftEnd = leftPos + childWidth + childMargin.leftMargin + childMargin.rightMargin;
            int topEnd = topPos + child.getMeasuredHeight();
            mChildLayoutArr.add(new ChildLayout(leftPos, topPos, leftEnd - childMargin.rightMargin, topEnd));
            leftPos = leftEnd;
            if (i == childCount - 1) {
                layoutHeight = topEnd;
            }
        }
        while(i < childCount) {
            getChildAt(i).setVisibility(View.GONE);
            ++i;
        }
        return layoutHeight;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mChildLayoutArr != null) {
            for (int i = 0; i < mChildLayoutArr.size(); ++i) {
                View view = getChildAt(i);
                ChildLayout item = mChildLayoutArr.get(i);
                if (view != null) {
                    view.layout(item.l, item.t, item.r, item.b);
                }
            }
        }
    }

    public void setAdapter(IFlowAdapter adapter) {
        mFlowAdapter = adapter;
        notifyDatasetChange();
    }

    public void notifyDatasetChange() {
        removeAllViews();

        int count = mFlowAdapter.getCount();
        for (int i = 0; i < count; ++i) {
            View newView = mFlowAdapter.getView(i);
            mChildViewArr.add(newView);
            addView(newView);
        }
        requestLayout();
        invalidate();
    }

    class ChildLayout {
        public int l, t, r, b;
        public ChildLayout(int l, int t, int r, int b) {
            this.l = l;
            this.t = t;
            this.r = r;
            this.b = b;
        }
    }
}