package com.devmatic.fruits.di.components

import android.content.Context
import com.devmatic.fruits.FruitApp
import dagger.Component
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.di.modules.AndroidModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidModule::class)])
interface ApplicationComponent {
    fun inject(target: FruitRepository)
    fun inject(target: FruitApp)
    fun getContext(): Context
}