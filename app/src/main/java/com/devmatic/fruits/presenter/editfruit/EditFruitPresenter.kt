package com.devmatic.fruits.presenter.editfruit

import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.data.utils.Utility
import com.devmatic.fruits.presenter.base.BaseMvpPresenterImpl


class EditFruitPresenter(private val repository: FruitRepository) : BaseMvpPresenterImpl<EditFruitContract.View>(), EditFruitContract.Presenter {
    override fun loadFruit(id: Int) {
        Utility.mainThreadObservable(repository.loadFruit(id)
        ).subscribe({
        }, {
            mView?.showError(it.message)
        })
    }
}