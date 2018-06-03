package com.devmatic.fruits.presenter.adapters.mainlist

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.devmatic.fruits.R
import com.devmatic.fruits.data.models.Fruit
import com.devmatic.fruits.presenter.adapters.base.BindableViewHolder

class FruitHolder(itemView: View) : BindableViewHolder<Fruit>(itemView), View.OnClickListener {

    private var tvTitle: TextView? = null
    private var tvColor: TextView? = null
    private var tvWeight: TextView? = null
    private var ivEdit: ImageView? = null
    private var fruitAdapterActionListener: FruitAdapterActionListener? = null

    @SuppressLint("SetTextI18n")
    override fun bindView(context: Context, position: Int, item: Fruit, actionListener: BindableViewHolder.ActionListener) {
        super.bindView(context, position, item, actionListener)
        fruitAdapterActionListener = actionListener as FruitAdapterActionListener
        tvTitle = itemView.findViewById(R.id.tvFruitName)
        tvColor = itemView.findViewById(R.id.tvFruitColor)
        tvWeight = itemView.findViewById(R.id.tvFruitWeight)
        ivEdit = itemView.findViewById(R.id.ivEdit)
        tvTitle?.text = "${item.name} #${item.id}"
        tvColor?.text = item.color
        tvWeight?.text = item.weight.toString()
        ivEdit?.setOnClickListener { fruitAdapterActionListener?.onItemEdit(item) }
    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    interface FruitAdapterActionListener : BindableViewHolder.ActionListener {
        fun onItemEdit(item: Fruit)
    }
}