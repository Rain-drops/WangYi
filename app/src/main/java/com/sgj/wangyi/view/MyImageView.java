package com.sgj.wangyi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/5/21.
 *
 * http://www.tuicool.com/articles/n6NRfmj
 */
public class MyImageView extends View{

    /**
     * 初始化状态常量
     */
    public static final int STATUS_INIT = 1;

    /**
     * 图片放大状态常量
     */
    public static final int STATUS_ZOOM_OUT = 2;

    /**
     * 图片缩小状态常量
     */
    public static final int STATUS_ZOOM_IN = 3;

    /**
     * 图片拖动状态常量
     */
    public static final int STATUS_MOVE = 4;

    /**
     * 用于对图片进行移动和缩放变换的矩阵
     */
    private Matrix matrix = new Matrix();

    /**
     * 待展示的Bitmap对象
     */
    private Bitmap sourceBitmap;

    /**
     * 记录当前操作的状态，可选值为STATUS_INIT、STATUS_ZOOM_OUT、STATUS_ZOOM_IN和STATUS_MOVE
     */
    private int currentStatus;

    /**
     * ZoomImageView控件的宽度
     */
    private int width;

    /**
     * ZoomImageView控件的高度
     */
    private int height;

    /**
     * 记录两指同时放在屏幕上时，中心点的横坐标值
     */
    private float centerPointX;

    /**
     * 记录两指同时放在屏幕上时，中心点的纵坐标值
     */
    private float centerPointY;

    /**
     * 记录当前图片的宽度，图片被缩放时，这个值会一起变动
     */
    private float currentBitmapWidth;

    /**
     * 记录当前图片的高度，图片被缩放时，这个值会一起变动
     */
    private float currentBitmapHeight;

    /**
     * 记录上次手指移动时的横坐标
     */
    private float lastXMove = -1;

    /**
     * 记录上次手指移动时的纵坐标
     */
    private float lastYMove = -1;

    /**
     * 记录手指在横坐标方向上的移动距离
     */
    private float movedDistanceX;

    /**
     * 记录手指在纵坐标方向上的移动距离
     */
    private float movedDistanceY;

    /**
     * 记录图片在矩阵上的横向偏移值
     */
    private float totalTranslateX;

    /**
     * 记录图片在矩阵上的纵向偏移值
     */
    private float totalTranslateY;

    /**
     * 记录图片在矩阵上的总缩放比例
     */
    private float totalRatio;

    /**
     * 记录手指移动的距离所造成的缩放比例
     */
    private float scaledRatio;

    /**
     * 记录图片初始化时的缩放比例
     */
    private float initRatio;

    /**
     * 记录上次两指之间的距离
     */
    private double lastFingerDis;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        currentStatus = STATUS_INIT;
    }

    public void setBitmap(Bitmap bitmap){
        this.sourceBitmap = bitmap;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed){
            width = getWidth();
            height = getHeight();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(initRatio == totalRatio){
            getParent().requestDisallowInterceptTouchEvent(false);
        }else {
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_POINTER_UP:

                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void zoom(Canvas canvas){

    }

    private void move(Canvas canvas){

    }
}
