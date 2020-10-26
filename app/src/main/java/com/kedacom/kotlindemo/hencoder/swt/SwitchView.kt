package com.kedacom.kotlindemo.hencoder.swt

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @author keda
 */
class SwitchView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mSolidColorUnChecked = Color.WHITE
    private var mSolidColorChecked = Color.BLUE
    private var mBgColor = Color.WHITE
    private var mStrokeColor = Color.GRAY

    private var isChecked = false

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val minWidth = 54
    private val maxWidth = 54
    private var measureWidth = dip2px(minWidth)
    private var measureHeight = measureWidth / 1.8f
    private var mStrokeWidth = 2f
    private var mCircleGap = 12f

    private var mCircleCenterY = measureHeight / 2
    private var mCircleRadius = mCircleCenterY - mCircleGap
    private var mCircleStartCenterX = mStrokeWidth + mCircleGap + mCircleRadius
    private var mCircleEndCenterX = measureWidth - mStrokeWidth - mCircleGap - mCircleRadius

    private var distance = mCircleEndCenterX - mCircleStartCenterX

    /**
     * 动画控制变量
     */
    @set:JvmName("setProgress")
    @get:JvmName("getProgress")
    var mProgress: Float = if (isChecked) 100f else 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        setOnClickListener {
            val anim =
                    if (!isChecked) {
                        ObjectAnimator.ofFloat(this, "progress", 0f, 100f)
                    } else {
                        ObjectAnimator.ofFloat(this, "progress", 100f, 0f)
                    }
            anim.duration = 200
//            anim.interpolator = LinearInterpolator()
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    isChecked = !isChecked
                }
            })
            anim.start()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val height = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        when (widthMode) {
            MeasureSpec.AT_MOST -> {
                measureWidth = dip2px(minWidth)
                measureHeight = measureWidth / 1.8f
                setMeasuredDimension(measureWidth.toInt(), measureHeight.toInt())
            }

            MeasureSpec.UNSPECIFIED -> {
                measureWidth = dip2px(minWidth)
                measureHeight = measureWidth / 1.8f
                setMeasuredDimension(measureWidth.toInt(), measureHeight.toInt())
            }

            MeasureSpec.EXACTLY -> {
                measureWidth = width.toFloat()
            }
        }


    }

    override fun onDraw(canvas: Canvas) {
        mPaint.strokeWidth = mStrokeWidth
        mPaint.style = Paint.Style.STROKE
        mPaint.color = mStrokeColor

        canvas.drawRoundRect(mStrokeWidth, mStrokeWidth, measureWidth - mStrokeWidth, measureHeight - mStrokeWidth, measureWidth / 2, measureWidth / 2, mPaint)
        mPaint.style = Paint.Style.FILL
        mPaint.color = if (isChecked) mSolidColorChecked else mSolidColorUnChecked
        canvas.drawRoundRect(mStrokeWidth, mStrokeWidth, measureWidth - mStrokeWidth, measureHeight - mStrokeWidth, measureWidth / 2, measureWidth / 2, mPaint)

        mPaint.color = mStrokeColor
        canvas.drawCircle(mCircleStartCenterX + distance / 100 * mProgress, mCircleCenterY, mCircleRadius, mPaint)
    }

    private fun dip2px(dpValue: Int): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }

    private fun px2dp(pxValue: Float) = (pxValue / context.resources.displayMetrics.density).toInt()
}