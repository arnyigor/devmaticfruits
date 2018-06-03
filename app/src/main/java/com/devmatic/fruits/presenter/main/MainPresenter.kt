package com.devmatic.fruits.presenter.main

import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.data.utils.Utility
import com.devmatic.fruits.presenter.base.BaseMvpPresenterImpl
import io.reactivex.disposables.CompositeDisposable


class MainPresenter(private val repository: FruitRepository) : BaseMvpPresenterImpl<MainContract.View>(), MainContract.Presenter {
    override fun cancelAll() {
        disposable.clear()
        mView?.stopRefresh()
    }

    private val disposable = CompositeDisposable()
    private var list = arrayListOf<Fruit>()
    override fun detachView() {
        super.detachView()
        repository.closeDb()

    }

    override fun loadFruits(b: Boolean) {
        disposable.add(Utility.mainThreadObservable(repository.loadFruits(b))
                .subscribe({
                    mView?.stopRefresh()
                    list = it as ArrayList<Fruit>
                    if (list.isEmpty()) {
                        mView?.viewNoData(true)
                        mView?.viewList(false)
                    } else {
                        mView?.updateList(list)
                        mView?.viewNoData(false)
                        mView?.viewList(true)
                    }
                }, {
                    mView?.stopRefresh()
                    it.printStackTrace()
                    mView?.toastError("Ошибка получения списка:${it.message}")
                })
        )
    }

}