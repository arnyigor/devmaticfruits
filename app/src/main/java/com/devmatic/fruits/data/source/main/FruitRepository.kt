package com.devmatic.fruits.data.source.main

import android.content.Context
import com.devmatic.fruits.FruitApp
import com.devmatic.fruits.FruitApp.Companion.applicationComponent
import com.devmatic.fruits.data.source.base.BaseDataRepository

class FruitRepository : BaseDataRepository() {

    init {
        FruitApp.applicationComponent.inject(this)
    }

    override fun getContext(): Context? {
        return applicationComponent.getContext()
    }

}