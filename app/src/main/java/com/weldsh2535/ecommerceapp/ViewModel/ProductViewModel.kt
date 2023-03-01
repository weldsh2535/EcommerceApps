package com.weldsh2535.ecommerceapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.weldsh2535.ecommerceapp.Model.Product
import java.util.Date

class ProductViewModel:ViewModel() {
    private var db = Firebase.firestore
    private val product = "Products"
    val createLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val updateLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val getListLiveData: MutableLiveData<List<Product>> by lazy {
        MutableLiveData<List<Product>>()
    }

    val deleteLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun create(products: Product) {
        val docRef = db.collection(product)
        docRef.add(products.toMap()).addOnSuccessListener {
            createLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("Ceate",it.localizedMessage)
            createLiveData.postValue(false)
        }
    }

    fun update(products: Product) {
        val docRef = db.collection(product)
        docRef.document(products.id!!.toString()).update(products.toMap()).addOnSuccessListener {
            updateLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("update",it.localizedMessage)
            updateLiveData.postValue(false)
        }
    }

    fun delete(id:String){
        val docRef = db.collection(product)
        docRef.document(id).delete().addOnSuccessListener {
            deleteLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("delete",it.localizedMessage)
            deleteLiveData.postValue(false)
        }
    }

    fun getList(){
        val docRef = db.collection(product)
        docRef.get().addOnSuccessListener {
            val products = ArrayList<Product>()
            for (item in it.documents){
                val product = Product()
                product.id = item.id
                product.title = item.data!!["title"] as String?
                product.userId = item.data!!["userId"] as String?
                product.metaTitle = item.data!!["metaTitle"] as String?
                product.quantity = item.data!!["quantity"] as Int?
                product.discount = item.data!!["discount"] as Float?
                product.price = item.data!!["price"] as Float?
                product.publishedAt = item.data!!["publishedAt"] as String?
                product.type = item.data!!["type"] as String?
                product.summary = item.data!!["summary"] as String?
                product.slug = item.data!!["slug"] as String?
                product.shop = item.data!!["shop"] as String?
                product.createdAt = item.data!!["createdAt"] as String?
                product.updatedAt = item.data!!["updatedAt"] as String?
                product.startsAt = item.data!!["startsAt"] as String?
                product.endsAt = item.data!!["endsAt"] as String?
                product.content = item.data!!["content"] as String?

                products.add(product)
            }
            getListLiveData.postValue(products)
        }.addOnFailureListener {
            Log.d("get",it.localizedMessage!!)
            getListLiveData.postValue(null)
        }
    }
}