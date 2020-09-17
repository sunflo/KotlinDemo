package com.kedacom.kotlindemo.hencoder.flp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kedacom.kotlindemo.R
import com.kedacom.kotlindemo.hencoder.BaseWidgetFragment

/**
 * @author keda
 */
class FlipBoardFragment : BaseWidgetFragment() {
    init {
        mTitle = "FlipBoard"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.fragment_flipboard, container, false)
        val flipView = fragment.findViewById<FlipView>(R.id.flipview)
        val animator1: ObjectAnimator = ObjectAnimator.ofFloat(flipView, "flipY", 0f, 45f)
        animator1.duration = 1000
        animator1.startDelay = 500

        val animator2: ObjectAnimator = ObjectAnimator.ofFloat(flipView, "rotate", 0f, 270f)
        animator2.duration = 800
        animator2.startDelay = 500

        val animator3: ObjectAnimator = ObjectAnimator.ofFloat(flipView, "flipX", 0f, 30f)
        animator3.duration = 500
        animator3.startDelay = 500

        val set = AnimatorSet()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                activity?.runOnUiThread(Runnable {
                    set.startDelay = 1000
                    set.start()
                })

            }
        })
        set.playSequentially(animator1, animator2, animator3)
        set.start()

        return fragment
    }
}