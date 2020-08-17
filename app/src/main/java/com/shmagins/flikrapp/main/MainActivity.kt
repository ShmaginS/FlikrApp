package com.shmagins.flikrapp.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.shmagins.flikrapp.R
import com.shmagins.flikrapp.common.MainActivityViewModel
import com.shmagins.flikrapp.photolist.PhotoListFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), HasAndroidInjector {
    private val viewModel: MainActivityViewModel by viewModels()
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(PhotoListFragment(), "MASTER_FRAGMENT")
            .commit()
    }

}