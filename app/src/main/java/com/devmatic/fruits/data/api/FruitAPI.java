package com.devmatic.fruits.data.api;

import android.content.Context;
import com.devmatic.fruits.data.models.Fruit;
import io.reactivex.Observable;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.List;
public class FruitAPI {
	public static final String BASE_URL = "https://next.json-generator.com/api/json/get/";

    public static Observable<List<Fruit>> getFruits(Context context) {
        return ApiFactory.getInstance().createService(FruitApiService.class, BASE_URL, 15, HttpLoggingInterceptor.Level.BODY).getFruits("41IFZNHuX");
    }

    public static Observable<Fruit> getFruit(Context context, int id) {
        return ApiFactory.getInstance().createService(FruitApiService.class, "", 15, HttpLoggingInterceptor.Level.BODY).getFruit(id);
    }

}
