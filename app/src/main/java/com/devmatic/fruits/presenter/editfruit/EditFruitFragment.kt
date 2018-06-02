package com.devmatic.fruits.presenter.editfruit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devmatic.fruits.R
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.presenter.base.BaseMvpFragment


class EditFruitFragment : BaseMvpFragment<EditFruitContract.View, EditFruitPresenter>(), EditFruitContract.View {

    override val mPresenter = EditFruitPresenter(FruitRepository())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fruit_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
