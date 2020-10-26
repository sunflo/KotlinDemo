package com.kedacom.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kedacom.kmap.arch.map.KMapManager

import com.kedacom.kmap.plugin.mapbox.MapboxAdapter
import com.kedacom.kotlindemo.hencoder.HenCoderActivity
import com.kedacom.kotlindemo.vm.MainActivityViewModel
import java.util.concurrent.Executor

/**
 * Created by huangxz on 2020/2/21.
 */
class MainActivity : AppCompatActivity() {

    private val executor = Executor { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: MainActivityViewModel = ViewModelProvider(this@MainActivity).get(MainActivityViewModel::class.java)

        viewModel.getUsers().observe(this, Observer {

        })
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                Log.d("flo", "App can authenticate using biometrics.")
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Log.e("flo", "No biometric features available on this device.")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Log.e("flo", "Biometric features are currently unavailable.")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Log.e("flo", "The user hasn't associated any biometric credentials " +
                        "with their account.")
        }

        initMap()

    }

    private fun initMap() {


        KMapManager.setMapAdapter(
                MapboxAdapter()
                        .mapMaxLevel(19)
                        .mapLevel(14)
                        .accessToken("ED6E62901CA443909AE2DE8721F8611702")
                        .styleUrl("http://10.68.8.70:6031/styles/kedamapstyle/style.json")

                , this@MainActivity)
    }


    private fun showBiometricPrompt() {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("请验证指纹信息")
                .setSubtitle("11111")
                .setConfirmationRequired(false)
                .setNegativeButtonText("11取消")
                .build()

        val biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int,
                                                       errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Toast.makeText(applicationContext,
                                "Authentication error: $errString", Toast.LENGTH_SHORT)
                                .show()
                    }

                    override fun onAuthenticationSucceeded(
                            result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        val authenticatedCryptoObject: BiometricPrompt.CryptoObject? =
                                result.cryptoObject
                        // User has verified the signature, cipher, or message
                        // authentication code (MAC) associated with the crypto object,
                        // so you can use it in your app's crypto-driven workflows.
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(applicationContext, "Authentication failed",
                                Toast.LENGTH_SHORT)
                                .show()
                    }
                })

        // Displays the "log in" prompt.
        biometricPrompt.authenticate(promptInfo)
    }

    fun toDataBinding(view: View) {
        startActivity(Intent(this, DataBindActivity::class.java))
    }

    fun toSensor(view: View) {
        startActivity(Intent(this, SensorActivity::class.java))
    }

    fun toSensorAnno(view: View) {
        startActivity(Intent(this, AnnoActivity::class.java))
    }

    fun toDemoView(view: View) {
        startActivity(Intent(this, DemoViewActivity::class.java))
    }

    fun toMapView(view: View) {
        startActivity(Intent(this, MapActivity::class.java))
    }

    fun toSensorDemo(view: View) {

        startActivity(Intent(this, RotationVectorDemo::class.java))
    }

    fun toHendCoder(view: View) {
        startActivity(Intent(this, HenCoderActivity::class.java))
    }


}