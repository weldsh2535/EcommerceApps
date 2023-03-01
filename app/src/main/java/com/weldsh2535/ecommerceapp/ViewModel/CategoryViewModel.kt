package com.weldsh2535.ecommerceapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.weldsh2535.ecommerceapp.Model.Category

class CategoryViewModel : ViewModel() {

    private var db = Firebase.firestore
    private val category = "Categorys"

    val createLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val getLiveData: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>()
    }

    val updateLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val deleteLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun create(categorys: Category) {
        val docRef = db.collection(category)
        docRef.add(categorys.toMap()).addOnSuccessListener {
            createLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("create", it.localizedMessage)
            createLiveData.postValue(false)
        }
    }

    fun update(categorys: Category) {
        val docRef = db.collection(category)
        docRef.document(categorys.id.toString()).update(categorys.toMap()).addOnSuccessListener {
            updateLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("updated", it.localizedMessage)
            updateLiveData.postValue(false)
        }
    }

    fun getListData() {
        val docRef = db.collection(category)
        docRef.get().addOnSuccessListener {
            val categorys = ArrayList<Category>()
            for (item in it.documents) {
                var category = Category()
                category.id = item.id.toInt()
                category.parentId = item.data!!["parentId"] as String?
                category.slug = item.data!!["slug"] as String?
                category.content = item.data!!["content"] as String?
                category.metaTitle = item.data!!["metaTitle"] as String?
                category.title = item.data!!["title"] as String?
                categorys.add(category)
            }
            getLiveData.postValue(categorys)
        }.addOnFailureListener {
            Log.d("get", it.localizedMessage)
            getLiveData.postValue(null)
        }
    }
}