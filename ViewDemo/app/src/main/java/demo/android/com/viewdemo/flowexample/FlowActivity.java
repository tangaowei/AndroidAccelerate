package demo.android.com.viewdemo.flowexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import demo.android.com.viewdemo.R;

/**
 * Created by tangaowei on 18/5/9.
 */

public class FlowActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flowactivity_layout);

        String[] str1 = {
                "前任填土后人收", "想窗招致", "贵在来了", "好嗨型的", "破环之外", "北京天气", "李ss", "下一站天后", "北京天气预报", "世界读书日四大名树", "世界读书日四大名shu"
        };
        final FlowLayout layout = (FlowLayout) findViewById(R.id.flow_layout);
        final List<BeanItem> list = new ArrayList<>();
        for (int i = 0; i < str1.length; ++i) {
            BeanItem history = new BeanItem();
            history.content = str1[i];
            list.add(history);
        }

        final FlowLayout layout1 = (FlowLayout) findViewById(R.id.flow_layout1);
        final List<BeanItem> list1 = new ArrayList<>();
        for (int i = 0; i < str1.length; ++i) {
            BeanItem history = new BeanItem();
            history.content = str1[i] + "_xx_" + i;
            list1.add(history);
        }

        final IFlowAdapter adapter = new FlowAdapter(this, list, layout);
        final IFlowAdapter adapter1 = new FlowAdapter(this, list1, layout1);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                layout.setAdapter(adapter);
                layout1.setAdapter(adapter1);
            }
        });
    }
}
