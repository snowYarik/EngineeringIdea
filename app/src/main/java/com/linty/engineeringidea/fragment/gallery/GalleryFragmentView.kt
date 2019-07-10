package com.linty.engineeringidea.fragment.gallery

import android.Manifest
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.linty.engineeringidea.ImageRecyclerAdapter
import com.linty.engineeringidea.OnImageListener
import com.linty.engineeringidea.R
import com.linty.engineeringidea.links.LinkFragmentView
import com.linty.engineeringidea.model.ImageResponse


class GalleryFragmentView : Fragment(), OnImageListener, IView {

    companion object {
        val TAG: String? = GalleryFragmentView::class.qualifiedName
    }

    @BindView(R.id.back_btn)
    lateinit var backBtn: ImageButton
    @BindView(R.id.image_recycler)
    lateinit var imageRecycler: RecyclerView
    @BindView(R.id.links_btn)
    lateinit var linksBtn: Button
    lateinit var presenter: IPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = GalleryPresenter(GalleryInterector(), GalleryFragmentView())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.gallery_fragment, container, false)
        ButterKnife.bind(this, view)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            imageRecycler.layoutManager = GridLayoutManager(context!!, 3)
        else
            imageRecycler.layoutManager = GridLayoutManager(context!!, 5)

        //refactoring
        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0
        )
        //
        imageRecycler.adapter = ImageRecyclerAdapter(getImages(), context!!, this)
        return view
    }

    @OnClick(R.id.back_btn)
    fun onBackClick() {
        activity!!.finish()
    }

    @OnClick(R.id.links_btn)
    fun onLinksClick() {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LinkFragmentView())
            .addToBackStack("").commit()
    }

    private fun getImages(): List<String> {
        val galleryImageUrls: ArrayList<String>
        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)
        val orderBy = MediaStore.Images.Media.DATE_TAKEN

        val imagecursor = context!!.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
            null, null, "$orderBy DESC"
        )

        galleryImageUrls = ArrayList()
        //TODO safe cursore
        for (i in 0 until imagecursor.getCount()) {
            imagecursor.moveToPosition(i)
            val dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA)
            galleryImageUrls.add(imagecursor.getString(dataColumnIndex))

        }
        imagecursor.close()
        return galleryImageUrls

    }

    override fun onImageClick(position: Int) {
        presenter.loadImage(activity!!.baseContext, getImages()[position])
    }

    override fun successLoad(response: ImageResponse) {
    }

    override fun errorLoad(message: String) {
        Log.i("RequestErr", message)
        //TODO alert message or window and turn off load spinner
    }

}