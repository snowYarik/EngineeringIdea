package com.linty.engineeringidea.links

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.linty.engineeringidea.Link
import com.linty.engineeringidea.LinkRecyclerAdapter
import com.linty.engineeringidea.OnLinkListener
import com.linty.engineeringidea.R
import com.linty.engineeringidea.fragment.gallery.GalleryFragmentView

class LinkFragmentView : Fragment(), IVIew, OnLinkListener {


    companion object {
        val TAG: String? = GalleryFragmentView::class.qualifiedName
    }

    @BindView(R.id.back_btn)
    lateinit var backBtn: ImageButton
    @BindView(R.id.link_recycler)
    lateinit var linkRecycler: RecyclerView

    lateinit var presenter: IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LinkPresenter(this, LinkInterector())

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.link_fragment, container, false)
        ButterKnife.bind(this, view)
        linkRecycler.layoutManager = LinearLayoutManager(context!!)
        presenter.loadLinks(context!!)
        return view
    }

    @OnClick(R.id.back_btn)
    fun onBackClick() {
        activity!!.supportFragmentManager.popBackStack()
    }

    override fun successLoad(links: List<Link>) {
        val adapter = LinkRecyclerAdapter(context!!, links, this)
        linkRecycler.adapter = adapter

    }

    override fun errorLoad(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLinkClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}