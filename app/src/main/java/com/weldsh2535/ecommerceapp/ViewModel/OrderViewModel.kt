package com.weldsh2535.ecommerceapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.weldsh2535.ecommerceapp.Model.Order
import java.util.Date

class OrderViewModel:ViewModel() {
    private var db = Firebase.firestore
    private val order = "Orders"

    val createLiveData:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val updateLiveData:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val deleteLiveData:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val getListLiveData:MutableLiveData<List<Order>> by lazy {
        MutableLiveData<List<Order>>()
    }

    fun create(orders: Order){
        val docRef = db.collection(order)
        docRef.add(orders).addOnSuccessListener {
            createLiveData.postValue(true)
        }.addOnFailureListener {
            createLiveData.postValue(false)
            Log.d("create",it.localizedMessage)
        }
    }
    fun update(orders:Order){
        val docRef = db.collection(order)
        docRef.document(orders.id.toString()).update(orders.toMap()).addOnSuccessListener {
            updateLiveData.postValue(true)
        }.addOnFailureListener {
            updateLiveData.postValue(false)
            Log.d("update",it.localizedMessage)
        }
    }
    fun delete(id:String){
        val docRef = db.collection(order)
        docRef.document(id).delete().addOnSuccessListener {
            deleteLiveData.postValue(true)
        }.addOnFailureListener {
            deleteLiveData.postValue(false)
            Log.d("delete",it.localizedMessage)
        }
    }
    fun getListData(){
        val docRef = db.collection(order)
        docRef.get().addOnSuccessListener {
            val orders = ArrayList<Order>()
            for (item in it.documents){
                var order = Order()
                order.id = item.id.toInt()
                order.userId = item.data!!["userId"] as String?
                order.token = item.data!!["token"] as String?
                order.sessionId = item.data!!["sessionId"] as String?
                order.firstName = item.data!!["firstName"] as String?
                order.middleName = item.data!!["middleName"] as String?
                order.lastName = item.data!!["lastName"] as String?
                order.country = item.data!!["country"] as String?
                order.city = item.data!!["city"] as String?
                order.email = item.data!!["email"] as String?
                order.tax = item.data!!["tax"] as Float?
                order.subTotal = item.data!!["subTotal"] as Float?
                order.total = item.data!!["total"] as Float?
                order.discount = item.data!!["discount"] as Int?
                order.grandTotal = item.data!!["grandTotal"] as Int?
                order.itemDiscount = item.data!!["itemDiscount"] as Float?
                order.shipping = item.data!!["shipping"] as Float?
                order.line1 = item.data!!["line1"] as String?
                order.line2 = item.data!!["line2"] as String?
                order.mobile = item.data!!["mobile"] as String?
                order.promo = item.data!!["promo"] as String?
                order.status = item.data!!["status"] as String?
                order.content = item.data!!["content"] as String?
                order.createdAt = item.data!!["createdAt"] as Date?
                order.updatedAt = item.data!!["updatedAt"] as Date?
                order.province = item.data!!["province"] as String?
               orders.add(order)
            }
            getListLiveData.postValue(orders)
        }.addOnFailureListener {
            Log.d("get",it.localizedMessage)
            getListLiveData.postValue(null)
        }
    }
}