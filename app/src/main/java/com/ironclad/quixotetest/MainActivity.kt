package com.ironclad.quixotetest

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.ironclad.quixotetest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var thisTouchTime: Long = 0
    private var previousTouchTime: Long = 0
    private var clickHandled = false
    private val DOUBLE_CLICK_INTERVAL = ViewConfiguration.getDoubleTapTimeout().toLong()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = 900
        rotate.repeatCount = Animation.INFINITE

        val sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager


        val accelSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensorListener = object : SensorEventListener {
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

            override fun onSensorChanged(p0: SensorEvent?) {
//                Log.d(
//                    "SENSOR", """
//                    values:
//                    ax : ${p0?.values?.get(0)}
//                    ay : ${p0?.values?.get(1)}
//                    az : ${p0?.values?.get(2)}
//                """.trimIndent()
//                )

                val ax = p0?.values?.get(0)?.toInt() ?: 0
                val ay = p0?.values?.get(1)?.toInt() ?: 0
                val az = p0?.values?.get(2)?.toInt() ?: 0

                if (ax in -1..1 && az in -1..1 && ay !in -1..1) {
                    binding?.imageArrow?.apply {
                        Log.d("SENSOR", "case stop")
                        this.clearAnimation()
                        rotation = 0f
                    }
                    binding?.apply {
                        button1.isClickable = false
                        button2.isClickable = false
                        button3.isClickable = false
                        button4.isClickable = false
                    }
                } else {
                    if (!rotate.hasStarted()) {
                        Log.d("SENSOR", "case start")
                        binding?.imageArrow?.startAnimation(rotate)
                    }
                }
            }

        }

        sm.registerListener(sensorListener, accelSensor, SensorManager.SENSOR_DELAY_NORMAL)

        initTapPressAndRelease(rotate)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initTapPressAndRelease(rotate: RotateAnimation) {
        if (binding?.button1?.isClickable == true) {
            binding?.button1?.setOnTouchListener { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        thisTouchTime = System.currentTimeMillis()
                        if (thisTouchTime - previousTouchTime <= DOUBLE_CLICK_INTERVAL) {
                            binding?.imageArrow?.clearAnimation()
                            binding?.imageArrow?.rotation = 145f
                            clickHandled = true
                        } else {
                            clickHandled = false
                            binding?.imageArrow?.clearAnimation()
                            binding?.imageArrow?.rotation = -30f
                        }
                        previousTouchTime = thisTouchTime
                    }

                    MotionEvent.ACTION_UP -> {
                        binding?.imageArrow?.startAnimation(rotate)
                    }
                }
                true
            }
        }

        if (binding?.button2?.isClickable == true) {
            binding?.button2?.setOnTouchListener { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        thisTouchTime = System.currentTimeMillis()
                        if (thisTouchTime - previousTouchTime <= DOUBLE_CLICK_INTERVAL) {
                            binding?.imageArrow?.clearAnimation()
                            binding?.imageArrow?.rotation = -145f
                            clickHandled = true
                        } else {
                            clickHandled = false
                            binding?.imageArrow?.clearAnimation()
                            binding?.imageArrow?.rotation = 30f
                        }
                        previousTouchTime = thisTouchTime
                    }

                    MotionEvent.ACTION_UP -> {
                        binding?.imageArrow?.startAnimation(rotate)
                    }
                }
                true
            }
        }

        if (binding?.button3?.isClickable == true) {
            binding?.button3?.setOnTouchListener { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        thisTouchTime = System.currentTimeMillis()
                        if (thisTouchTime - previousTouchTime <= DOUBLE_CLICK_INTERVAL) {
                            binding?.imageArrow?.clearAnimation()
                            binding?.imageArrow?.rotation = -30f
                            clickHandled = true
                        } else {
                            clickHandled = false
                            binding?.imageArrow?.clearAnimation()
                            binding?.imageArrow?.rotation = 145f
                        }
                        previousTouchTime = thisTouchTime
                    }

                    MotionEvent.ACTION_UP -> {
                        binding?.imageArrow?.startAnimation(rotate)
                    }
                }
                true
            }
        }

        if (binding?.button4?.isClickable == true) {
            binding?.button4?.setOnTouchListener { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        thisTouchTime = System.currentTimeMillis()
                        if (thisTouchTime - previousTouchTime <= DOUBLE_CLICK_INTERVAL) {
                            binding?.imageArrow?.clearAnimation()
                            binding?.imageArrow?.rotation = 30f
                            clickHandled = true
                        } else {
                            clickHandled = false
                            binding?.imageArrow?.clearAnimation()
                            binding?.imageArrow?.rotation = -145f
                        }
                        previousTouchTime = thisTouchTime
                    }

                    MotionEvent.ACTION_UP -> {
                        binding?.imageArrow?.startAnimation(rotate)
                    }
                }
                true
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}