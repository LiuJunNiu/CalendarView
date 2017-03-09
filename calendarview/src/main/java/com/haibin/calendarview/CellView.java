package com.haibin.calendarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by haibin
 * on 2017/3/6.
 */
@SuppressWarnings("unused")
public class CellView extends View {

    private int mDay = 20;
    private String mLunar = "初一";
    private String mScheme = "记";
    private Paint mDayPaint = new Paint();
    private Paint mLunarPaint = new Paint();
    private Paint mSchemePaint = new Paint();
    private Paint mCirclePaint = new Paint();
    private int mRadius;
    private int mCirclePadding;
    private int mCircleColor;

    public CellView(Context context) {
        this(context, null);
    }

    public CellView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mDayPaint.setAntiAlias(true);
        mDayPaint.setColor(Color.BLACK);
        mDayPaint.setFakeBoldText(true);
        mDayPaint.setTextAlign(Paint.Align.CENTER);

        mLunarPaint.setAntiAlias(true);
        mLunarPaint.setColor(Color.GRAY);
        mLunarPaint.setTextAlign(Paint.Align.CENTER);

        mSchemePaint.setAntiAlias(true);
        mSchemePaint.setColor(Color.WHITE);
        mSchemePaint.setFakeBoldText(true);
        mSchemePaint.setTextAlign(Paint.Align.CENTER);

        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CellView);
        mDayPaint.setTextSize(array.getDimensionPixelSize(R.styleable.CellView_cell_day_text_size, 18));
        mLunarPaint.setTextSize(array.getDimensionPixelSize(R.styleable.CellView_cell_lunar_text_size, 12));
        mRadius = (int) array.getDimension(R.styleable.CellView_cell_scheme_radius, 8);
        mSchemePaint.setTextSize(array.getDimensionPixelSize(R.styleable.CellView_cell_scheme_text_size, 6));
        mCirclePadding = array.getDimensionPixelSize(R.styleable.CellView_cell_circle_padding, 4);
        mCirclePaint.setColor(array.getColor(R.styleable.CellView_cell_circle_color, 0xff16BB7F));
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int w = (width - getPaddingLeft() - getPaddingRight());
        int h = (height - getPaddingTop() - getPaddingBottom()) / 4;
        canvas.drawText(String.valueOf(mDay), w / 2, 2 * h + getPaddingTop(), mDayPaint);
        canvas.drawText(mLunar, w / 2, 4 * h + getPaddingTop(), mLunarPaint);
        if (!TextUtils.isEmpty(mScheme)) {
            canvas.drawCircle(w / 2 + mCirclePadding + mDayPaint.getTextSize(), getPaddingTop() + h, mRadius, mCirclePaint);
            canvas.drawText(mScheme, w / 2 + mCirclePadding + mDayPaint.getTextSize(), getPaddingTop() + mRadius / 2 + h, mSchemePaint);
        }
    }

    void init(int day, String lunar, String scheme) {
        this.mDay = day;
        this.mLunar = lunar;
        this.mScheme = scheme;
    }

    void setTextColor(int textColor) {
        mDayPaint.setColor(textColor);
        mLunarPaint.setColor(textColor);
    }

    void setCircleColor(int circleColor) {
        mCirclePaint.setColor(circleColor);
        invalidate();
    }
}
