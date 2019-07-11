package com.linty.engineeringidea.links

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.linty.engineeringidea.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class LinkInterector : IInterector {
    @SuppressLint("CheckResult")
    override fun selectAllLinks(context: Context, selectListener: IPresenter.ISelectLinksListener) {
        AppDatabase.getDB(context).linkDao().getAllLinks().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                Consumer {
                    selectListener.onSuccessLoad(it)
                }
            )

    }
}