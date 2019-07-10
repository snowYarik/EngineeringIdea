package com.linty.engineeringidea.links

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.linty.engineeringidea.R
import com.linty.engineeringidea.fragment.gallery.GalleryFragmentView

class LinkFragmentView : Fragment() {
    companion object {
        val TAG: String? = GalleryFragmentView::class.qualifiedName
    }

    @BindView(R.id.back_btn)
    lateinit var backBtn: ImageButton
    @BindView(R.id.link_recycler)
    lateinit var linkRecycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.link_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.back_btn)
    fun onBackClick() {
        activity!!.supportFragmentManager.popBackStack()
    }
}