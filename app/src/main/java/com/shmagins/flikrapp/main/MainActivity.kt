package com.shmagins.flikrapp.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.shmagins.flikrapp.R
import com.shmagins.flikrapp.common.MainActivityViewModel
import com.shmagins.flikrapp.common.ReadyPhoto
import com.shmagins.flikrapp.photolist.PhotoListFragment
import com.shmagins.flikrapp.singlephoto.SinglePhotoActivity
import com.shmagins.flikrapp.singlephoto.SinglePhotoFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.core.Observable
import java.sql.Time
import java.util.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), HasAndroidInjector {
    private val viewModel: MainActivityViewModel by viewModels()
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var searcher: Searcher
    var isLandscape: Boolean = false

    interface Searcher {
        fun onSearch(o: Observable<ReadyPhoto>)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        isLandscape = resources.getBoolean(R.bool.isLandscape)

        Log.d("TAG", "" + isLandscape)
        val fragment = PhotoListFragment()
        searcher = fragment
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_activity_fragment_container, fragment, "MASTER_FRAGMENT")
            .commit()

        if(isLandscape) {
            val fragment = SinglePhotoFragment("","")
            supportFragmentManager
                .beginTransaction()
                .add(R.id.detail_fragment_container, fragment, "DETAIL_FRAGMENT")
                .commit()
        }
    }

    fun openDetail(url: String, title: String){
        if (isLandscape) {
            val fragment = SinglePhotoFragment(url, title)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.detail_fragment_container, fragment, "DETAIL_FRAGMENT")
                .addToBackStack("DETAIL_FRAGMENT_CHANGE")
                .commit()
        } else {
            SinglePhotoActivity.start(this, url, title)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.let {
            val delay: Long = 300
            var timer: Timer = Timer()
            val searchView = menu.findItem(R.id.action_search)
                .actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    submitSearch(p0)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null && p0.length > 3) {
                        submitSearch(p0)
                    }
                    return false
                }

                fun submitSearch(s: String?) {
                    s?.let {
                        timer.cancel()
                        timer = Timer()
                        timer.schedule(object: TimerTask(){
                            override fun run() {
                                searcher.onSearch(
                                ViewModelProvider(this@MainActivity)
                                    .get(MainActivityViewModel::class.java)
                                    .searchTextChanged(s)
                                )
                            }
                        }, delay )
                    }
                }

            })

        }

        return true
    }
}