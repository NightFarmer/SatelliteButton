package com.nightfarmer.satellitebutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfan on 16-8-4.
 */
public class SatelliteView extends RelativeLayout {
    private SatelliteAdapter adapter;
    private int Radius = 400;

    private boolean show;
    private View toggleButton;
    private boolean animRunning;
    List<View> menuItemList = new ArrayList<>();



    public void setAdapter(SatelliteAdapter adapter) {
        this.adapter = adapter;
        init();
    }

    public SatelliteView(Context context) {
        this(context, null);
    }

    public SatelliteView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SatelliteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRadius(int radius) {
        Radius = radius;
    }


    private void init() {
        RelativeLayout.LayoutParams layoutParams1 = (LayoutParams) getLayoutParams();
        toggleButton = adapter.createToggleItem(this);
        toggleButton.setId(R.id.satelliteBtn);
        addView(toggleButton);
        toggleButton.setLayoutParams(layoutParams1);
        for (int i = 0; i < adapter.getCount(); i++) {
            View menuItem = adapter.createMenuItem(this, i);
            menuItem.setAlpha(0);
            addView(menuItem, 0);
            RelativeLayout.LayoutParams layoutParams2 = (LayoutParams) menuItem.getLayoutParams();
            layoutParams2.addRule(RelativeLayout.ALIGN_LEFT, R.id.satelliteBtn);
            layoutParams2.addRule(RelativeLayout.ALIGN_TOP, R.id.satelliteBtn);
            menuItem.setLayoutParams(layoutParams2);
            menuItemList.add(menuItem);
        }

        setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        toggleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleShow();
            }
        });
    }


    public void toggleShow() {
        if (animRunning) return;

        int left = toggleButton.getLeft();
        int top = toggleButton.getTop();
        int right = getMeasuredWidth() - (left + toggleButton.getWidth());
        int bottom = getMeasuredHeight() - (top + toggleButton.getHeight());


        if (left < Radius && top < Radius) {
            partToggle(90, 0);
            return;
        }
        if (right < Radius && top < Radius) {
            partToggle(90, 90);
            return;
        }
        if (right < Radius && bottom < Radius) {
            partToggle(90, 180);
            return;
        }
        if (left < Radius && bottom < Radius) {
            partToggle(90, 270);
            return;
        }
        if (top < Radius) {
            partToggle(180, 0);
            return;
        }
        if (right < Radius) {
            partToggle(180, 90);
            return;
        }
        if (bottom < Radius) {
            partToggle(180, 180);
            return;
        }
        if (left < Radius) {
            partToggle(180, 270);
            return;
        }

        partToggle(360, 0);
    }

    private void partToggle(int angleMax, int startAngle) {
        int count = adapter.getCount();
        AnimatorSet animatorSetTotal = new AnimatorSet();
        ArrayList<Animator> animators = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int angle;
            if (angleMax == 360) {
                angle = angleMax / count;
            } else {
                angle = angleMax / (count - 1);
            }
            float x = (float) (Math.cos((angle * i + startAngle) / 180f * Math.PI) * Radius);
            float y = (float) (Math.sin((angle * i + startAngle) / 180f * Math.PI) * Radius);

            View view = menuItemList.get(i);
            AnimatorSet animatorSet = new AnimatorSet();
            int x_trans = toggleButton.getMeasuredWidth() / 2 - view.getMeasuredWidth() / 2;
            int y_trans = toggleButton.getMeasuredHeight() / 2 - view.getMeasuredHeight() / 2;
            if (show) {
                ObjectAnimator translateX = ObjectAnimator.ofFloat(view, "translationX", x + x_trans, 0 + x_trans);
                ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", y + y_trans, 0 + y_trans);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
                animatorSet.playTogether(translateX, translateY, alpha);
                animatorSet.setStartDelay(50 * (count - 1 - i));
                animators.add(animatorSet);
            } else {
                ObjectAnimator translateX = ObjectAnimator.ofFloat(view, "translationX", 0 + x_trans, x + x_trans);
                ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", 0 + y_trans, y + y_trans);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
                animatorSet.playTogether(translateX, translateY, alpha);
                animatorSet.setStartDelay(50 * i);
                animators.add(animatorSet);
            }
        }
        animRunning = true;
        animatorSetTotal.playTogether(animators);
        animatorSetTotal.setDuration(300);
        if (show) {
            animatorSetTotal.setInterpolator(new AnticipateInterpolator());
        } else {
            animatorSetTotal.setInterpolator(new OvershootInterpolator());
        }
        animatorSetTotal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animRunning = false;
            }
        });
        animatorSetTotal.start();
        show = !show;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        if (isInEditMode()) return;

    }


}
