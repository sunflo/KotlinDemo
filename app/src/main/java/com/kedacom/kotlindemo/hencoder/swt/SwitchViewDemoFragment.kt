package com.kedacom.kotlindemo.hencoder.swt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kedacom.kotlindemo.R
import com.kedacom.kotlindemo.hencoder.BaseWidgetFragment

/**
 * @author keda
 */
class SwitchViewDemoFragment : BaseWidgetFragment() {
    init {
        mTitle = "Switch View"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_like_switch, container, false)
    }
}