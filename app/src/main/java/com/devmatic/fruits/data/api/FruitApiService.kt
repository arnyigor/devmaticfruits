package com.devmatic.fruits.data.api

import com.devmatic.fruits.data.models.Fruit
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface FruitApiService {
    @Headers("User-Agent: Dalvik/2.1.0 (Linux; U; Android 7.1.2; Swift 2 Build/N2G47H)")
    @GET("{url}")
    fun getFruits(@Path("url") url: String): Observable<MutableList<Fruit>>

    @Headers("User-Agent: Dalvik/2.1.0 (Linux; U; Android 7.1.2; Swift 2 Build/N2G47H)")
    @GET("fruits/{id}")
    fun getFruit(@Path("id") fruitId: Int): Observable<Fruit>

    @Headers("User-Agent: Dalvik/2.1.0 (Linux; U; Android 7.1.2; Swift 2 Build/N2G47H)")
    @POST("fruits")
    fun saveFruit(@Body body: RequestBody): Observable<Any>

}
