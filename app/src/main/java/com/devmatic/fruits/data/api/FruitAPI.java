package com.devmatic.fruits.data.api;

import android.content.Context;
import com.devmatic.fruits.data.models.Fruit;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class FruitAPI {
	public static final String BASE_URL = "https://next.json-generator.com/api/json/get/";
	public static final String BASE_URL1 = "http://fruits.dev/api/";

	public static Observable<List<Fruit>> getFruits(Context context) {
		return ApiFactory.getInstance().createService(FruitApiService.class, BASE_URL, 15, HttpLoggingInterceptor.Level.BODY).getFruits("41IFZNHuX");
	}

	public static Observable<Fruit> getFruit(Context context, int id) {
		return ApiFactory.getInstance().createService(FruitApiService.class, "", 15, HttpLoggingInterceptor.Level.BODY).getFruit(id);
	}

	public static Observable<Object> apiSaveFruit(Fruit fruit) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", fruit.getName());
		map.put("color", fruit.getColor());
		map.put("weight", fruit.getWeight());
		map.put("delicious", fruit.getDelicious());
		RequestBody body = ApiUtilsKt.getRequestBody(map);
		return ApiFactory.getInstance()
				.createService(FruitApiService.class, BASE_URL1, 30, HttpLoggingInterceptor.Level.HEADERS)
				.saveFruit(body);
	}

}
