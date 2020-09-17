package com.kedacom.kotlindemo

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.kedacom.kotlindemo.databinding.ActivityBindBinding
import com.kedacom.kotlindemo.entity.Device

/**
 * @author huangxz
 */
class DataBindActivity : AppCompatActivity() {

    private val mDevice by lazy {
        Device()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind: ActivityBindBinding = DataBindingUtil.setContentView(this, R.layout.activity_bind)
        bind.device = mDevice
        mDevice.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            }

        })

        bind.chronometer.run {
            base = SystemClock.elapsedRealtime()+50000
            format = "%s"
            isCountDown = true
            start()
            setOnChronometerTickListener {
                if(it.isTheFinalCountDown)
                    it.stop()
                mDevice.version.set(it.text.toString())
            }
        }



    }


}