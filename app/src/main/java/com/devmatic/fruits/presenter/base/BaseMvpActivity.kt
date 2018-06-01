package com.devmatic.fruits.presenter.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>> : AppCompatActivity(), BaseMvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    protected abstract val mPresenter: T

    override fun toastError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showError(error: String?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}