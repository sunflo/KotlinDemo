package com.kedacom.kotlindemo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kedacom.kotlindemo.R


/**
 * @author keda
 */
class DemoView @JvmOverloads constructor(
        c: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(c, attrs, defStyleAttr) {

    val mPaint: Paint = Paint()
    val mColor = c.getColor(R.color.colorPrimary)

    override fun onDraw(canvas: Canvas) {

        mPaint.color = mColor
        mPaint.flags = Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG
        canvas.drawCircle(30f, 30f, 20f, mPaint)

        mPaint.setColor(Color.parseColor("#009688"))
        canvas.drawRect(30f, 30f, 230f, 180f, mPaint)

        mPaint.setColor(Color.parseColor("#FF9800"))
        canvas.drawLine(300f, 30f, 450f, 180f, mPaint)

        mPaint.setColor(Color.parseColor("#E91E63"))
        mPaint.textSize = 60f
        canvas.drawText("HenCoder", 500f, 130f, mPaint)

        //
        canvas.drawCircle(50f, 300f, 20f, mPaint)
        canvas.drawCircle(450f, 700f, 20f, mPaint)


        mPaint.shader = LinearGradient(100f, 300f, 500f, 800f, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP)
        canvas.drawCircle(250f, 500f, 200f, mPaint)
        //
        mPaint.shader = null
        canvas.drawCircle(500f, 300f, 20f, mPaint)
        canvas.drawCircle(900f, 700f, 20f, mPaint)
        mPaint.shader = RadialGradient(700f, 500f, 200f, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR)
        canvas.drawCircle(700f, 500f, 200f, mPaint)

        //
        mPaint.shader = null
        canvas.drawCircle(950f, 300f, 20f, mPaint)
        canvas.drawCircle(1350f, 700f, 20f, mPaint)
        mPaint.shader = SweepGradient(1150f, 500f, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"))
        canvas.drawCircle(1150f, 500f, 200f, mPaint)

        //
        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.redpng)
        val logo = BitmapFactory.decodeResource(resources, R.drawable.logo024)
        mPaint.shader = ComposeShader(
                BitmapShader(bitmap1, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT),
                BitmapShader(logo, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT),
                PorterDuff.Mode.XOR
        )
//        mPaint.setColorFilter(LightingColorFilter(0x00ffff, 0x000000))
        canvas.drawCircle(250f, 950f, 200f, mPaint)
        //

        mPaint.shader = null
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 60f
        //700f, 950f, 200f, mPaint
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.pathEffect = DashPathEffect(floatArrayOf(10f, 5f), 10f)
        canvas.drawArc(RectF(500f, 750f, 900f, 1150f), 0f, 300f, false, mPaint)


    }


}