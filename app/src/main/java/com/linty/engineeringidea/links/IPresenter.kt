package com.linty.engineeringidea.links

import com.linty.engineeringidea.ILoadListener

interface IPresenter {
    fun loadLinks()
    interface ILoadLinksListener : ILoadListener
}