package com.linty.engineeringidea.links

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.linty.engineeringidea.model.Link
import com.linty.engineeringidea.adapter.LinkRecyclerAdapter
import com.linty.engineeringidea.listener.OnLinkListener
import com.linty.engineeringidea.R
import com.linty.engineeringidea.gallery.GalleryFragmentView
import com.linty.engineeringidea.util.ViewUtil

/**
 * LinkFragmentView is a view fragment class for display link page interface and interact with it
 */
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

    /**
     * onBackClick is a method for listening back click
     */
    @OnClick(R.id.back_btn)
    fun onBackClick() {
        activity!!.supportFragmentManager.popBackStack()
    }

    /**
     * successLoad is a method for callback success selecting of the link
     * @param links the selected list of the Link
     */
    override fun successLoad(links: List<Link>) {
        linkRecycler.adapter = LinkRecyclerAdapter(context!!, links, this)

    }

    /**
     * errorLoad is a method for callback error selecting of the link
     * @param message the response message for alert dialog
     */
    override fun errorLoad(message: String) {
        ViewUtil.showAlertDialog(message, context!!)
    }

    /**
     * onLinkClick is a method for callback link click
     * @param position the position of the link in recyclerview
     */
    override fun onLinkClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}