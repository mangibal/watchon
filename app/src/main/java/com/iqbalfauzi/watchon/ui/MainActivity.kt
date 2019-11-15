package com.iqbalfauzi.watchon.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.databinding.ActivityMainBinding
import com.iqbalfauzi.watchon.ui.movie.MovieFragment
import com.iqbalfauzi.watchon.ui.tv.TvFragment
import com.iqbalfauzi.watchon.utils.FakeJson

class MainActivity : AppCompatActivity() {

    private lateinit var databinding: ActivityMainBinding
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_movie -> setFragment(MovieFragment())
            R.id.navigation_tv -> setFragment(TvFragment())
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(databinding) {
            setFragment(MovieFragment())
            navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commitNow(allowStateLoss = true) {
            replace(R.id.frame, fragment, fragment.javaClass.name)
        }
    }
}