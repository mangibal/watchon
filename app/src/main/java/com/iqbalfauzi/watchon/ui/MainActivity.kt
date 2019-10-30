package com.iqbalfauzi.watchon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.iqbalfauzi.watchon.R
import com.iqbalfauzi.watchon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var databinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(databinding) {
            val navController = findNavController(R.id.nav_host_fragment)
            navView.setupWithNavController(navController)
        }
    }
}