package com.example.onlineshopping.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshopping.Model.CategoryModel
import com.example.onlineshopping.Model.ItemsModel
import com.example.onlineshopping.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainViewModel:ViewModel() {
    private val firebaseDatabase=FirebaseDatabase.getInstance()
    private val _banner=MutableLiveData<List<SliderModel>>()
    private val _category=MutableLiveData<MutableList<CategoryModel>>()
    private val _recommended=MutableLiveData<MutableList<ItemsModel>>()

    val banners:LiveData<List<SliderModel>> = _banner
    val categories:LiveData<MutableList<CategoryModel>> = _category
    val recommended:LiveData<MutableList<ItemsModel>> = _recommended

    fun loadFiltered(id:String) {
        val ref=firebaseDatabase.getReference("Items")
        val query: Query =ref.orderByChild("categoryId").equalTo(id)
        query.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapSnapshot in snapshot.children) {
                    val list=childSnapSnapshot.getValue(ItemsModel::class.java)
                    if(list!=null) {
                        lists.add(list)
                    }
                }
                _recommended.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadRecommended() {
        val ref=firebaseDatabase.getReference("Items")
        val query: Query =ref.orderByChild("showRecommended").equalTo(true)
        query.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapSnapshot in snapshot.children) {

                    val list=childSnapSnapshot.getValue(ItemsModel::class.java)
                    if(list!=null) {
                        lists.add(list)
                    }
                }
                _recommended.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadCategory() {
        val ref=firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for (childSnapSnapshot in snapshot.children) {
                    val list=childSnapSnapshot.getValue(CategoryModel::class.java)
                    if(list!=null) {
                        lists.add(list)
                    }
                }
                _category.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        } )
    }

    fun loadBanners() {
        val ref=firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapSnapshot in snapshot.children) {
                    val list=childSnapSnapshot.getValue(SliderModel::class.java)
                    if(list!=null) {
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        } )
    }
}