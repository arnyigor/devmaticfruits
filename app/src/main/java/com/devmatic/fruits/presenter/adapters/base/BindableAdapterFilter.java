package com.devmatic.fruits.presenter.adapters.base;

public interface BindableAdapterFilter<T> {
    boolean onFilterItem(CharSequence constraint, T item);
}