package com.kedacom.kotlindemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_map.*

/**
 * @author keda
 */
class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        kmapview.onCreate(savedInstanceState)
        lifecycle.addObserver(kmapview)



    }
}