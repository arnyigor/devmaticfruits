package com.devmatic.fruits.presenter.viewfruit

import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.data.utils.Utility
import com.devmatic.fruits.presenter.base.BaseMvpPresenterImpl


class ViewFruitPresenter(private val repository: FruitRepository) : BaseMvpPresenterImpl<ViewFruitContract.View>(), ViewFruitContract.Presenter {
    override fun loadFruit(id: Int?) {
        if (id != null) {
            Utility.mainThreadObservable(repository.loadFruit(id)
                    .map { repository.getFruitInfo(it) })
                    .subscribe({
                        if (it != null) {
                            mView?.showInfo(it)
                        } else {
                            mView?.showError("No data")
                        }
                    }, {
                        mView?.showError(it.message)
                    })
        } else {
            mView?.showError("No data")
        }

    }
}