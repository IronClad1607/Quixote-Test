package com.ironclad.quixotetest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.ironclad.quixotetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null


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

        binding?.imageArrow?.startAnimation(rotate)

        binding?.button1?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding?.imageArrow?.clearAnimation()
                    binding?.imageArrow?.rotation = -30f
                }

                MotionEvent.ACTION_UP -> {
                    binding?.imageArrow?.startAnimation(rotate)
                }
            }
            true
        }

        binding?.button2?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding?.imageArrow?.clearAnimation()
                    binding?.imageArrow?.rotation = 30f
                }

                MotionEvent.ACTION_UP -> {
                    binding?.imageArrow?.startAnimation(rotate)
                }
            }
            true
        }

        binding?.button3?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding?.imageArrow?.clearAnimation()
                    binding?.imageArrow?.rotation = 145f
                }

                MotionEvent.ACTION_UP -> {
                    binding?.imageArrow?.startAnimation(rotate)
                }
            }
            true
        }

        binding?.button4?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding?.imageArrow?.clearAnimation()
                    binding?.imageArrow?.rotation = -145f
                }

                MotionEvent.ACTION_UP -> {
                    binding?.imageArrow?.startAnimation(rotate)
                }
            }
            true
        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}