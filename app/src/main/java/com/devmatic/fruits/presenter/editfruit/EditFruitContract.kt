package com.devmatic.fruits.presenter.editfruit

import com.devmatic.fruits.presenter.base.BaseMvpPresenter
import com.devmatic.fruits.presenter.base.BaseMvpView


object EditFruitContract {
    interface View : BaseMvpView {
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadFruit(id: Int)
    }
}