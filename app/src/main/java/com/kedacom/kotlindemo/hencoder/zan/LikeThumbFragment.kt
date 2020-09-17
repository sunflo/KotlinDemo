package com.kedacom.kotlindemo.hencoder.zan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kedacom.kotlindemo.R
import com.kedacom.kotlindemo.hencoder.BaseWidgetFragment

/**
 * @author keda
 */
class LikeThumbFragment : BaseWidgetFragment() {
    init {
        mTitle = "Like Thumb"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_like_thumb, container, false)
    }
}