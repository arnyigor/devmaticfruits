package com.devmatic.fruits.presenter.editfruit

import com.devmatic.fruits.presenter.base.BaseMvpPresenter
import com.devmatic.fruits.presenter.base.BaseMvpView


object EditFruitContract {
    interface View : BaseMvpView {
        fun setTitle(s: String)
        fun setFruitName(name: String?)
        fun setFruitColor(color: String?)
        fun setFruitWeight(weight: String)
        fun setBtnTitle(s: String)
        fun backToList()
        fun setDelicious(b: Boolean)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadFruit(id: Long?, edit: Boolean?)
        fun saveFruit(name: String, color: String, weight: String, delicious: Boolean)
        fun removeFruit()
    }
}