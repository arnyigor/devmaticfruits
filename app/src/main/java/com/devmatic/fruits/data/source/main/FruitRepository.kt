package com.devmatic.fruits.data.source.main

import android.content.Context
import com.devmatic.fruits.FruitApp
import com.devmatic.fruits.FruitApp.Companion.applicationComponent
import com.devmatic.fruits.data.api.FruitAPI
import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.base.BaseDataRepository
import io.reactivex.Observable
import io.realm.Realm


class FruitRepository : BaseDataRepository(), FruitRepositoryContract {
    override fun getFruitInfo(fruit: Fruit?): String? {
        if (fruit != null) {
            return "Title: ${fruit.name}\n" +
                    "Color: ${fruit.color}\n" +
                    "Weight: ${fruit.weight}"
        }
        return null
    }

    override fun loadFruit(id: Int): Observable<Fruit?> {
        val findFirst = realmDB.where(Fruit::class.java).equalTo("id", id).findFirst()
        return Observable.fromCallable { realmDB.copyFromRealm(findFirst) }
    }

    private val realmDB: Realm = Realm.getDefaultInstance()

    override fun closeDb() {
        realmDB.close()
    }

    init {
        FruitApp.applicationComponent.inject(this)

    }

    override fun getContext(): Context? {
        return applicationComponent.getContext()
    }

    override fun loadFruits(useApi: Boolean): Observable<List<Fruit>> {
        val fromDb = Observable.fromCallable { realmDB.copyFromRealm(realmDB.where(Fruit::class.java).findAll()) }
        if (useApi) {
            return FruitAPI.getFruits(getContext())
                    .map {
                        for (fruit in it) {
                            realmDB.beginTransaction()
                            realmDB.copyToRealm(fruit)
                            realmDB.createObject(Fruit::class.java)
                            realmDB.commitTransaction()
                        }
                        it
                    }.flatMap { fromDb }
        }
        return fromDb
    }

}
