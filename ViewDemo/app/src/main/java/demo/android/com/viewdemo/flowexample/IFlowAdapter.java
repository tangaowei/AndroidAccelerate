package demo.android.com.viewdemo.flowexample;

import android.view.View;

/**
 * Created by tangaowei on 18/6/18.
 */

public interface IFlowAdapter {
    public int getCount();
    public View getView(int pos);
}
