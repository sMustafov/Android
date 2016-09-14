package myapplication.pc1.com.homeworkthirdex;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecycleViewCustomDecoration extends RecyclerView.ItemDecoration {

    Paint brown, grey, green;
    int offset;

    public RecycleViewCustomDecoration(){
        brown = new Paint(Paint.ANTI_ALIAS_FLAG);
        grey = new Paint(Paint.ANTI_ALIAS_FLAG);
        green = new Paint(Paint.ANTI_ALIAS_FLAG);
        offset = 10;

        brown.setColor(Color.rgb(54, 32, 15));
        brown.setStyle(Paint.Style.FILL);

        grey.setColor(Color.GRAY);
        grey.setStyle(Paint.Style.FILL);

        green.setColor(Color.rgb(55, 111, 42));
        green.setStyle(Paint.Style.STROKE);

        brown.setStrokeWidth(1f);
        grey.setStrokeWidth(1f);
        green.setStrokeWidth(1f);
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(offset, offset, offset, offset);

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for(int i=0; i < parent.getChildCount(); i++){
            final View child = parent.getChildAt(i);
            c.drawRect(
                    layoutManager.getDecoratedLeft(child),
                    layoutManager.getDecoratedTop(child),
                    layoutManager.getDecoratedRight(child),
                    layoutManager.getDecoratedBottom(child),
                    brown);
            if (i % 2 != 0) {
                c.drawRoundRect(
                        layoutManager.getDecoratedLeft(child) + 6,
                        layoutManager.getDecoratedTop(child) + 58,
                        layoutManager.getDecoratedRight(child) - 940,
                        layoutManager.getDecoratedBottom(child) - 58,
                        4f,
                        4f,
                        grey);
            }else {
                c.drawOval(
                        layoutManager.getDecoratedLeft(child) + 40,
                        layoutManager.getDecoratedTop(child) + 70,
                        layoutManager.getDecoratedRight(child) - 980,
                        layoutManager.getDecoratedBottom(child) - 40,
                        green);
                c.drawLine(layoutManager.getDecoratedLeft(child) + 70,
                        layoutManager.getDecoratedTop(child) + 75,
                        layoutManager.getDecoratedRight(child) - 1010,
                        layoutManager.getDecoratedBottom(child) - 45,
                        green);
                c.drawLine(layoutManager.getDecoratedLeft(child) + 90,
                        layoutManager.getDecoratedTop(child) + 85,
                        layoutManager.getDecoratedRight(child) - 1010,
                        layoutManager.getDecoratedBottom(child) - 45,
                        green);
                c.drawLine(layoutManager.getDecoratedLeft(child) + 50,
                        layoutManager.getDecoratedTop(child) + 85,
                        layoutManager.getDecoratedRight(child) - 1010,
                        layoutManager.getDecoratedBottom(child) - 45,
                        green);
            }
        }
    }
}
