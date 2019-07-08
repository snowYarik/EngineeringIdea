package com.linty.engineeringidea.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.linty.engineeringidea.R

abstract class SingleActivity : AppCompatActivity() {
    protected abstract val getFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = supportFragmentManager
        var fragment = manager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = getFragment
            manager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
