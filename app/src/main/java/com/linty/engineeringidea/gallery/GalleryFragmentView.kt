package com.linty.engineeringidea.gallery

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.linty.engineeringidea.adapter.ImageRecyclerAdapter
import com.linty.engineeringidea.listener.OnImageListener
import com.linty.engineeringidea.R
import com.linty.engineeringidea.links.LinkFragmentView
import com.linty.engineeringidea.model.ImageResponse
import com.linty.engineeringidea.util.ViewUtil
import kotlinx.android.synthetic.main.image_recycler_include.view.spinkit

/**
 *
 *The Fragment class
 */
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
    var position: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = GalleryPresenter(
            GalleryInterector(),
            this
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.gallery_fragment, container, false)
        ButterKnife.bind(this, view)
        if (!checkReadExternalPermision()) {
            requestReadPermision()
        } else imageRecycler.adapter =
            ImageRecyclerAdapter(getImages(), context!!, this)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            imageRecycler.layoutManager = GridLayoutManager(context!!, 3)
        else
            imageRecycler.layoutManager = GridLayoutManager(context!!, 5)

        return view
    }

    /**
     * The listener method for back button
     */
    @OnClick(R.id.back_btn)
    fun onBackClick() {
        activity!!.finish()
    }

    /**
     * The listener method for 'link button'
     */
    @OnClick(R.id.links_btn)
    fun onLinksClick() {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LinkFragmentView())
            .addToBackStack("").commit()
    }

    /**
     * Method for getting images from gallery
     * @return the list of the String URIs
     */
    private fun getImages(): List<String> {
        val galleryImageUrls: List<String>?
        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)
        val orderBy = MediaStore.Images.Media.DATE_TAKEN

        val imagecursor = context!!.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
            null, null, "$orderBy DESC"
        )
        galleryImageUrls = ArrayList()
        for (i in 0 until imagecursor!!.count) {
            imagecursor.moveToPosition(i)
            val dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA)
            galleryImageUrls.add(imagecursor.getString(dataColumnIndex))

        }
        imagecursor.close()
        return galleryImageUrls

    }

    /**
     * onImageClick is a method for callback image click
     * @param position the position of the image in recyclerview
     */
    override fun onImageClick(position: Int) {
        presenter.loadImage(activity!!.baseContext, getImages()[position])
        this.position = position


    }

    /**
     * successLoad is a method for callback success upload of the image
     * @param response the response of the image upload
     */
    override fun successLoad(response: ImageResponse) {
        Toast.makeText(context, getString(R.string.done), Toast.LENGTH_SHORT).show()
        ViewUtil.hideView(imageRecycler.getChildAt(position).spinkit)
    }

    /**
     * errorLoad is a method for callback error upload of the image
     * @param message the response message for alert dialog
     */
    override fun errorLoad(message: String) {
        ViewUtil.hideView(imageRecycler.getChildAt(position).spinkit)
        ViewUtil.showAlertDialog(message, context!!)
    }

    /**
     * The method witch check READ_EXTERNAL_STORAGE permission
     * @return is permision denied
     */
    private fun checkReadExternalPermision(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context!!.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED
        }
        return false
    }

    /**
     * Method for request READ_EXTERNAL_STORAGE window
     */
    private fun requestReadPermision() {
        try {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                imageRecycler.adapter =
                    ImageRecyclerAdapter(getImages(), context!!, this)
            }
        }
    }
}
