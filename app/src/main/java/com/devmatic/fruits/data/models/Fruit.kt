package com.devmatic.fruits.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

data class Fruit(@SerializedName("id") @PrimaryKey var id: Int? = null) : RealmObject() {
    constructor() : this(null)

    @SerializedName("color")
    var color: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("weight")
    var weight: Double? = null
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("delicious")
    var delicious: Int? = null

}