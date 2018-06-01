package com.devmatic.fruits.presenter.main

import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.presenter.base.BaseMvpPresenter
import com.devmatic.fruits.presenter.base.BaseMvpView


object MainContract {
    interface View : BaseMvpView {
        fun updateList(fruits: List<Fruit>)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadFruits()
    }
}