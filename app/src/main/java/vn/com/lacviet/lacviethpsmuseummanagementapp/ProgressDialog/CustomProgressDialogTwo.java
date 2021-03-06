package vn.com.lacviet.lacviethpsmuseummanagementapp.ProgressDialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;


public class CustomProgressDialogTwo extends Dialog {
    private ImageView imageView;
    public CustomProgressDialogTwo(Context context, int themeResId) {
        super(context, R.style.CustomProgressDialog);

        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        setTitle(null);
        setCancelable(true);
        //setOnCancelListener(null);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,200);
        imageView = new ImageView(context);
        imageView.setImageResource(themeResId);
        layout.addView(imageView, params);
        addContentView(layout, params);
    }

    @Override
    public void show() {
        super.show();
        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f , Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(2200);
        imageView.setAnimation(anim);
        imageView.startAnimation(anim);
    }
}
