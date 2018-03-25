package cn.edu.nuaa.launcher;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Meteor on 2018/3/23.
 */

public class AppItemDecoration extends RecyclerView.ItemDecoration {
    private int[] defaultDivider = new int[]{android.R.attr.listDivider};
    private Drawable divider;

    public AppItemDecoration(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(defaultDivider);
        Drawable   drawable   = typedArray.getDrawable(0);
        typedArray.recycle();
        divider = drawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            drawHorizonDivider(c, parent, state, i);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childLayoutPosition = parent.getChildLayoutPosition(view);
        Log.d(this.getClass().getName(), "child layout position " + childLayoutPosition);
        int margin = divider.getIntrinsicHeight();
        outRect.set(5, margin, 5, 0);
    }

    private void drawHorizonDivider(Canvas c, RecyclerView parent, RecyclerView.State state, int position) {
        View                      v            = parent.getChildAt(position);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) v.getLayoutParams();
        int                       top          = v.getBottom() + layoutParams.bottomMargin;
        int                       bottom       = top + divider.getIntrinsicHeight();
        int                       left         = v.getLeft() /*- layoutParams.leftMargin*/;
        int                       right        = v.getRight() /*+ layoutParams.rightMargin*/;
        divider.setBounds(left, top, right, bottom);
        divider.draw(c);
    }
}
