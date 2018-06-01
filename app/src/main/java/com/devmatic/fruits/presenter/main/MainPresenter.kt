package com.devmatic.fruits.presenter.main

import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.data.utils.Utility
import com.devmatic.fruits.presenter.base.BaseMvpPresenterImpl


class MainPresenter(private val repository: FruitRepository) : BaseMvpPresenterImpl<MainContract.View>(), MainContract.Presenter {
    private var list = arrayListOf<Fruit>()
    override fun loadFruits() {
        Utility.mainThreadObservable(repository.loadFruits())
                .subscribe({
                    list = it as ArrayList<Fruit>
                    mView?.updateList(it)
                }, {
                    mView?.toastError("Ошибка получения списка:${it.message}")
                })
    }

}