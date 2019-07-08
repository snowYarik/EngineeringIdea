package com.linty.engineeringidea.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.linty.engineeringidea.ImageRecyclerAdapter
import com.linty.engineeringidea.R
import android.provider.MediaStore
import android.support.annotation.RequiresPermission
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker.checkSelfPermission
import android.util.Log


class GalleryFragment : Fragment() {
    @BindView(R.id.back_btn)
    lateinit var backBtn: ImageButton
    @BindView(R.id.image_recycler)
    lateinit var imageRecycler: RecyclerView
    private var imageAdapter: ImageRecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(com.linty.engineeringidea.R.layout.gallery_fragment, container, false)
        ButterKnife.bind(this, view)
        imageRecycler.layoutManager = GridLayoutManager(context, 3)
        //refactoring
        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0
        )
        //
        Log.i("COUNT", getImages().size.toString())
        imageAdapter = ImageRecyclerAdapter(getImages(), this.context!!)
        return view
    }

    @OnClick(R.id.back_btn)
    fun onBackClick() {
        activity!!.finish()
    }

    fun getImages(): List<String> {
        val galleryImageUrls: ArrayList<String>
        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)
        val orderBy = MediaStore.Images.Media.DATE_TAKEN

        val imagecursor = context!!.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
            null, null, "$orderBy DESC"
        )

        galleryImageUrls = ArrayList()

        for (i in 0 until imagecursor.getCount()) {
            imagecursor.moveToPosition(i)
            val dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA)
            galleryImageUrls.add(imagecursor.getString(dataColumnIndex))

        }
        imagecursor.close()
        return galleryImageUrls

    }

}