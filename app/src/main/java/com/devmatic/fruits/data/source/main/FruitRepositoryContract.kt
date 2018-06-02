package com.devmatic.fruits.data.source.main

import com.devmatic.fruits.data.models.Fruit
import io.reactivex.Observable

interface FruitRepositoryContract {
    fun loadFruits(useApi: Boolean = false): Observable<List<Fruit>>
    fun closeDb()
    fun loadFruit(id: Int): Observable<Fruit?>
    fun getFruitInfo(fruit: Fruit?): String?
}