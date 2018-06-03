package com.devmatic.fruits.presenter.viewfruit

import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.data.utils.Utility
import com.devmatic.fruits.presenter.base.BaseMvpPresenterImpl


class ViewFruitPresenter(private val repository: FruitRepository) : BaseMvpPresenterImpl<ViewFruitContract.View>(), ViewFruitContract.Presenter {
    private var fruit: Fruit? = null

    override fun updateInfo() {
        val id = fruit?.id
        if (id != null) {
            Utility.mainThreadObservable(repository.loadFruit(id, true)
                    .doOnNext { this.fruit = it }
                    .map { repository.getFruitInfo(it) })
                    .subscribe({
                        if (it != null) {
                            mView?.showInfo(it)
                        } else {
                            mView?.toastError("No data")
                        }
                    }, {
                        mView?.toastError(it.message)
                    })
        } else {
            mView?.toastError("No data")
        }
    }

    override fun detachView() {
        super.detachView()
        repository.closeDb()
    }

    override fun loadFruit(id: Long?) {
        if (id != null) {
            Utility.mainThreadObservable(repository.loadFruit(id)
                    .doOnNext { this.fruit = it }
                    .map { repository.getFruitInfo(it) })
                    .subscribe({
                        if (it != null) {
                            mView?.showInfo(it)
                        } else {
                            mView?.toastError("No data")
                        }
                    }, {
                        mView?.toastError(it.message)
                    })
        } else {
            mView?.toastError("No data")
        }

    }
}