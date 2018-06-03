package com.devmatic.fruits.data.source.main

import android.content.Context
import com.devmatic.fruits.FruitApp
import com.devmatic.fruits.FruitApp.Companion.applicationComponent
import com.devmatic.fruits.data.api.FruitAPI
import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.base.BaseDataRepository
import com.devmatic.fruits.data.utils.DateTimeUtils
import io.reactivex.Observable
import io.realm.Realm


class FruitRepository : BaseDataRepository(), FruitRepositoryContract {

    init {
        FruitApp.applicationComponent.inject(this)
    }

    override fun uploadChanges(fruit: Fruit): Observable<Any> {
        return FruitAPI.apiSaveFruit(fruit)
    }

    override fun addFruit(name: String, color: String, weight: Double, delicious: Boolean): Observable<Fruit> {
        return Observable.just(1)
                .map {
                    Realm.getDefaultInstance().beginTransaction()
                    val fruit = Realm.getDefaultInstance().createObject(Fruit::class.java)
                    fruit.name = name
                    fruit.color = color
                    fruit.delicious = if (delicious) 1 else 0
                    fruit.weight = weight
                    fruit.createdAt = DateTimeUtils.getDateTime("yyyy-MM-dd HH:mm:ss")
                    fruit.updatedAt = DateTimeUtils.getDateTime("yyyy-MM-dd HH:mm:ss")
                    Realm.getDefaultInstance().commitTransaction()
                    fruit
                }

    }

    override fun updateFruit(fruit: Fruit, name: String, color: String, weight: Double, delicious: Boolean): Observable<Fruit> {
        return Observable.just { 1 }
                .map {
                    Realm.getDefaultInstance().beginTransaction()
                    val copyToRealm = Realm.getDefaultInstance().copyToRealm(fruit)
                    copyToRealm.name = name
                    copyToRealm.color = name
                    copyToRealm.weight = weight
                    copyToRealm.delicious = if (delicious) 1 else 0
                    copyToRealm.updatedAt = DateTimeUtils.getDateTime("yyyy-MM-dd HH:mm:ss")
                    Realm.getDefaultInstance().commitTransaction()
                    fruit
                }

    }

    override fun getFruitInfo(fruit: Fruit?): String? {
        if (fruit != null) {
            return "Title: ${fruit.name}\n" +
                    "Color: ${fruit.color}\n" +
                    "Weight: ${fruit.weight}"
        }
        return null
    }

    override fun loadFruit(id: Long): Observable<Fruit> {
        return getFruitObserv(id)
    }

    override fun closeDb() {
        Realm.getDefaultInstance().close()
    }

    override fun getContext(): Context? {
        return applicationComponent.getContext()
    }

    override fun loadFruits(useApi: Boolean): Observable<List<Fruit>>{
        val fromDb = Observable.fromCallable<MutableList<Fruit>?> {
            Realm.getDefaultInstance()
                    .copyFromRealm(Realm.getDefaultInstance()
                            .where(Fruit::class.java).findAll())
        }
                .map { it as List<Fruit> }
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

    private fun getFruitObserv(id: Long): Observable<Fruit> {
        return Observable.create<Fruit> {
            val all = Realm.getDefaultInstance().where(Fruit::class.java).equalTo("id", id).findFirst()
            if (all != null) {
                it.onNext(Realm.getDefaultInstance().copyFromRealm(all))
            }
            it.onComplete()
        }
    }

}
