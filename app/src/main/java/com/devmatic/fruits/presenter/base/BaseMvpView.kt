package com.devmatic.fruits.presenter.base


interface BaseMvpView {
    fun showError(error: String?)
    fun toastError(error: String?)
}
