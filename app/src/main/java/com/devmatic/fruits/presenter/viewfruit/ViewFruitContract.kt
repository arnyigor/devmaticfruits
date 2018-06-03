package com.devmatic.fruits.presenter.viewfruit

import com.devmatic.fruits.presenter.base.BaseMvpPresenter
import com.devmatic.fruits.presenter.base.BaseMvpView


object ViewFruitContract {
    interface View : BaseMvpView {
        fun showInfo(data: String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadFruit(id: Long?)
        fun updateInfo()
    }
}