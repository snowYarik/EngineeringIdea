package com.linty.engineeringidea.links

import android.content.Context
import com.linty.engineeringidea.model.Link

interface IPresenter {
    fun loadLinks(context: Context)
    interface ISelectLinksListener {
        fun onSuccessLoad(links: List<Link>)
        fun onErrorLoad(message: String)
    }
}