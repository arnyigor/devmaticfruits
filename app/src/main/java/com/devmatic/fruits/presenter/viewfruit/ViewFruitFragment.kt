package com.devmatic.fruits.presenter.viewfruit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devmatic.fruits.R
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.presenter.base.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_view_fruit.*

class ViewFruitFragment : BaseMvpFragment<ViewFruitContract.View, ViewFruitPresenter>(), ViewFruitContract.View {
    override val mPresenter = ViewFruitPresenter(FruitRepository())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_fruit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val int = arguments?.getLong("id")
        mPresenter.loadFruit(int)
    }

    override fun showInfo(data: String) {
        tvFruitInfo.text = data
    }

}
