package com.kedacom.kotlindemo.hencoder.flp

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View
import com.kedacom.kotlindemo.R

/**
 * @author keda
 */
class FlipView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val bitmapToDraw: Bitmap

    private val mCamera: Camera

    /**
     * 第一段折叠的角度控制变量0~-45
     */
    @set:JvmName("setFlipY")
    @get:JvmName("getFlipY")
    var mFlipY = 0f
        set(value) {
            field = value
            invalidate()
        }

    /**
     * 第二段旋转角度的控制变量 0~270
     */
    @set:JvmName("setRotate")
    @get:JvmName("getRotate")
    var mRotate = 0f
        set(value) {
            field = value
            invalidate()
        }

    /**
     * 第三段折叠的角度控制变量0~30
     */
    @set:JvmName("setFlipX")
    @get:JvmName("getFlipX")
    var mFlipX = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val mPaint: Paint


    init {
        val att = context.obtainStyledAttributes(attrs, R.styleable.FlipView)
        val drawable = att.getDrawable(R.styleable.FlipView_showImage)?.run { this as BitmapDrawable }
        att.recycle()
        bitmapToDraw = if (drawable != null) {
            drawable.bitmap
        } else {
            BitmapFactory.decodeResource(resources, R.drawable.flip_board)
        }
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mCamera = Camera().apply { setLocation(0f, 0f, -resources.displayMetrics.density * 6) }
    }

    override fun onDraw(canvas: Canvas) {
        //获取view中心坐标，便于canvas移动
        val centerX = width / 2.toFloat()
        val centerY = height / 2.toFloat()
        //view绘制的原点
        val startX = centerX - bitmapToDraw.width / 2
        val startY = centerY - bitmapToDraw.height / 2

        canvas.save()
        mCamera.save()
        canvas.translate(centerX, centerY)
        canvas.rotate(-mRotate)
        mCamera.rotateY(-mFlipY)

        mCamera.applyToCanvas(canvas)
        mCamera.restore()
        canvas.clipRect(0f, -centerY, centerX, centerY)
        canvas.rotate(mRotate)
        canvas.translate(-centerX, -centerY)
        canvas.drawBitmap(bitmapToDraw, startX, startY, mPaint)
        canvas.restore()
        //
        canvas.save()
        mCamera.save()
        canvas.translate(centerX, centerY)
        canvas.rotate(-mRotate)
        mCamera.rotateY(mFlipX)
        mCamera.applyToCanvas(canvas)
        canvas.clipRect(-centerX, -centerY, 0f, centerY)
        canvas.rotate(mRotate)
        canvas.translate(-centerX, -centerY)
        canvas.drawBitmap(bitmapToDraw, startX, startY, mPaint)
        mCamera.restore()
        canvas.restore()
        //
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun reset() {
        mFlipX = 0f
        mFlipY = 0f
        mRotate = 0f
    }
}