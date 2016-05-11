package com.sgj.wangyi.materialrefreshlibrery;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;

/**
 * 这是一个下拉刷新控制;
 * 比SwipeRefreshLayout更漂亮和强大的下拉刷新控件
 */
public class MaterialRefreshLayout extends FrameLayout {

    public static final String Tag = "cjj_log";

    private MaterialHeadView materialHeadView;

    private MaterialFoodView materialFoodView;

    private boolean isOverlay;

    private int waveType;//波型

    private int DEFAULT_WAVE_HEIGHT = 140;//默认_波高

    private int HIGHER_WAVE_HEIGHT = 180;//最大_波高

    private int DEFAULT_HEAD_HEIGHT = 70;//默认_头高度

    private int hIGHER_HEAD_HEIGHT = 100;//最大_头高度

    private int DEFAULT_PROGRESS_SIZE = 50;//默认进步大小

    private int BIG_PROGRESS_SIZE = 56;//大进步的大小

    private int PROGRESS_STOKE_WIDTH = 3;//前进

    private int waveColor;//波色

    protected float mWaveHeight; //波高

    protected float mHeadHeight; //头高

    private View mChildView; //子视图

    protected FrameLayout mHeadLayout;

    protected boolean isRefreshing;

    private float mTouchY;// 触摸时Y坐标

    private float mCurrentY;//当前Y坐标

    private DecelerateInterpolator decelerateInterpolator; //在动画开始的地方速率改变比较慢，然后开始减速

    private float headHeight;// 头高

    private float waveHeight;// 波高

    private int[] colorSchemeColors;// 配色方案

    private int colorsId;

    private int progressTextColor;// 进步文本颜色

    private int progressValue,progressMax;

    private boolean showArrow;

    private int textType;

    private MaterialRefreshListener refreshListener;

    private boolean showProgressBg;

    private int progressBg;

    private boolean isShowWave;

    private int progressSizeType;

    private int progressSize = 0;

    private boolean isLoadMoreing;

    private boolean isLoadMore;//加载更多

    public MaterialRefreshLayout(Context context) {
        this(context, null, 0);
    }

