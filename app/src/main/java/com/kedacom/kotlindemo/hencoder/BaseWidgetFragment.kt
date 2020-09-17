package com.kedacom.kotlindemo.hencoder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * @author keda
 */
open class BaseWidgetFragment : Fragment() {
    var mTitle = "Default"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container?.addView(TextView(context).apply {
            text = "hello!!!"
        })
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}