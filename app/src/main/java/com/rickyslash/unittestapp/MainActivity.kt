package com.rickyslash.unittestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.rickyslash.unittestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mainViewModel = MainViewModel(CuboidModel())

        activityMainBinding.btnSave.setOnClickListener(this)
        activityMainBinding.btnCalculateSurfaceArea.setOnClickListener(this)
        activityMainBinding.btnCalculateCircumference.setOnClickListener(this)
        activityMainBinding.btnCalculateVolume.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val length = activityMainBinding.edtLength.text.toString().trim()
        val width = activityMainBinding.edtWidth.text.toString().trim()
        val height = activityMainBinding.edtHeight.text.toString().trim()
        when {
            TextUtils.isEmpty(length) -> {
                activityMainBinding.edtLength.error = "Field can't be empty"
            }
            TextUtils.isEmpty(width) -> {
                activityMainBinding.edtWidth.error = "Field can't be empty"
            }
            TextUtils.isEmpty(height) -> {
                activityMainBinding.edtHeight.error = "Field can't be empty"
            }
            else -> {
                val valueLength = length.toDouble()
                val valueWidth = width.toDouble()
                val valueHeight = height.toDouble()
                when (v.id) {
                    R.id.btn_save -> {
                        mainViewModel.save(valueLength, valueWidth, valueHeight)
                        visible()
                    }
                    R.id.btn_calculate_circumference -> {
                        activityMainBinding.tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                    R.id.btn_calculate_surface_area -> {
                        activityMainBinding.tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                    R.id.btn_calculate_volume -> {
                        activityMainBinding.tvResult.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        activityMainBinding.btnCalculateVolume.visibility = View.VISIBLE
        activityMainBinding.btnCalculateCircumference.visibility = View.VISIBLE
        activityMainBinding.btnCalculateSurfaceArea.visibility = View.VISIBLE
        activityMainBinding.btnSave.visibility = View.GONE
    }

    private fun gone() {
        activityMainBinding.btnCalculateVolume.visibility = View.GONE
        activityMainBinding.btnCalculateCircumference.visibility = View.GONE
        activityMainBinding.btnCalculateSurfaceArea.visibility = View.GONE
        activityMainBinding.btnSave.visibility = View.VISIBLE
    }
}

// Testing is a way to test an app, to know whether the app is easy to maintenance in the future

// Testing has 2 intention:
// - to get information about the app quality
// - to ensure the app is working as it's needed

// Zero defects: a term that describes that the product result meet the determined specification

// User Acceptence Criteria: list of specification that is expected from user, organized from it's importance level, also whether if it's 'nice to have'
// User Acceptance Testing: A document contains a list of User Acceptance Criteria item that is approved from developer & engineer

// Test Code: code to do testing process, ensuring the app is running as it's needed. There's 2 kind of Test Code:
// - Unit Test: a code that tests an individual component/unit from an app (either it's a class, method, or module)
// --- It works as isolating part of program and give validation whether that component runs well
// --- It's commonly written in: module-name/src/test/java
// --- It runs on local JVM & don't have acess to Android Framework API

// - UI Test: A mechanism where an app is going to be tested as the user interact with the app
// --- Example: making scenario that is going to be tested to the user (input data, press button, swipe screen etc)
// --- It's commonly written in: module-name/src/androidTest/Java
// --- It needs device/emulator and Android framework API / other API like Android Testing Support Library

// JUnit is the default library to Test in Android Project

// add function to Test case (Android Studio): SHIFT + CTRL + T
// @Test annotation let JUnit know that the function is a test case
// assertion like 'assertEquals' should be added inside the function test case, to assert the desired result & actual result
// --- assertEquals("Wed, 28 Feb 2018", Utils.toSimpleString(date))