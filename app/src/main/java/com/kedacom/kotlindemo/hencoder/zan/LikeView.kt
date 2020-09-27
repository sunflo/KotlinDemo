package com.kedacom.kotlindemo.hencoder.zan

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.kedacom.kotlindemo.R
import java.lang.Exception

/**
 * @author keda
 */
class LikeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val textSizeDp: Float
    private val tvColorCommon: Int
    private val tvColorChecked: Int

    private var mCount: String

    /**
     * icon与文字间的margin
     */
    private val centerMarginDp = 3f

    private var checked = false

    /**
     * 动画控制变量
     */
    @set:JvmName("setProgress")
    @get:JvmName("getProgress")
    var mProgress: Float = 0f
        set(value) {
            field = value
            invalidate()
        }


    private val mDrawablePaint: Paint
    private val mTvPaint: Paint
    private val mCirclePaint: Paint

    private val shiningDrawable: Bitmap
    private val thumbDrawable: Bitmap
    private val thumbDrawableSelected: Bitmap
    private val lineGap: Float

    init {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.LikeView)
        textSizeDp = arr.getDimension(R.styleable.LikeView_likeCountTextSize, 14f)
        tvColorCommon = arr.getColor(R.styleable.LikeView_likeCountTextColor, context.getColor(R.color.hint_color))
        mCount = arr.getInteger(R.styleable.LikeView_currentCount, 0).toString()
        tvColorChecked = arr.getColor(R.styleable.LikeView_likeCountTextColorChecked, context.getColor(R.color.colorAccent))
        arr.recycle()
//
        mDrawablePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTvPaint = Paint()
        mCirclePaint = Paint()
        mTvPaint.textSize = textSizeDp
        lineGap = mTvPaint.fontSpacing

        shiningDrawable = BitmapFactory.decodeResource(context.resources, R.drawable.ic_messages_like_selected_shining)
        thumbDrawable = BitmapFactory.decodeResource(context.resources, R.drawable.ic_messages_like_unselected)
        thumbDrawableSelected = BitmapFactory.decodeResource(context.resources, R.drawable.ic_messages_like_selected)
        setOnClickListener {
            val anim1 = ObjectAnimator.ofFloat(this, "progress", 0f, 100f)
            anim1.duration = 150
            anim1.interpolator = LinearInterpolator()
            anim1.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    checked = !checked
                }
            })

            val anim2 = ObjectAnimator.ofFloat(this, "progress", 100f, 0f)
            anim2.duration = 150
            anim2.interpolator = LinearInterpolator()
            AnimatorSet().run {
                this.playSequentially(anim1, anim2)
                start()
            }

        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val offsetX = (thumbDrawable.width / 2 - shiningDrawable.width / 2).toFloat()
        val offsetY = shiningDrawable.height / 2.toFloat()
        val textStartX = thumbDrawable.width + dip2px(centerMarginDp)
        val textStartY = thumbDrawable.height + offsetY - (thumbDrawable.height + offsetY - lineGap) / 2
        val circleCenterX = thumbDrawable.width / 2.toFloat()
        val circleCenterY = thumbDrawable.height / 2 + offsetY
        val radius = (thumbDrawable.height + offsetY) / 2

        if (!checked) {
            canvas.save()
            canvas.rotate(-mProgress / 100 * 3, 0f, offsetY + thumbDrawable.height)
            val scale = (mProgress / 100 * 0.08 + 1).toFloat()
            canvas.scale(scale, scale, 0f, offsetY + thumbDrawable.height)
            canvas.drawBitmap(thumbDrawable, 0f, offsetY, mDrawablePaint)
            canvas.restore()

            mTvPaint.color = tvColorCommon
            canvas.drawText(mCount, textStartX, textStartY - (mProgress / 200 * textStartY), mTvPaint)

        } else {
            canvas.save()
            canvas.rotate(-mProgress / 100 * 3, 0f, offsetY + thumbDrawable.height)
            val scale = (mProgress / 100 * 0.08 + 1).toFloat()
            canvas.scale(scale, scale, 0f, offsetY + thumbDrawable.height)
            canvas.drawBitmap(thumbDrawableSelected, 0f, offsetY, mDrawablePaint)
            canvas.restore()
            canvas.drawBitmap(shiningDrawable, offsetX, 0f, mDrawablePaint)
            mTvPaint.color = tvColorChecked
            canvas.drawText(mCount.xx(), textStartX, textStartY + (mProgress / 200 * textStartY), mTvPaint)

        }
    }

    private fun String.xx(): String {
        return try {
            var oo = this.toInt()
            (++oo).toString()
        } catch (e: Exception) {
            this
        }

    }


    private fun dip2px(dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }

    private fun sp2px(spValue: Float): Float {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return spValue * fontScale + 0.5f
    }


}