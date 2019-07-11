package com.linty.engineeringidea.activity

import android.support.v4.app.Fragment
import com.linty.engineeringidea.gallery.GalleryFragmentView

/**
 * MainActivity is the main activity class
 */
class MainActivity : SingleActivity() {
    /**
     * get fragment for attacing
     * @return fragment for attaching
     */
    override val getFragment: Fragment
        get() = GalleryFragmentView()


}
