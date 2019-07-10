package com.linty.engineeringidea

interface ILoadListener {
    fun <T> onSuccessLoad(type: T)
    fun onErrorLoad(message: String)
}