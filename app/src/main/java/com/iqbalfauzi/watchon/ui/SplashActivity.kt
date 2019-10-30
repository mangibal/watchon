package com.iqbalfauzi.watchon.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import androidx.core.text.toSpannable
import androidx.databinding.DataBindingUtil
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.databinding.ActivitySplashBinding
import com.iqbalfauzi.watchon.utils.goToActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        checkScreenType()
        setAnimation()
        Handler().postDelayed({
            this.goToActivity(MainActivity::class.java, null, true)
        }, 2000)

        val s = "Iqbal Fauzi".toSpannable()
    }

    private fun setAnimation() {
        with(dataBinding) {
            val fadeIn = AlphaAnimation(0f, 1f)
            fadeIn.interpolator = DecelerateInterpolator()
            fadeIn.duration = 1000

            val animation = AnimationSet(false)
            animation.addAnimation(fadeIn)
            tvSplash.animation = animation
        }
    }

    private fun checkScreenType() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val attrib = window.attributes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            attrib.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }
}
