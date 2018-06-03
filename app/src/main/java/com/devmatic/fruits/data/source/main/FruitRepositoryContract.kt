package com.devmatic.fruits.data.source.main

import com.devmatic.fruits.data.models.Fruit
import io.reactivex.Observable

interface FruitRepositoryContract {
    fun loadFruits(useApi: Boolean = false): Observable<List<Fruit>>
    fun closeDb()
    fun loadFruit(id: Long): Observable<Fruit?>
    fun getFruitInfo(fruit: Fruit?): String?
    fun addFruit(name: String, color: String, weight: Double, delicious: Boolean): Observable<Fruit>
    fun updateFruit(fruit: Fruit, name: String, color: String, weight: Double, delicious: Boolean): Observable<Fruit>
    fun uploadChanges(fruit: Fruit): Observable<Any>
    fun removeFruit(id: Long?): Observable<Boolean>?
    fun loadFruit(id: Long, useApi: Boolean): Observable<Fruit>
}