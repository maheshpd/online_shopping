package com.example.onlineshopping.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.onlineshopping.adapter.CategoryAdapter
import com.example.onlineshopping.adapter.RecommendedAdapter
import com.example.onlineshopping.adapter.SliderAdapter
import com.example.onlineshopping.Model.SliderModel
import com.example.onlineshopping.databinding.ActivityMainBinding
import com.example.onlineshopping.viewModel.MainViewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initCategory()
        initRecommended()

    }

    private fun initRecommended() {
        binding.progressBarRecommendation.visibility = View.VISIBLE
        viewModel.recommended.observe(this, Observer {
            binding.viewRecommendation.layoutManager=GridLayoutManager(this@MainActivity,2);
            binding.viewRecommendation.adapter = RecommendedAdapter(it)
            binding.progressBarRecommendation.visibility = View.GONE
        })
        viewModel.loadRecommended()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.categories.observe(this, Observer {
            binding.viewCategory.layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL, false
            )
            binding.viewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()
    }

    private fun banners(image: List<SliderModel>) {
        binding.viewPager2.adapter = SliderAdapter(image, binding.viewPager2)
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPager2.setPageTransformer(compositePageTransformer)
        if (image.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPager2)
        }
    }

    private fun initBanner() {
        binding.progressBarSider.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer {
            banners(it)
            binding.progressBarSider.visibility = View.GONE
        })
        viewModel.loadBanners()
    }
}