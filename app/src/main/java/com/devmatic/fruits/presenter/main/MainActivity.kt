package com.devmatic.fruits.presenter.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.devmatic.fruits.R
import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.presenter.adapters.base.SimpleBindableAdapter
import com.devmatic.fruits.presenter.adapters.mainlist.FruitHolder
import com.devmatic.fruits.presenter.base.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainContract.View, MainPresenter>(), MainContract.View {
    override fun updateList(fruits: List<Fruit>) {
        adapter?.clear()
        adapter?.addAll(fruits)
    }

    override val mPresenter = MainPresenter(FruitRepository())

    private var adapter: SimpleBindableAdapter<Fruit, FruitHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvFruitList.layoutManager = LinearLayoutManager(this)
        adapter = SimpleBindableAdapter(this, R.layout.fruit_list_item, FruitHolder::class.java)
        rvFruitList.adapter = adapter
        mPresenter.loadFruits()
    }
}
