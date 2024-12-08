package com.example.onlineshopping.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlineshopping.adapter.ListItemsAdapter
import com.example.onlineshopping.databinding.ActivityListItemsBinding
import com.example.onlineshopping.viewModel.MainViewModel

class ListItemsActivity : BaseActivity() {
    private lateinit var binding:ActivityListItemsBinding
    private val viewModel = MainViewModel()
    private var id:String = ""
    private var title:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundles()
        initList()
    }

    private fun initList() {
        binding.apply {
            progressBarList.visibility = View.VISIBLE
            viewModel.recommended.observe(this@ListItemsActivity, Observer {
                viewList.layoutManager = GridLayoutManager(this@ListItemsActivity,2)
                viewList.adapter = ListItemsAdapter(it)
                progressBarList.visibility = View.GONE
            })
            viewModel.loadFiltered(id)
        }
    }

    private fun getBundles() {
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!
        binding.categoryTxt.text = title
    }
}