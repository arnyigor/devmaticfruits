package com.devmatic.fruits.data.source.main

import android.content.Context
import android.util.Log
import com.devmatic.fruits.FruitApp
import com.devmatic.fruits.FruitApp.Companion.applicationComponent
import com.devmatic.fruits.data.api.FruitAPI
import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.base.BaseDataRepository
import com.devmatic.fruits.data.utils.DateTimeUtils
import io.reactivex.Observable
import io.realm.Realm


class FruitRepository : BaseDataRepository(), FruitRepositoryContract {
    override fun removeFruit(id: Long?): Observable<Boolean>? {
        Log.d(FruitRepository::class.java.simpleName, "FruitRepository removeFruit")
        return Observable.fromCallable {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val fruit = realm.where(Fruit::class.java).equalTo("id", id).findFirst()
            fruit?.deleteFromRealm()
            realm.commitTransaction()
            fruit?.isValid == false
        }

    }

    init {
        FruitApp.applicationComponent.inject(this)
    }

    override fun uploadChanges(fruit: Fruit): Observable<Any> {
        return FruitAPI.apiSaveFruit(fruit)
    }

    override fun addFruit(name: String, color: String, weight: Double, delicious: Boolean): Observable<Fruit> {
        return Observable.just(1)
                .map {
                    val realm = Realm.getDefaultInstance()
                    realm.beginTransaction()
                    val maxId = realm.where(Fruit::class.java).max("id")
                    val nextId = if (maxId == null) 1 else maxId.toLong() + 1
                    val fruit = realm.createObject(Fruit::class.java, nextId)
                    fruit.name = name
                    fruit.color = color
                    fruit.delicious = if (delicious) 1 else 0
                    fruit.weight = weight
                    fruit.createdAt = DateTimeUtils.getDateTime(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")
                    fruit.updatedAt = DateTimeUtils.getDateTime(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")
                    realm.commitTransaction()
                    fruit
                }
    }

    override fun updateFruit(fruit: Fruit, name: String, color: String, weight: Double, delicious: Boolean): Observable<Fruit> {
        return Observable.just { 1 }
                .map {
                    val realm = Realm.getDefaultInstance()
                    val dbFruit = Realm.getDefaultInstance().where(Fruit::class.java).equalTo("id", fruit.id).findFirst()
                    realm.beginTransaction()
                    val fruit1: Fruit
                    if (dbFruit == null) {
                        val created = realm.createObject(Fruit::class.java)
                        created.name = name
                        created.color = color
                        created.weight = weight
                        created.delicious = if (delicious) 1 else 0
                        created.updatedAt = DateTimeUtils.getDateTime(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")
                        fruit1 = realm.copyFromRealm(created)
                    } else {
                        dbFruit.name = name
                        dbFruit.color = color
                        dbFruit.weight = weight
                        dbFruit.delicious = if (delicious) 1 else 0
                        dbFruit.updatedAt = DateTimeUtils.getDateTime(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")
                        fruit1 = realm.copyFromRealm(dbFruit)
                    }
                    realm.commitTransaction()
                    fruit1
                }

    }

    override fun getFruitInfo(fruit: Fruit?): String? {
        if (fruit != null) {
            return "Title: ${fruit.name}\n" +
                    "Color: ${fruit.color}\n" +
                    "Date: ${DateTimeUtils.getDateTime(fruit.updatedAt, "yyyy-MM-dd HH:mm:ss")}\n" +
                    "Weight: ${fruit.weight}"
        }
        return null
    }

    override fun loadFruit(id: Long): Observable<Fruit?> {
        return Observable.fromCallable {
            val realm = Realm.getDefaultInstance()
            realm.copyFromRealm(realm.where(Fruit::class.java).equalTo("id", id).findFirst())
        }
    }

    override fun closeDb() {
        Realm.getDefaultInstance().close()
    }

    override fun getContext(): Context? {
        return applicationComponent.getContext()
    }

    override fun loadFruits(useApi: Boolean): Observable<List<Fruit>> {
        val fromDb = Observable.fromCallable<List<Fruit>> {
            val all = Realm.getDefaultInstance().where(Fruit::class.java).findAll()
            Realm.getDefaultInstance().copyFromRealm(all)
        }
        if (useApi) {
            return FruitAPI.getFruits(getContext())
                    .map {
                        val realm = Realm.getDefaultInstance()
                        realm.beginTransaction()
                        realm.copyToRealmOrUpdate(it)
                        realm.commitTransaction()
                        it
                    }.flatMap { fromDb }
        }
        return fromDb
    }

    override fun loadFruit(id: Long, useApi: Boolean): Observable<Fruit> {
        val fromDb = Observable.fromCallable<Fruit> {
            val realm = Realm.getDefaultInstance()
             realm.copyFromRealm(realm.where(Fruit::class.java).equalTo("id",id).findFirst())
        }
        if (useApi) {
            return FruitAPI.getFruit(id)
                    .map {
                        val realm = Realm.getDefaultInstance()
                        realm.beginTransaction()
                        realm.copyToRealmOrUpdate(it)
                        realm.commitTransaction()
                        it
                    }.flatMap { fromDb }
        }
        return fromDb
    }

}
