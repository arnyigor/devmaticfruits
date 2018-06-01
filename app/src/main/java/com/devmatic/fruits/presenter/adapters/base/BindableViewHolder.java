package com.devmatic.fruits.presenter.adapters.base;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
public abstract class BindableViewHolder<T> extends RecyclerView.ViewHolder {

    protected int position;
    protected Context context;

	public BindableViewHolder(View itemView) {
        super(itemView);
    }

    //в этом методе будет происходить обработка данных, сейчас вешается OnItemClickListener
    public void bindView(Context context, final int position, T item, final ActionListener actionListener) {
	    this.context = context;
	    if (actionListener != null) {
            itemView.setOnClickListener(v -> actionListener.onHolderItemClick(position, item));
        }
    }

    //это интерфейс который мы будем в дальнейшем расширять
    public interface ActionListener {
        void onHolderItemClick(int position, Object item);
    }
}