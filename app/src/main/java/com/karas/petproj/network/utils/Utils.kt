package com.karas.petproj.network.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karas.petproj.network.model.UserNetworkData

fun UserNetworkData.toData(): Map<String, Any> {
    val gson = Gson()
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
}

fun Map<String, Any>.toModel(): UserNetworkData {
    val gson = Gson()
    val json = gson.toJson(this)
    Log.v("TAGBAGA", json)
    return gson.fromJson(json, UserNetworkData::class.java)
}