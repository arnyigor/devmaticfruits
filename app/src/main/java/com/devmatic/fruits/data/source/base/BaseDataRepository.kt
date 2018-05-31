package com.devmatic.fruits.data.source.base

import com.devmatic.fruits.data.utils.Config
import com.devmatic.fruits.data.utils.Utility

abstract class BaseDataRepository: BaseDataContract {

    override fun getPrefInt(key: String): Int? {
        return getContext().let { Config.getInt(key, it) }
    }

    override fun getPrefString(key: String, default: String?): String? {
        if (default != null) {
            return getContext().let { Config.getString(key, it, default) }
        }
        return getContext().let { Config.getString(key, it) }
    }

    override fun setPrefBoolean(key: String, value: Boolean) {
        getContext().let { Config.setBoolean(key, value, it) }
    }

    override fun setPrefInt(key: String, value: Int) {
        getContext().let { Config.setInt(key, value, it) }
    }

    override fun getPrefBoolean(key: String, default: Boolean): Boolean {
        return getContext().let { Config.getBoolean(key, default, it) }
    }

    override fun removePref(key: String) {
        return getContext().let { Config.remove(key, it) }
    }

    override fun setPrefString(key: String?, value: String?) {
        Config.setString(key, value, getContext())
    }

    override fun isConnected(): Boolean {
        return Utility.isConnected(getContext())
    }

    override fun getString(res: Int): String {
        return getContext()?.getString(res) ?: ""
    }
}