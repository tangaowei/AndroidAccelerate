package demo.android.com.viewdemo.touchexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import demo.android.com.viewdemo.R;

/**
 * Created by tangaowei on 18/5/8.
 */

public class TouchExampleView extends View {
    private static final int INVALID_POINTER_ID = -1;

    private Drawable mIcon;
    private ScaleGestureDetector mScaleDetector;

    private float mLastTouchX;
    private float mLastTouchY;

    private float mPosX;
    private float mPosY;

    private int mActivePointerId = INVALID_POINTER_ID;
    private int mActivePointerIndex;

    private float mScaleFactor = 1.0f;

    public TouchExampleView(Context context) {
        this(context, null, 0);
    }

    public TouchExampleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchExampleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mIcon = context.getResources().getDrawable(R.mipmap.ic_launcher);
        mIcon.setBounds(0, 0, mIcon.getIntrinsicWidth(), mIcon.getIntrinsicHeight());
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        mScaleDetector.onTouchEvent(e);

        final int action = e.getAction();
        switch(action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mLastTouchX = e.getX();
                mLastTouchY = e.getY();
                mActivePointerId = e.getPointerId(0);
                Log.i("TGW", "mActivePointerId: " + mActivePointerId);
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerIndex = e.findPointerIndex(mActivePointerId);
                float x = e.getX(pointerIndex);
                float y = e.getY(pointerIndex);

                if (!mScaleDetector.isInProgress()) {
                    float dx = x - mLastTouchX;
                    float dy = y - mLastTouchY;
                    mPosX += dx;
                    mPosY += dy;
                    invalidate();
                }

                mLastTouchX = x;
                mLastTouchY = y;
                break;
            case MotionEvent.ACTION_UP:
                mActivePointerId = INVALID_POINTER_ID;
                break;
            case MotionEvent.ACTION_CANCEL:
                mActivePointerId = INVALID_POINTER_ID;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                int pIndex = (e.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                int pointId = e.getPointerId(pIndex);
                Log.i("TGW", "mActivePointerId: " + mActivePointerId);
                Log.i("TGW", "pIndex: " + pIndex);
                Log.i("TGW", "pointId: " + pointId);
                if (pointId == mActivePointerId) {
                    int newPointerIndex = pIndex == 0 ? 1 : 0;
                    mLastTouchX = e.getX(newPointerIndex);
                    mLastTouchY = e.getY(newPointerIndex);
                    mActivePointerId = e.getPointerId(newPointerIndex);
                }
                break;
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(mPosX, mPosY);
        canvas.scale(mScaleFactor, mScaleFactor);
        mIcon.draw(canvas);
        canvas.restore();
    }

    private class ScaleListener extends  ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }
}
