package com.iqbalfauzi.watchon.ui.tv

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Iqbal Fauzi on 10:19 24/10/19
 */
class TvViewModelTest {

    private lateinit var tvViewModel: TvViewModel

    @Before
    fun setUp() {
        tvViewModel = TvViewModel()
    }

    @Test
    fun getTv() {
        val tvData = tvViewModel.getTvData()
        assertNotNull(tvData)
        assertEquals(10, tvData.size)
    }

}