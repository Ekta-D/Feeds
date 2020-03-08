package com.appiness.cogni.tasktodo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.appiness.cogni.tasktodo.R
import com.appiness.cogni.tasktodo.di.Injection
import com.appiness.cogni.tasktodo.model.Repositry
import com.appiness.cogni.tasktodo.model.RowResponse
import com.appiness.cogni.tasktodo.viewmodel.RowViewModel
import com.ebayk.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: RowViewModel
    private lateinit var adapter: RecycleAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var nodata: TextView
    private lateinit var title: TextView
    lateinit var itemsCells: List<RowResponse?>
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        initViews()


    }


    fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        ).get(RowViewModel::class.java)

        viewModel.rows_response.observe(this, renderReviews)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
    }

    fun initViews() {
        recyclerView = findViewById(R.id.recycle) as RecyclerView
        swipeRefreshLayout = findViewById(R.id.itemsswipetorefresh) as SwipeRefreshLayout

        nodata = findViewById(R.id.no_data)
        toolbar = findViewById(R.id.toolbar)
        title = toolbar.findViewById(R.id.title_page)

        adapter = RecycleAdapter(this@MainActivity, viewModel.rows_response.value ?: emptyList())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.GONE
            viewModel.refresh()
        }


    }

    //observers
    private val renderReviews = Observer<List<RowResponse>> {
        adapter.update(it)
    }


    private val isViewLoadingObserver = Observer<Boolean> {
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
        if (adapter.itemCount > 0) {
            recyclerView.visibility = View.VISIBLE
        }
        if (!it) {
            title.text = RowViewModel.titleString
        }
        if(Repositry.isBadResponse)
        {
            swipeRefreshLayout.visibility=View.GONE
            toolbar.visibility=View.GONE
            nodata.visibility=View.VISIBLE
            nodata.text="Bad Url request please check..."
        }


    }


    override fun onResume() {
        super.onResume()
        if (NetworkUtils.isNetworkConnected(this@MainActivity)) {
            swipeRefreshLayout.visibility=View.VISIBLE
            toolbar.visibility=View.VISIBLE
            viewModel.loadResponse()
        } else {
            swipeRefreshLayout.visibility=View.GONE
            nodata.visibility = View.VISIBLE
            toolbar.visibility=View.GONE
        }
    }
}
