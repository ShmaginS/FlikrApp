package com.shmagins.flikrapp.singlephoto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.shmagins.flikrapp.R
import com.shmagins.flikrapp.common.MainActivityViewModel
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SinglePhotoActivity : DaggerAppCompatActivity(), HasAndroidInjector {
    private val viewModel: MainActivityViewModel by viewModels()
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            exitTransition = Explode()
        }
        setContentView(R.layout.activity_fragment_container)
        val url = intent.getStringExtra(SinglePhotoActivity.url) ?: ""
        val title = intent.getStringExtra(SinglePhotoActivity.title) ?: ""
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_activity_fragment_container, SinglePhotoFragment(url, title), "DETAIL_FRAGMENT")
            .commit()

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun start(context: Context, url: String, title: String) {
            val intent = Intent(context, SinglePhotoActivity::class.java)
            intent.putExtra(this.url, url)
            intent.putExtra(this.title, title)
            context.startActivity(intent)
        }

        const val url = "URL"
        const val title = "TITLE"
    }



}
