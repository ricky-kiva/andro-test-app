package com.rickyslash.unittestapp

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var cuboidModel: CuboidModel

    private val dumLength = 12.0
    private val dumWidth = 7.0
    private val dumHeight = 6.0

    private val dumVolume = 504.0
    private val dumCircumference = 100.0
    private val dumSurfaceArea = 396.0

    @Before
    fun before() {
        // 'mocking' CuboidModel using 'mockito'
        cuboidModel = mock(CuboidModel::class.java)
        mainViewModel = MainViewModel(cuboidModel)
    }

    @Test
    fun getCircumference() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dumWidth, dumLength, dumHeight)
        val circumference = mainViewModel.getCircumference()
        assertEquals(dumCircumference, circumference, 0.0001)
    }

    @Test
    fun getSurfaceArea() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dumWidth, dumLength, dumHeight)
        val surfaceArea = mainViewModel.getSurfaceArea()
        assertEquals(dumSurfaceArea, surfaceArea, 0.0001)
    }

    @Test
    fun getVolume() {

        // try using test using 'mockito'
        // this 'mockito' method used to know the interaction between 'getVolume()' method & 'cuboidModel' object

        // this will 'set' 'return value' for 'mainViewModel.getVolume()' to 'dumVolume'
        `when`(mainViewModel.getVolume()).thenReturn(dumVolume)
        // this will call the 'getVolume' from 'mainViewModel'
        val volume = mainViewModel.getVolume()
        //  this ensure 'cuboidModel.getVolume' is called 'once' in 'this scope'
        verify(cuboidModel).getVolume()
    }

    @Test
    fun save() {
    }
}