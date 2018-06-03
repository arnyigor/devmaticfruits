package com.devmatic.fruits.presenter.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.devmatic.fruits.R
import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.presenter.adapters.base.SimpleBindableAdapter
import com.devmatic.fruits.presenter.adapters.mainlist.FruitHolder
import com.devmatic.fruits.presenter.base.BaseMvpActivity
import com.devmatic.fruits.presenter.fruiteditor.ViewEditActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainContract.View, MainPresenter>(), MainContract.View, FruitHolder.FruitAdapterActionListener, View.OnClickListener {
    override fun onItemEdit(item: Fruit) {
        val fruit = item as Fruit
        val i = Intent(this, ViewEditActivity::class.java)
        i.putExtra("id", fruit.id)
        i.putExtra("edit", true)
        startActivity(i)
    }

    override fun stopRefresh() {
        refresh.isRefreshing = false
    }

    override fun viewList(vis: Boolean) {
        rvFruitList.visibility = if (vis) View.VISIBLE else View.GONE
    }

    override fun viewNoData(vis: Boolean) {
        tvNoDataInfo.visibility = if (vis) View.VISIBLE else View.GONE
    }

    override val mPresenter = MainPresenter(FruitRepository())

    private var adapter: SimpleBindableAdapter<Fruit, FruitHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvFruitList.layoutManager = LinearLayoutManager(this)
        adapter = SimpleBindableAdapter(this, R.layout.fruit_list_item, FruitHolder::class.java)
        rvFruitList.adapter = adapter
        adapter?.setActionListener(this)
        fab.setOnClickListener(this)
        refresh.setOnRefreshListener { mPresenter.loadFruits(true) }
        mPresenter.loadFruits(false)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {
                startActivity(Intent(this, ViewEditActivity::class.java))
            }
        }
    }

    override fun onHolderItemClick(position: Int, item: Any?) {
        val fruit = item as Fruit
        val i = Intent(this, ViewEditActivity::class.java)
        i.putExtra("id", fruit.id)
        startActivity(i)
    }

    override fun updateList(fruits: List<Fruit>) {
        adapter?.clear()
        adapter?.addAll(fruits)
        refresh.isRefreshing = false
    }
}
