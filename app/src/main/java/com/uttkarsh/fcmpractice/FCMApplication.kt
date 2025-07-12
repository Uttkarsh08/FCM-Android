package com.uttkarsh.fcmpractice

import android.app.Application
import com.google.firebase.FirebaseApp

class FCMApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}