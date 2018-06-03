package com.devmatic.fruits.presenter.viewfruit


import android.os.Bundle
import android.view.*
import com.devmatic.fruits.R
import com.devmatic.fruits.data.source.main.FruitRepository
import com.devmatic.fruits.presenter.base.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_view_fruit.*

class ViewFruitFragment : BaseMvpFragment<ViewFruitContract.View, ViewFruitPresenter>(), ViewFruitContract.View {
    override val mPresenter = ViewFruitPresenter(FruitRepository())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_fruit, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_update -> {
                mPresenter.updateInfo()
                return true
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_view_fruit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "View fruit"
        val int = arguments?.getLong("id")
        mPresenter.loadFruit(int)
    }

    override fun showInfo(data: String) {
        tvFruitInfo.text = data
    }

}
