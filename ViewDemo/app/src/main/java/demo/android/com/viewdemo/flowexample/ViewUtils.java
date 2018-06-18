package demo.android.com.viewdemo.flowexample;

import android.graphics.Paint;
import android.text.TextPaint;
import android.widget.TextView;

import demo.android.com.viewdemo.DemoApp;

/**
 * Created by tangaowei on 18/5/10.
 */

public class ViewUtils {
    public static float getTextHeight(TextView view) {
        if (view == null) return 0;
        TextPaint paint = view.getPaint();
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (float)Math.ceil((double)fm.descent - fm.ascent);
    }

    public static float getTextWidth(TextView view) {
        if (view == null) return 0;
        return view.getPaint().measureText(view.getText().toString());
    }

    public static boolean isUrl(String query) {
        return android.util.Patterns.WEB_URL.matcher(query).matches();
    }

    public static float dip2px(float dpValue) {
        final float scale = DemoApp.getContext().getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }
}
