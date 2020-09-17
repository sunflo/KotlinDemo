package com.kedacom.kotlindemo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.room.Room
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.kedacom.kotlindemo.room.AppDataBase
import com.kedacom.kotlindemo.room.User
import com.kedacom.kotlindemo.workmanager.UploadWorker
import kotlinx.android.synthetic.main.activity_sensor.*

/**
 * @author huangxz
 */
class SensorActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var pressure: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

//        initRoom()
    }

    private fun initRoom() {
        val db = Room.databaseBuilder(this, AppDataBase::class.java, "flooooo").allowMainThreadQueries().build()



        db.userDao().insertAll(User(123, "nick", "carter"))

        val page: PagedList<User>



        Toast.makeText(this, "size--> ${db.userDao().getAll().size}", Toast.LENGTH_SHORT).show()

        WorkManager.getInstance(this).enqueue(OneTimeWorkRequestBuilder<UploadWorker>()

                .build())
    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()
        sensorManager.registerListener(this, pressure, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        tv_log.append("$accuracy\n")

    }

    var floatArray: FloatArray = FloatArray(9)
    var result: FloatArray = FloatArray(3)
    override fun onSensorChanged(event: SensorEvent) {

        if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {

            SensorManager.getRotationMatrixFromVector(floatArray, event.values)
            SensorManager.getOrientation(floatArray, result)
            val msg = result.fold(StringBuffer("sensor: \n")) { o, i ->
                o.append(i).append("\n")
            }

            event.values.fold(msg.append("================\n")) { o, i ->
                o.append(i).append("\n")
            }

            msg.append((360f + result[0] * 180f / Math.PI) % 360)
            tv_log.text = msg.toString()
        }
    }
}