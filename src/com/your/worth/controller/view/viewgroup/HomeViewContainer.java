package com.your.worth.controller.view.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

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
        CLOSED, OPEN
    }

    // Position information attributes
    protected int mCurrentContentOffset = 0;
    protected MenuState mMmenuCurrentState = MenuState.CLOSED;

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
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        if (changed)
            this.calculateChildDimensions();

        this.mMenu.layout(left, top, right - mMenuMargin, bottom);

        this.mContent.layout(left + this.mCurrentContentOffset, top, right
                + this.mCurrentContentOffset, bottom);

    }

    //TODO: add animation to the toggle
    /**
     * Method that toggles the menu off and on
     */
    public void toggleMenu() {
        switch (this.mMmenuCurrentState) {
            case CLOSED:
                this.mMenu.setVisibility(View.VISIBLE);
                this.mCurrentContentOffset = this.getMenuWidth();
                this.mContent.offsetLeftAndRight(mCurrentContentOffset);
                this.mMmenuCurrentState = MenuState.OPEN;
                break;
            case OPEN:
                this.mContent.offsetLeftAndRight(-mCurrentContentOffset);
                this.mCurrentContentOffset = 0;
                this.mMmenuCurrentState = MenuState.CLOSED;
                this.mMenu.setVisibility(View.GONE);
                break;
        }

        this.invalidate();
    }

    /**
     * Method that returns the current menu state
     * @return the menu state OPEN/CLOSE
     */
    public MenuState getMenuState() {
        return mMmenuCurrentState;
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

}
