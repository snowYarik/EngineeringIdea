package com.linty.engineeringidea.links

import android.content.Context
import com.linty.engineeringidea.model.Link

class LinkPresenter(val view: IVIew, val model: IInterector) : IPresenter, IPresenter.ISelectLinksListener {
    override fun loadLinks(context: Context) {
        model.selectAllLinks(context, this)
    }

    override fun onSuccessLoad(links: List<Link>) {
        view.successLoad(links)
    }

    override fun onErrorLoad(message: String) {
        view.errorLoad(message)
    }
}