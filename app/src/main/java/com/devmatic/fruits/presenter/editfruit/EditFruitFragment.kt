package com.devmatic.fruits.presenter.editfruit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devmatic.fruits.R
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.presenter.base.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_fruit_edit.*


class EditFruitFragment : BaseMvpFragment<EditFruitContract.View, EditFruitPresenter>(), EditFruitContract.View, View.OnClickListener {
    override fun backToList() {
        activity?.onBackPressed()
    }

    override fun setBtnTitle(s: String) {
        btnEdit.text = s
    }

    override fun setTitle(s: String) {
        activity?.title = s
    }

    override fun setFruitName(name: String?) {
        tilEdtName.setText(name)
    }

    override fun setFruitColor(color: String?) {
        tilEdtColor.setText(color)
    }

    override fun setFruitWeight(weight: String) {
        tilEdtWeight.setText(weight)
    }

    override val mPresenter = EditFruitPresenter(FruitRepository())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fruit_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnEdit.setOnClickListener(this)
        val id = arguments?.getLong("id")
        val edit = arguments?.getBoolean("edit")
        mPresenter.loadFruit(id, edit)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnEdit -> {
                mPresenter.saveFruit(tilEdtName.text.toString(),
                        tilEdtColor.text.toString(),
                        tilEdtWeight.text.toString(),
                        chBoxDelicious.isChecked)
            }
        }
    }


}
