package com.devmatic.fruits.presenter.main

import android.os.Bundle
import com.devmatic.fruits.R
import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.presenter.adapters.base.SimpleBindableAdapter
import com.devmatic.fruits.presenter.adapters.mainlist.FruitHolder
import com.devmatic.fruits.presenter.base.BaseMvpActivity

class MainActivity : BaseMvpActivity<MainContract.View, MainPresenter>(), MainContract.View {
    override val mPresenter = MainPresenter(FruitRepository())

    private var adapter: SimpleBindableAdapter<Fruit, FruitHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.loadFruits()
    }
}
