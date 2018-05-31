package com.devmatic.fruits.data.source.base

import android.content.Context
import android.support.annotation.StringRes

interface BaseDataContract {
    fun getContext(): Context?
    fun setPrefString(key: String?, value: String?)
    fun getString(@StringRes res: Int): String?
    fun isConnected(): Boolean
    fun getPrefString(key: String, default: String? = null): String?
    fun getPrefInt(key: String): Int?
    fun setPrefBoolean(key: String, value: Boolean)
    fun setPrefInt(key: String, value: Int)
    fun getPrefBoolean(key: String, default: Boolean): Boolean
    fun removePref(key: String)
}