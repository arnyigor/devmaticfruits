package com.devmatic.fruits.presenter.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast

abstract class BaseMvpFragment<in V : BaseMvpView, T : BaseMvpPresenter<V>> : Fragment(), BaseMvpView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this as V)
    }

    protected abstract val mPresenter: T


    override fun toastError(error: String?) {
        context?.let { Toast.makeText(context, error, Toast.LENGTH_LONG).show() }
    }

    override fun showError(error: String?) {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.detachView()
    }
}