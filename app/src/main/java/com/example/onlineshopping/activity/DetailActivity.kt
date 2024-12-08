package com.example.onlineshopping.activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlineshopping.adapter.SelectModelAdapter
import com.example.onlineshopping.databinding.ActivityDetailBinding
import com.example.onlineshopping.helper.ManagmentCart
import com.example.onlineshopping.Model.ItemsModel
import com.example.onlineshopping.R
import com.example.onlineshopping.adapter.PicAdapter


class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1

    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart = ManagmentCart(this)
        getBundle()
        initList()
    }

    private fun initList() {
        val modelList = ArrayList<String>()
        for (models in item.model) {
            modelList.add(models)
        }

        binding.modelList.adapter = SelectModelAdapter(modelList)
        binding.modelList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val picList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            picList.add(imageUrl)
        }

        Glide.with(this)
            .load(picList[0])
            .into(binding.img)

        binding.picList.adapter = PicAdapter(picList) { selectedImageUrl ->
            Glide.with(this)
                .load(selectedImageUrl)
                .into(binding.img)
        }

        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun getBundle() {
        item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("object", ItemsModel::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("object")!!
        }

        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$" + item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.addToCartButton.setOnClickListener {
            item.numberInCart = numberOrder
            managmentCart.insertFood(item)
        }
        binding.backBtn.setOnClickListener { finish() }
        binding.cartBtn.setOnClickListener {

        }
    }
}