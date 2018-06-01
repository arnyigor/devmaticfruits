package com.devmatic.fruits.data.source.main

import com.devmatic.fruits.data.models.Fruit
import io.reactivex.Observable

interface FruitRepositoryContract {
    fun loadFruits(useApi: Boolean = false): Observable<List<Fruit>>
    fun closeDb()
}