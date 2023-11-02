package com.sagar.nirfpredictor

import android.app.Application
import com.google.firebase.FirebaseApp
import com.sagar.nirfpredictor.model.AppContainer
import com.sagar.nirfpredictor.model.DefaultAppContainer

class MainApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        FirebaseApp.initializeApp(this)
    }
}