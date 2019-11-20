package com.iqbalfauzi.watchon.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Created by Iqbal Fauzi on 18:54 02/11/19
 */
object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    val espressoIdlingResource: IdlingResource
        get() = espressoTestIdlingResource

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }
}