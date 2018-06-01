package com.devmatic.fruits.data.api

import com.devmatic.fruits.data.models.Fruit
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FruitApiService {
    @Headers("User-Agent: Dalvik/2.1.0 (Linux; U; Android 7.1.2; Swift 2 Build/N2G47H)")
    @GET("/api/fruits")
    fun getFruits(): Observable<List<Fruit>>

    @Headers("User-Agent: Dalvik/2.1.0 (Linux; U; Android 7.1.2; Swift 2 Build/N2G47H)")
    @GET("/api/fruits/{id}")
    fun getFruit(@Path("id") fruitId: Int): Observable<Fruit>

}
