package com.shmagins.flikrapp.photolist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shmagins.flikrapp.R
import com.shmagins.flikrapp.common.MainActivityViewModel
import com.shmagins.flikrapp.common.ReadyPhoto
import com.shmagins.flikrapp.main.MainActivity
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PhotoListFragment : Fragment(), MainActivity.Searcher {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val adapter: PhotoListAdapter = PhotoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_master, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.photo_list_recycler)
        val viewModel = ViewModelProvider(this.requireActivity())
            .get(MainActivityViewModel::class.java)

        recycler.adapter = adapter
        recycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    var array: IntArray = IntArray(2)
                    (recyclerView.layoutManager as StaggeredGridLayoutManager).findFirstVisibleItemPositions(array)
                    if (dy > 0) {
                        if (array[0] > recyclerView.adapter!!.itemCount / 4 * 3) {
                            Log.d("happy", "loading")
                            viewModel.loadNextPage()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe{ readyPhoto: ReadyPhoto? -> this@PhotoListFragment.adapter.addPhoto(readyPhoto!!) }
                        }
                    }
            }
        })

        viewModel.getPhotos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ readyPhoto: ReadyPhoto? -> this@PhotoListFragment.adapter.addPhoto(readyPhoto!!) }


        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onSearch(o: Observable<ReadyPhoto>) {
        activity?.runOnUiThread { (adapter as PhotoListAdapter).clearPhotos() }
        o.observeOn(AndroidSchedulers.mainThread())
            .subscribe{readyPhoto: ReadyPhoto? -> adapter.addPhoto(readyPhoto!!)}
    }
}