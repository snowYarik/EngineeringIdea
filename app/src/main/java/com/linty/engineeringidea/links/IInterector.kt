package com.linty.engineeringidea.links

import android.content.Context

interface IInterector {

    fun selectAllLinks(context: Context, selectListener: IPresenter.ISelectLinksListener)

}