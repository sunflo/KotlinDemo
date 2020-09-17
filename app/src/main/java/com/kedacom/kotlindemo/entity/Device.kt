package com.kedacom.kotlindemo.entity

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import java.util.*

/**
 * @author huangxz
 */
class Device : BaseObservable() {
    var name: ObservableField<String> = ObservableField("P2")
    var os: ObservableField<String> = ObservableField("android")
    var color: ObservableField<String> = ObservableField("black")
    var version: ObservableField<String> = ObservableField("1.0.0")
    var serial: ObservableField<String> = ObservableField("F21C8AE81FA")


    fun random() {
        val r = Random()
        name.set("P${r.nextInt(10)}")
        os.set(r.nextString(6))
        color.set(r.nextString(5))
        version.set("1.0.${r.nextInt(9)}")
        serial.set(r.nextString(10))
//        notifyChange()
    }




    private fun Random.nextString(length: Int): String {
        return with(StringBuilder()) {
            for (i in 0 until length) {
                val flag = nextInt(3)
                append(when (flag) {
                    0 -> '0' + nextInt(10)
                    1 -> 'A' + nextInt(26)
                    else -> 'a' + nextInt(26)
                })
            }
            this.toString()
        }
    }
}