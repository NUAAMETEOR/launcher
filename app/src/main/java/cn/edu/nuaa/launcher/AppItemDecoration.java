package cn.edu.nuaa.launcher;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Meteor on 2018/3/23.
 */

public class AppItemDecoration extends RecyclerView.ItemDecoration {
    private int[] defaultDivider = new int[]{android.R.attr.listDivider};
    private Drawable divider;
    private Drawable bgPic;

    public AppItemDecoration(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(defaultDivider);
        divider = typedArray.getDrawable(0);
        bgPic = ContextCompat.getDrawable(context, R.drawable.laucher_bg);
        typedArray.recycle();
    }

    /**
     * @param c
     * @param parent
     * @param state  onDraw在绘制每个ITEM之前调用，因此，如果空间重叠，重合的部分，ITEM会覆盖在onDraw中绘制的
     *               图形
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);//暂时没想到画啥，先用默认处理
//        drawEachDecoration(c, parent, state);
    }

    /**
     * @param c
     * @param parent
     * @param state  onDraw在绘制每个ITEM之后调用，因此，如果空间重叠，重合的部分，onDrawOver会覆盖ITEM中的
     *               图形。一句话，后绘制的覆盖先绘制的
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
//        drawEachDecoration(c, parent, state);
    }

    private void drawEachDecoration(Canvas c, RecyclerView parent, RecyclerView.State state) {
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
        outRect.set(0, 0, 0, margin);
    }

    private void drawHorizonDivider(Canvas c, RecyclerView parent, RecyclerView.State state, int position) {
        View                      v            = parent.getChildAt(position);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) v.getLayoutParams();
        int                       top          = v.getBottom() + layoutParams.bottomMargin;
        int                       bottom       = top + divider.getIntrinsicHeight();
        int                       left         = v.getLeft() /*- layoutParams.leftMargin*/;
        int                       right        = v.getRight() /*+ layoutParams.rightMargin*/;
        divider.setBounds(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        divider.draw(c);
    }

}
