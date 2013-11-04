package com.your.worth.controller.view.viewgroup;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/3/13
 * Time: 6:00 PM
 * This code is adapted from: https://github.com/jaylamont/AndroidFlyOutMenuDemo
 * Tutorial at: http://www.youtube.com/watch?v=YeR7McJIltk
 */
public class HomeViewContainer extends LinearLayout {

    // References to groups contained in this view.
    private View mMenu;
    private View mContent;

    // Constants
    protected static final int mMenuMargin = 175;

    public enum MenuState {
        CLOSED, OPEN, CLOSING, OPENING
    }

    // Animation objects
    protected Scroller mMenuAnimationScroller = new Scroller(this.getContext(),new SmoothInterpolator());

    protected Runnable mMenuAnimationRunnable = new AnimationRunnable();
    protected Handler mMenuAnimationHandler = new Handler();

    // Animation constants
    private static final int mMenuAnimationDuration = 1000;
    private static final int mMenuAnimationPollingInterval = 16;

    // Position information attributes
    protected int mCurrentContentOffset = 0;
    protected MenuState mMenuCurrentState = MenuState.CLOSED;

    public HomeViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        this.mMenu = this.getChildAt(0);
        this.mContent = this.getChildAt(1);

        this.mMenu.setVisibility(View.GONE);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed)
            this.calculateChildDimensions();

        this.mMenu.layout(left, top, right - mMenuMargin, bottom);

        this.mContent.layout(left + this.mCurrentContentOffset, top, right
                + this.mCurrentContentOffset, bottom);
    }

    /**
     * Method that toggles the menu off and on
     */
    public void toggleMenu() {
        switch (mMenuCurrentState) {

            case CLOSED:
                mMenuCurrentState = MenuState.OPENING;
                mMenu.setVisibility(View.VISIBLE);
                mMenuAnimationScroller.startScroll(0, 0, this.getMenuWidth(), 0, mMenuAnimationDuration);
                break;
            case OPEN:
                mMenuCurrentState = MenuState.CLOSING;
                mMenuAnimationScroller.startScroll(mCurrentContentOffset,
                        0, -mCurrentContentOffset, 0, mMenuAnimationDuration);
                break;
            default:
                return;
        }

        mMenuAnimationHandler.postDelayed(mMenuAnimationRunnable, mMenuAnimationPollingInterval);

    }

    /**
     * Method that returns the current menu state
     * @return the menu state OPEN/CLOSE
     */
    public MenuState getMenuState() {
        return mMenuCurrentState;
    }

    private int getMenuWidth() {
        return this.mMenu.getLayoutParams().width;
    }

    private void calculateChildDimensions() {
        this.mContent.getLayoutParams().height = this.getHeight();
        this.mContent.getLayoutParams().width = this.getWidth();

        this.mMenu.getLayoutParams().width = this.getWidth() - mMenuMargin;
        this.mMenu.getLayoutParams().height = this.getHeight();
    }

    private void adjustContentPosition(boolean isAnimationOngoing) {
        int scrollerOffset = this.mMenuAnimationScroller.getCurrX();

        mContent.offsetLeftAndRight(scrollerOffset - mCurrentContentOffset);

        mCurrentContentOffset = scrollerOffset;

        invalidate();

        if (isAnimationOngoing)
            mMenuAnimationHandler.postDelayed(mMenuAnimationRunnable,mMenuAnimationPollingInterval);
        else
            onMenuTransitionComplete();
    }

    private void onMenuTransitionComplete() {
        switch (mMenuCurrentState) {
            case OPENING:
                mMenuCurrentState = MenuState.OPEN;
                break;
            case CLOSING:
                mMenuCurrentState = MenuState.CLOSED;
                mMenu.setVisibility(View.GONE);
                break;
        }
    }

    protected class SmoothInterpolator implements Interpolator {

        @Override
        public float getInterpolation(float t) {
            return (float)Math.pow(t-1, 5) + 1;
        }

    }

    protected class AnimationRunnable implements Runnable {

        @Override
        public void run() {
            HomeViewContainer.this.adjustContentPosition(
                    HomeViewContainer.this.mMenuAnimationScroller.computeScrollOffset());
        }

    }
}
