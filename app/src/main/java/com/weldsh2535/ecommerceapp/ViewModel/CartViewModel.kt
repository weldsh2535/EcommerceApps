package com.weldsh2535.ecommerceapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.weldsh2535.ecommerceapp.Model.Cart
import java.util.Date

class CartViewModel:ViewModel() {
    private var db = Firebase.firestore
    private val cart = "Carts"

    val createLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val updateLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val deleteLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val getLiveData:MutableLiveData<List<Cart>> by lazy {
        MutableLiveData<List<Cart>>()
    }

    fun create(carts: Cart){
        val docRef = db.collection(cart)
        docRef.add(carts.toMap()).addOnSuccessListener {
            createLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("create",it.localizedMessage)
            createLiveData.postValue(false)
        }
    }
    fun update(carts:Cart){
        val docRef = db.collection(cart)
        docRef.document(carts.id.toString()).update(carts.toMap()).addOnSuccessListener {
            updateLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("update",it.localizedMessage)
            updateLiveData.postValue(false)
        }
    }
    fun delete(id: String){
        val docRef = db.collection(cart)
        docRef.document(id).delete().addOnSuccessListener {
            deleteLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("delete",it.localizedMessage)
            deleteLiveData.postValue(false)
        }
    }
    fun getListData(){
        val docRef = db.collection(cart)
        docRef.get().addOnSuccessListener {
            val carts = ArrayList<Cart>()
            for (item in it.documents){
                var cart = Cart()
                cart.id = item.id.toInt()
                cart.userId = item.data!!["userId"] as String?
                cart.sessionId = item.data!!["sessionId"] as String?
                cart.token = item.data!!["token"] as String?
                cart.status = item.data!!["status"] as Int?
                cart.firstName = item.data!!["firstName"] as String?
                cart.middleName = item.data!!["middleName"] as String?
                cart.lastName = item.data!!["lastName"] as String?
                cart.mobile = item.data!!["mobile"] as String?
                cart.email = item.data!!["email"] as String?
                cart.line1 = item.data!!["line1"] as String?
                cart.line2 = item.data!!["line2"] as String?
                cart.city = item.data!!["city"] as String?
                cart.province = item.data!!["province"] as String?
                cart.country = item.data!!["country"] as String?
                cart.createdAt = item.data!!["createdAt"] as Date?
                cart.updatedAt = item.data!!["updatedAt"] as Date?
                cart.content = item.data!!["content"] as String?
                carts.add(cart)
            }
            getLiveData.postValue(carts)
        }.addOnFailureListener {
            Log.d("get",it.localizedMessage)
            getLiveData.postValue(null)
        }
    }
}