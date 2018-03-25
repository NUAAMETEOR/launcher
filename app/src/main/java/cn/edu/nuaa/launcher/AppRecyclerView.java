package cn.edu.nuaa.launcher;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;

import java.util.List;

import cn.edu.nuaa.launcher.dummy.AppRecyclerAdapter;

/**
 * Created by Meteor on 2018/3/17.
 */

public class AppRecyclerView extends RecyclerView {

    private int spanCount = 0;

    public AppRecyclerView(Context context, int spanCount) {
        super(context);
        this.spanCount = spanCount;
        init();
    }

    public AppRecyclerView(Context context) {
        super(context);
        init();
    }

    public AppRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutMgr();
        setData();
        addItemDecoration(new AppItemDecoration(getContext()));
    }

    private void setLayoutMgr() {
        if (spanCount == 0) {
            int degree = getScreenOritation((Activity) getContext());
            if (degree / 90 % 2 == 1) {
                spanCount = 8;
            } else {
                spanCount = 4;
            }
        }

        /*GridLayoutManager(getContext(), spanCount, GridLayoutManager.VERTICAL, false);
        第三个参数定义每一页内容衔接的方向（item太多，一页放不下，需翻页），GridLayoutManager.VERTICAL代表垂直方向衔接，GridLayoutManager.HORIZONAL代表水平方向衔接
        第四个参数定义手动翻页的方向。如果第三个参数是GridLayoutManager.VERTICAL，当第四个参数为true时，代表常规的：手指向上滑动翻到下一页，手指向下滑动翻到上一页；
        当为false时，手指向上滑动翻到下一页，手指向下滑动翻到上一页。简单来说，就是翻页方向与常规相反
        */
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount, GridLayoutManager.VERTICAL, false);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), GridLayoutManager.VERTICAL, false);
        setLayoutManager(layoutManager);
    }

    public static int getScreenOritation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degree   = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 0;
                break;
            case Surface.ROTATION_90:
                degree = 90;
                break;
            case Surface.ROTATION_180:
                degree = 180;
                break;
            case Surface.ROTATION_270:
                degree = 270;
                break;
            default:
                degree = 0;
                break;
        }
        return degree;
    }

    private void setData() {
        List<ResolveInfo>  list    = AppFragment.getAppInformation(getContext());
        AppRecyclerAdapter adapter = new AppRecyclerAdapter(list, getContext());
        setAdapter(adapter);
    }


}
