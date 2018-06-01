package com.devmatic.fruits.presenter.adapters.base;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
public final class SimpleBindableAdapter<T, VH extends BindableViewHolder> extends RecyclerBindableAdapter<T, VH> {
	@LayoutRes
	private final int layoutId;

	Class<VH> vhClass;
	BindableViewHolder.ActionListener actionListener;

	public SimpleBindableAdapter(Context context, @LayoutRes int layoutId, Class<VH> vhClass) {
		this.context = context;
		this.layoutId = layoutId;
		this.vhClass = vhClass;
	}

	@Override
	protected void onBindItemViewHolder(BindableViewHolder viewHolder, int position, int type) {
		//вставляем данные во ViewHolder, ради этого метода мы и создавали BindableViewHolder
		viewHolder.bindView(context,position, getItem(position), actionListener);
	}

	@Override
	protected VH viewHolder(View view, int type) {
		//через Java Reflection создаем новый экземпляр ViewHolder
		try {
			return (VH) vhClass.getConstructor(View.class).newInstance(view);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

	@Override
	protected int layoutId(int type) {
		return layoutId;
	}

	public void setActionListener(BindableViewHolder.ActionListener actionListener) {
		this.actionListener = actionListener;
	}
}
