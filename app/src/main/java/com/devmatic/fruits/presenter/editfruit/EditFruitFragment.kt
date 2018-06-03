package com.devmatic.fruits.presenter.editfruit


import android.os.Bundle
import android.util.Log
import android.view.*
import com.devmatic.fruits.R
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.presenter.base.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_fruit_edit.*
import android.view.MenuInflater
import com.devmatic.fruits.data.interfaces.ConfirmDialogListener
import com.devmatic.fruits.data.utils.confirmDialog


class EditFruitFragment : BaseMvpFragment<EditFruitContract.View, EditFruitPresenter>(), EditFruitContract.View, View.OnClickListener {
    override fun setDelicious(b: Boolean) {
        chBoxDelicious.isChecked = b
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit_fruit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        val menuRemove = menu?.findItem(R.id.action_remove)
        menuRemove?.isVisible = arguments?.getLong("id") != null
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_remove -> {
                activity?.let {
                    confirmDialog(it, "Delete fruit?", dialogListener = object : ConfirmDialogListener {
                        override fun onConfirm() {
                            Log.d(EditFruitFragment::class.java.simpleName, "action_remove")
                            mPresenter.removeFruit()
                        }

                        override fun onCancel() {
                        }

                    })
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Edit fruit"
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
