package com.shmagins.flikrapp.main

import android.os.Bundle
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
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.sql.Time
import java.util.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), HasAndroidInjector {
    private val viewModel: MainActivityViewModel by viewModels()
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var searcher: Searcher

    interface Searcher {
        fun onSearch(text: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        val fragment = PhotoListFragment()
        searcher = fragment
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_activity_fragment_container, fragment, "MASTER_FRAGMENT")
            .commit()
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
                    searcher.onSearch(p0!!)
                    submitSearch(p0)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null && p0.length > 3) {
                        searcher.onSearch(p0!!)
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
                                ViewModelProvider(this@MainActivity)
                                    .get(MainActivityViewModel::class.java)
                                    .searchTextChanged(s)
                            }
                        }, delay )
                    }
                }

            })

        }

        return true
    }
}