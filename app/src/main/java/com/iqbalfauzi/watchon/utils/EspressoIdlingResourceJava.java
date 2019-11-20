package com.iqbalfauzi.watchon.utils;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

/**
 * Created by Iqbal Fauzi on 16:27 20/11/19
 */
public class EspressoIdlingResourceJava {
    private static final String RESOURCE = "GLOBAL";
    private static CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource(RESOURCE);

    public static void increment() {
        espressoTestIdlingResource.increment();
    }

    public static void decrement() {
        espressoTestIdlingResource.decrement();
    }

    public static IdlingResource getEspressoIdlingResource() {
        return espressoTestIdlingResource;
    }
}
