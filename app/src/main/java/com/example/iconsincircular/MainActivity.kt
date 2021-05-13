package com.example.iconsincircular

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.iconsincircular.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    var imgPointer: ImageView? = null
    var moonAnimator: ValueAnimator? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imgPointer = binding.rocketIcon

        moonAnimator = animatePointer(TimeUnit.SECONDS.toMillis(10))

        moonAnimator!!.start()


        binding.btnStart.setOnClickListener {

            binding.btnStart.text = "On"



            if (moonAnimator!!.isPaused) {
                moonAnimator!!.resume()
                binding.btnStart.text = "Off"

                Toast.makeText(applicationContext, "Resumed", Toast.LENGTH_SHORT).show()
            } else if (moonAnimator!!.isRunning) {

                Toast.makeText(applicationContext, "You Stopped The Moon", Toast.LENGTH_LONG).show()
                moonAnimator!!.pause()
            } else moonAnimator!!.start()


        }
    }


    private fun animatePointer(orbitDuration: Long): ValueAnimator {
        val anim: ValueAnimator = ValueAnimator.ofInt(0, 359)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams: ConstraintLayout.LayoutParams = imgPointer!!.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = `val`.toFloat()
            imgPointer!!.layoutParams = layoutParams
        }
        anim.duration = orbitDuration
        anim.interpolator = LinearInterpolator()
        anim.repeatMode = ValueAnimator.RESTART
        anim.repeatCount = ValueAnimator.INFINITE
        return anim
    }

}


