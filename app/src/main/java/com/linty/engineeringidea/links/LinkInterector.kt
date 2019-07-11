package com.linty.engineeringidea.links

import android.annotation.SuppressLint
import android.content.Context
import com.linty.engineeringidea.db.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LinkInterector : IInterector {
    @SuppressLint("CheckResult")
    override fun selectAllLinks(context: Context, selectListener: IPresenter.ISelectLinksListener) {
        AppDatabase.getDB(context).linkDao().getAllLinks().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    selectListener.onSuccessLoad(it)
                }, {
                    if (it != null)
                        selectListener.onErrorLoad(it.localizedMessage)
                }
            )

    }
}