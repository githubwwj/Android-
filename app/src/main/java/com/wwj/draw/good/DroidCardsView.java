package com.wwj.draw.good;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DroidCardsView extends View {
    //图片与图片之间的间距
    private int mCardSpacing = 150;
    //图片与左侧距离的记录
    private int mCardLeft = 10;

    private List<DroidCard> mDroidCards = new ArrayList<DroidCard>();

    private Paint paint = new Paint();

    public DroidCardsView(Context context) {
        super(context);
        initCards();
    }

    public DroidCardsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCards();
    }


    /**
     * 初始化卡片集合
     */
    protected void initCards() {
        Resources res = getResources();
        mDroidCards.add(new DroidCard(res, R.drawable.alex, mCardLeft));

        mCardLeft += mCardSpacing;
        mDroidCards.add(new DroidCard(res, R.drawable.claire, mCardLeft));

        mCardLeft += mCardSpacing;
        mDroidCards.add(new DroidCard(res, R.drawable.kathryn, mCardLeft));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        for (DroidCard c : mDroidCards){
//            drawDroidCard(canvas, c);
//        }
        for (int i = 0; i < mDroidCards.size() - 1; i++) {
            drawDroidCard(canvas, mDroidCards.get(i), i + 1);
        }
        drawDroidCard(canvas, mDroidCards.get(mDroidCards.size() - 1));


        invalidate();
    }

    /**
     * 绘制DroidCard
     *
     * @param canvas
     * @param c
     */
    private void drawDroidCard(Canvas canvas, DroidCard c) {
        canvas.drawBitmap(c.bitmap, c.x, 0f, paint);
    }

    private void drawDroidCard(Canvas canvas, DroidCard c, int nextPicPosition) {
        canvas.save();
        canvas.clipRect(c.x, 0, mDroidCards.get(nextPicPosition).x, c.height);
        canvas.drawBitmap(c.bitmap, c.x, 0, paint);
        canvas.restore();

    }
}
