package com.devmatic.fruits.presenter.editfruit

import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.data.utils.Utility
import com.devmatic.fruits.presenter.base.BaseMvpPresenterImpl


class EditFruitPresenter(private val repository: FruitRepository) : BaseMvpPresenterImpl<EditFruitContract.View>(), EditFruitContract.Presenter {
    private var fruit: Fruit? = null

    override fun detachView() {
        super.detachView()
        repository.closeDb()
    }

    override fun saveFruit(name: String, color: String, weight: String, delicious: Boolean) {
        var w = 0.0
        try {
            w = weight.toDouble()
        } catch (e: Exception) {
            mView?.toastError("Wrong parameter weight")
            return
        }
        if (fruit == null) {
            Utility.mainThreadObservable(repository.addFruit(name, color, w, delicious))
        } else {
            Utility.mainThreadObservable(repository.updateFruit(fruit!!, name, color, w, delicious))
        }
        mView?.backToList()
    }

    override fun loadFruit(id: Long?, edit: Boolean?) {
        fruit = null
        if (id != null && edit == true) {
            Utility.mainThreadObservable(repository.loadFruit(id)
            ).subscribe({
                fruit = it
                if (it != null) {
                    mView?.setFruitName(it.name)
                    mView?.setFruitColor(it.color)
                    mView?.setFruitWeight(it.weight.toString())
                    mView?.setBtnTitle("Save")
                } else {
                    mView?.toastError("No data for Fruit with id #$id")
                }
            }, {
                mView?.showError(it.message)
            })
        } else {
            mView?.setBtnTitle("Add")
            mView?.setTitle("Add fruit")
        }
    }
}