    public MaterialRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defstyleAttr) {
        if (isInEditMode()) {
            return;
        }

        if (getChildCount() > 1) {
            throw new RuntimeException("can only have one child widget");
        }

        decelerateInterpolator = new DecelerateInterpolator(10);


        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MaterialRefreshLayout, defstyleAttr, 0);
        isOverlay = t.getBoolean(R.styleable.MaterialRefreshLayout_overlay,false);
        /**attrs for materialWaveView*/
        waveType = t.getInt(R.styleable.MaterialRefreshLayout_wave_height_type, 0);
        if (waveType == 0) {
            headHeight = DEFAULT_HEAD_HEIGHT;
            waveHeight = DEFAULT_WAVE_HEIGHT;
            MaterialWaveView.DefaulHeadHeight = DEFAULT_HEAD_HEIGHT;
            MaterialWaveView.DefaulWaveHeight = DEFAULT_WAVE_HEIGHT;
        } else {
            headHeight = hIGHER_HEAD_HEIGHT;
            waveHeight = HIGHER_WAVE_HEIGHT;
            MaterialWaveView.DefaulHeadHeight = hIGHER_HEAD_HEIGHT;
            MaterialWaveView.DefaulWaveHeight = HIGHER_WAVE_HEIGHT;
        }
        waveColor = t.getColor(R.styleable.MaterialRefreshLayout_wave_color, Color.WHITE);
        isShowWave = t.getBoolean(R.styleable.MaterialRefreshLayout_wave_show, true);

        /**attrs for circleprogressbar*/
        colorsId = t.getResourceId(R.styleable.MaterialRefreshLayout_progress_colors, R.array.material_colors);
        colorSchemeColors = context.getResources().getIntArray(colorsId);
        showArrow = t.getBoolean(R.styleable.MaterialRefreshLayout_progress_show_arrow, true);
        textType = t.getInt(R.styleable.MaterialRefreshLayout_progress_text_visibility, 1);
        progressTextColor = t.getColor(R.styleable.MaterialRefreshLayout_progress_text_color, Color.BLACK);
        progressValue = t.getInteger(R.styleable.MaterialRefreshLayout_progress_value, 0);
        progressMax = t.getInteger(R.styleable.MaterialRefreshLayout_progress_max_value, 100);
        showProgressBg = t.getBoolean(R.styleable.MaterialRefreshLayout_progress_show_circle_backgroud, true);
        progressBg = t.getColor(R.styleable.MaterialRefreshLayout_progress_backgroud_color, CircleProgressBar.DEFAULT_CIRCLE_BG_LIGHT);
        progressSizeType = t.getInt(R.styleable.MaterialRefreshLayout_progress_size_type,0);
        if(progressSizeType == 0)
        {
            progressSize = DEFAULT_PROGRESS_SIZE;
        }else {
            progressSize = BIG_PROGRESS_SIZE;
        }
        isLoadMore = t.getBoolean(R.styleable.MaterialRefreshLayout_isLoadMore,false);
        t.recycle();
    }

    /**
     * View和Window绑定时就会调用这个函数，此时将会有一个Surface进行绘图之类的逻辑
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        Context context = getContext();

        FrameLayout headViewLayout = new FrameLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.gravity = Gravity.TOP;
        headViewLayout.setLayoutParams(layoutParams);

        mHeadLayout = headViewLayout;

        this.addView(mHeadLayout);

        mChildView = getChildAt(0);

        if (mChildView == null) {
            return;
        }

        setWaveHeight(Util.dip2px(context, waveHeight));
        setHeaderHeight(Util.dip2px(context, headHeight));

        materialHeadView = new MaterialHeadView(context);
        materialHeadView.setWaveColor(isShowWave ? waveColor : Color.WHITE);
        materialHeadView.showProgressArrow(showArrow);
        materialHeadView.setProgressSize(progressSize);
        materialHeadView.setProgressColors(colorSchemeColors);
        materialHeadView.setProgressStokeWidth(PROGRESS_STOKE_WIDTH);
        materialHeadView.setTextType(textType);
        materialHeadView.setProgressTextColor(progressTextColor);
        materialHeadView.setProgressValue(progressValue);
        materialHeadView.setProgressValueMax(progressMax);
        materialHeadView.setIsProgressBg(showProgressBg);
        materialHeadView.setProgressBg(progressBg);
        setHeaderView(materialHeadView);

        materialFoodView = new MaterialFoodView(context);
        LayoutParams layoutParams2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dip2px(context,hIGHER_HEAD_HEIGHT));
        layoutParams2.gravity = Gravity.BOTTOM;
        materialFoodView.setLayoutParams(layoutParams2);
        materialFoodView.showProgressArrow(showArrow);
        materialFoodView.setProgressSize(progressSize);
        materialFoodView.setProgressColors(colorSchemeColors);
        materialFoodView.setProgressStokeWidth(PROGRESS_STOKE_WIDTH);
        materialFoodView.setTextType(textType);
        materialFoodView.setProgressValue(progressValue);
        materialFoodView.setProgressValueMax(progressMax);
        materialFoodView.setIsProgressBg(showProgressBg);
        materialFoodView.setProgressBg(progressBg);
        materialFoodView.setVisibility(View.GONE);
        setFooderView(materialFoodView);
    }

    /**
     * onInterceptTouchEvent()是用于处理事件（类似于预处理，当然也可以不处理）并改变事件的传递方向，
     * 也就是决定是否允许Touch事件继续向下（子控件）传递，一但返回True（代表事件在当前的viewGroup中会被处理），
     * 则向下传递之路被截断（所有子控件将没有机会参与Touch事件），同时把事件传递给当前的控件的onTouchEvent()处理；
     * 返回false，则把事件交给子控件的onInterceptTouchEvent()
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isRefreshing) return true;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchY = ev.getY();
                mCurrentY = mTouchY;
                break;
            case MotionEvent.ACTION_MOVE:
                float currentY = ev.getY();
                float dy = currentY - mTouchY;
                if (dy > 0 && !canChildScrollUp()) {
                    if (materialHeadView != null) {
                        materialHeadView.onBegin(this);
                    }
                    return true;
                }
                else if(dy<0 && !canChildScrollDown()&&isLoadMore)
                {
                    if(materialFoodView != null&&!isLoadMoreing)
                    {
                        soveLoadMoreLogic();
                    }
                    return super.onInterceptTouchEvent(ev);
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void soveLoadMoreLogic() {
        isLoadMoreing = true;
        materialFoodView.setVisibility(View.VISIBLE);
        materialFoodView.onBegin(this);
        materialFoodView.onRefreshing(this);
        if(refreshListener != null)
        {
            refreshListener.onRefreshLoadMore(MaterialRefreshLayout.this);
        }
    }

    /**
     * onTouchEvent()用于处理事件，返回值决定当前控件是否消费（consume）了这个事件，
     * 也就是说在当前控件在处理完Touch事件后，是否还允许Touch事件继续向上（父控件）传递，
     *
     * * @param e
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (isRefreshing) {
            return super.onTouchEvent(e);
        }

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mCurrentY = e.getY();
                float dy = mCurrentY - mTouchY;
                    dy = Math.min(mWaveHeight * 2, dy);
                    dy = Math.max(0, dy);
                    if (mChildView != null) {
                        float offsetY = decelerateInterpolator.getInterpolation(dy / mWaveHeight / 2) * dy / 2;
                        float fraction = offsetY / mHeadHeight;
                        mHeadLayout.getLayoutParams().height = (int) offsetY;
                        mHeadLayout.requestLayout();

                        if (materialHeadView != null) {
                            materialHeadView.onPull(this, fraction);

                        }
                        if (!isOverlay)
                            ViewCompat.setTranslationY(mChildView, offsetY);

                    }
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mChildView != null) {
                    if (isOverlay) {
                        if (mHeadLayout.getLayoutParams().height > mHeadHeight) {

                            updateListener();

                            mHeadLayout.getLayoutParams().height = (int) mHeadHeight;
                            mHeadLayout.requestLayout();

                        } else {
                            mHeadLayout.getLayoutParams().height = 0;
                            mHeadLayout.requestLayout();
                        }

                    } else {
                        if (ViewCompat.getTranslationY(mChildView) >= mHeadHeight) {
                            createAnimatorTranslationY(mChildView, mHeadHeight, mHeadLayout);
                           updateListener();
                        } else {
                            createAnimatorTranslationY(mChildView, 0, mHeadLayout);
                        }
                    }

                }
                return true;
        }
        return super.onTouchEvent(e);
    }


    public void autoRefresh()
    {
        if(!isRefreshing)
        {
            updateListener();
            if(isOverlay)
            {
                mHeadLayout.getLayoutParams().height = (int) mHeadHeight;
                mHeadLayout.requestLayout();
            }else
            {
                createAnimatorTranslationY(mChildView, mHeadHeight, mHeadLayout);
            }
        }

    }

    public void autoRefreshLoadMore()
    {
        if(isLoadMore)
        {
            soveLoadMoreLogic();
        }else
        {
            throw new RuntimeException("you must  setLoadMore ture");
        }
    }

    public void updateListener()
    {
        isRefreshing = true;

        if (materialHeadView != null) {
            materialHeadView.onRefreshing(MaterialRefreshLayout.this);
        }

        if (refreshListener != null) {
            refreshListener.onRefresh(MaterialRefreshLayout.this);
        }

    }

    public void setLoadMore(boolean isLoadMore)
    {
        this.isLoadMore = isLoadMore;
    }

    public void setProgressColors(int[] colors)
    {
        this.colorSchemeColors = colors;
    }

    public void setShowArrow(boolean showArrow)
    {
        this.showArrow = showArrow;
    }

    public void setShowProgressBg(boolean showProgressBg)
    {
        this.showProgressBg = showProgressBg;
    }

    public void setWaveColor(int waveColor)
    {
        this.waveColor = waveColor;
    }

    public void setWaveShow(boolean isShowWave){
        this.isShowWave = isShowWave;
    }

    public void setIsOverLay(boolean isOverLay)
    {
        this.isOverlay = isOverLay;
    }

    public void setProgressValue(int progressValue)
    {
        this.progressValue = progressValue;
        materialHeadView.setProgressValue(progressValue);
    }

    public void createAnimatorTranslationY(final View v, final float h, final FrameLayout fl) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(v);
        viewPropertyAnimatorCompat.setDuration(200);
        viewPropertyAnimatorCompat.setInterpolator(new DecelerateInterpolator());
        viewPropertyAnimatorCompat.translationY(h);
        viewPropertyAnimatorCompat.start();
        viewPropertyAnimatorCompat.setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(View view) {
                float height = ViewCompat.getTranslationY(v);
                fl.getLayoutParams().height = (int) height;
                fl.requestLayout();
            }
        });
    }

    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     */
    public boolean canChildScrollUp() {
        if (mChildView == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14) {
            if (mChildView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mChildView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mChildView, -1) || mChildView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mChildView, -1);
        }
    }

    public boolean canChildScrollDown() {
        if (mChildView == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14) {
            if (mChildView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mChildView;
                if (absListView.getChildCount()>0)
                {
                    int lastChildBottom = absListView.getChildAt(absListView.getChildCount() - 1).getBottom();
                    return absListView.getLastVisiblePosition() == absListView.getAdapter().getCount() - 1 && lastChildBottom <= absListView.getMeasuredHeight();
                }else
                {
                    return false;
                }

            } else {
                return ViewCompat.canScrollVertically(mChildView, 1) || mChildView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mChildView, 1);
        }
    }

    public void setWaveHigher() {
        headHeight = hIGHER_HEAD_HEIGHT;
        waveHeight = HIGHER_WAVE_HEIGHT;
        MaterialWaveView.DefaulHeadHeight = hIGHER_HEAD_HEIGHT;
        MaterialWaveView.DefaulWaveHeight = HIGHER_WAVE_HEIGHT;
    }

    public void finishRefreshing() {
        if (mChildView != null) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(mChildView);
            viewPropertyAnimatorCompat.setDuration(200);
            viewPropertyAnimatorCompat.y(ViewCompat.getTranslationY(mChildView));
            viewPropertyAnimatorCompat.translationY(0);
            viewPropertyAnimatorCompat.setInterpolator(new DecelerateInterpolator());
            viewPropertyAnimatorCompat.start();

            if (materialHeadView != null) {
                materialHeadView.onComlete(MaterialRefreshLayout.this);
            }

            if (refreshListener != null) {
                refreshListener.onfinish();
            }
        }
        isRefreshing = false;
        progressValue = 0;
        setProgressValue(0);
    }

    /**
     * 1 . 完成更新
     */
    public void finishRefresh()
    {
        // 用于延迟ＵＩ操作的方法
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                finishRefreshing();
            }
        },300);
    }

    public void finishRefreshLoadMore()
    {
        this.post(new Runnable() {
            @Override
            public void run() {
                if (materialFoodView != null && isLoadMoreing) {
                    isLoadMoreing = false;
                    materialFoodView.onComlete(MaterialRefreshLayout.this);
                }
            }
        });

    }

    public void setHeaderView(final View headerView) {
        post(new Runnable() {
            @Override
            public void run() {
                mHeadLayout.addView(headerView);
            }
        });
    }

    public void setFooderView(final View fooderView) {
        this.addView(fooderView);
    }


    public void setWaveHeight(float waveHeight) {
        this.mWaveHeight = waveHeight;
    }

    public void setHeaderHeight(float headHeight) {
        this.mHeadHeight = headHeight;
    }

    public void setMaterialRefreshListener(MaterialRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

}