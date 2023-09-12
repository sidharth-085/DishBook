package com.app.dishbook.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.app.dishbook.R

class SplashScreenActivity : AppCompatActivity() {
    var topAnimation: Animation ?= null
    var bottomAnimation: Animation ?= null

    var splashImage: ImageView ?= null
    var splashText: TextView ?= null
    var splashSlogan: TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        splashImage = findViewById(R.id.splash_image)
        splashText = findViewById(R.id.splash_text)
        splashSlogan = findViewById(R.id.slogan)

        splashImage?.animation = topAnimation
        splashText?.animation = bottomAnimation
        splashSlogan?.animation = bottomAnimation

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)

    }
}