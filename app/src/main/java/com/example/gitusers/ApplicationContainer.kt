package com.example.gitusers

import android.app.Application
import com.example.gitusers.data.AppContainer
import com.example.gitusers.data.DefaultAppContainer

class ApplicationContainer : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}