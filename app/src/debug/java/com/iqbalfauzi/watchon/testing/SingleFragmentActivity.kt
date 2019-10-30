package com.iqbalfauzi.watchon.testing

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.iqbalfauzi.watchon.R

/**
 * Created by Iqbal Fauzi on 17:27 27/10/19
 */
class SingleFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val content = FrameLayout(this)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT,
            Gravity.CENTER)
        content.layoutParams = params
        content.id = R.id.frame
        setContentView(content)
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.commitNow(allowStateLoss = true) {
            replace(R.id.frame, fragment, "TEST")
        }
    }

    /*fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commitNow(allowStateLoss = true) {
            replace(R.id.frame, fragment, "TEST")
        }
    }*/

}