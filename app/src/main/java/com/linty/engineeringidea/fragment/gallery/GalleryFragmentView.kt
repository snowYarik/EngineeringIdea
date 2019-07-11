package com.linty.engineeringidea.fragment.gallery

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
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
import kotlinx.android.synthetic.main.image_recycler_include.*
import kotlinx.android.synthetic.main.image_recycler_include.view.*
import kotlinx.android.synthetic.main.image_recycler_include.view.spinkit
import java.security.Permission

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
        presenter = GalleryPresenter(GalleryInterector(), this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.gallery_fragment, container, false)
        ButterKnife.bind(this, view)
        if (!checkReadExternalPermision()) {
            requestReadPermision()
        } else imageRecycler.adapter = ImageRecyclerAdapter(getImages(), context!!, this)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            imageRecycler.layoutManager = GridLayoutManager(context!!, 3)
        else
            imageRecycler.layoutManager = GridLayoutManager(context!!, 5)

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

    override fun onImageClick(position: Int) {
        presenter.loadImage(activity!!.baseContext, getImages()[position])


    }

    override fun successLoad(context: Context, response: ImageResponse) {
        Log.i(TAG, isAdded.toString())
        Toast.makeText(this.context, getString(R.string.done), Toast.LENGTH_SHORT).show()
        hideLoadSpinner()
    }


    override fun errorLoad(message: String) {
//        showAlertDialog(message, this.context!!)
    }

    private fun checkReadExternalPermision(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context!!.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED
        }
        return false
    }

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

    private fun showAlertDialog(message: String, context: Context) {
        val dialog: AlertDialog = AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }

    private fun hideLoadSpinner() {
        spinkit.visibility = View.GONE
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                imageRecycler.adapter = ImageRecyclerAdapter(getImages(), context!!, this)
            }
        }
    }
}
