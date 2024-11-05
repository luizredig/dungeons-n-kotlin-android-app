package com.dnk.app.data

import android.app.Application

class AppApplication : Application() {
    lateinit var db: DBConnection

    override fun onCreate() {
        super.onCreate()
        db = DBConnection.getDatabase(this)
    }
}