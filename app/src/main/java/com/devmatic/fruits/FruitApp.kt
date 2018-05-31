package com.devmatic.fruits

import android.app.Application
import com.devmatic.fruits.di.components.ApplicationComponent
import com.devmatic.fruits.di.components.DaggerApplicationComponent
import com.devmatic.fruits.di.modules.AndroidModule

class FruitApp:Application(){

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
        applicationComponent.inject(this)
    }

    companion object {
        @JvmStatic lateinit var applicationComponent: ApplicationComponent
    }
}