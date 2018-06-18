package demo.android.com.viewdemo.flowexample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static demo.android.com.viewdemo.flowexample.ViewUtils.dip2px;

/**
 * Created by tangaowei on 18/6/18.
 */

public class FlowAdapter implements IFlowAdapter {
    private List<BeanItem> beanList = new ArrayList<>();
    private Context mContext;

    private float mItemRightMargin;
    private float mItemBottomMargin;

    private FlowLayout mLayout;

    public FlowAdapter(Context context, List<BeanItem> list, FlowLayout layout) {
        mContext = context;
        beanList = list;

        mItemRightMargin = dip2px(10f);
        mItemBottomMargin = dip2px(10f);
        mLayout = layout;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public View getView(final int pos) {
        final BeanItem item = beanList.get(pos);
        FlowTextView newView = new FlowTextView(mContext);
        newView.init(item.content, pos, mContext);
        newView.requestDisallowInterceptTouchEvent(true);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, (int) mItemRightMargin, (int) mItemBottomMargin);
        newView.setLayoutParams(lp);
        newView.setOnEventListener(new FlowTextView.OnEventListener() {
            @Override
            public void onDelete(int position) {
                beanList.remove(position);
                mLayout.notifyDatasetChange();
            }

            @Override
            public void onClick(int position) {
                Toast.makeText(mContext, beanList.get(position).content, Toast.LENGTH_SHORT).show();
            }
        });
        return newView;
    }
}