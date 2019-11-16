package com.iqbalfauzi.watchon.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.snackbar.Snackbar
import com.iqbalfauzi.watchon.R

/**
 * Created by Iqbal Fauzi on 10:36 16/10/19
 */

fun Activity.goToActivity(targetClass: Class<*>, bundle: Bundle?, isFinish: Boolean) {
    val intent = Intent(this, targetClass)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    startActivity(intent)
    if (isFinish) {
        this.finish()
    }
}

fun Activity.goToActivityClearAllStack(c: Class<*>, bundle: Bundle?) {
    val i = Intent(this, c)
    if (bundle != null) {
        i.putExtras(bundle)
    }
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(i)
    finish()
}

fun Context.toastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.snackBar(message: String, type: Int) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    val sbView = snackbar.view
    val text = sbView.findViewById<View>(R.id.snackbar_text) as TextView
    when (type) {
        0 -> {
            snackbar.show()
        }
        1 -> {
            text.setTextColor(Color.BLACK)
            sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
            snackbar.show()
        }
        2 -> {
            text.setTextColor(Color.WHITE)
            sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            snackbar.show()
        }
    }
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}