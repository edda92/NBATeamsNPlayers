package com.mohamed.nbateamsandplayers.ui

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.animation.addListener
import androidx.core.content.ContextCompat
import com.mohamed.nbateamsandplayers.R
import com.mohamed.nbateamsandplayers.databinding.ActivityPlashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var colorAnimator: ValueAnimator
    private lateinit var splashBinding: ActivityPlashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivityPlashScreenBinding.inflate(layoutInflater)
        val view = splashBinding.root
        setContentView(view)
        supportActionBar?.hide()
        initAnimation()
    }

    private fun initAnimation(){
        colorAnimator = ValueAnimator.ofArgb(
            ContextCompat.getColor(applicationContext, R.color.purple_500),
            ContextCompat.getColor(applicationContext, R.color.teal_700),
            ContextCompat.getColor(applicationContext, R.color.red_400),
            ContextCompat.getColor(applicationContext, R.color.pink_400),
            ContextCompat.getColor(applicationContext, R.color.purple_500),
            ContextCompat.getColor(applicationContext, R.color.purple_400),
            ContextCompat.getColor(applicationContext, R.color.yellow_400),
            ContextCompat.getColor(applicationContext, R.color.orange_400)
        )

        colorAnimator.addUpdateListener{
            splashBinding.container.setBackgroundColor(it.animatedValue as Int)
        }
        colorAnimator.duration = 2000L

        //Add AnimationListener for the end of the animation
        colorAnimator.addListener(
            onStart = {},
            onCancel = {},
            onEnd = {
                    goToHome()
            },
            onRepeat = {}
        )

        colorAnimator.start()
    }

    private fun goToHome(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}