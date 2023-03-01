package com.weldsh2535.ecommerceapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.weldsh2535.ecommerceapp.Model.User
import java.util.Date

class UserViewModel:ViewModel() {
    private var db = Firebase.firestore
    private val user ="Users"

    val createLiveData:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val updateLiveData:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val getListLiveData:MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }

    val deleteLiveData:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun create(users:User) {
        val docRef = db.collection(user)
        docRef.add(users.toMap()).addOnSuccessListener {
            createLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("Ceate",it.localizedMessage)
            createLiveData.postValue(false)
        }
    }

    fun update(users:User) {
        val docRef = db.collection(user)
        docRef.document(users.id!!.toString()).update(users.toMap()).addOnSuccessListener {
            updateLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("update",it.localizedMessage)
            updateLiveData.postValue(false)
        }
    }

    fun delete(id:String){
        val docRef = db.collection(user)
        docRef.document(id).delete().addOnSuccessListener {
            deleteLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("delete",it.localizedMessage)
            deleteLiveData.postValue(false)
        }
    }

    fun getList(){
        val docRef = db.collection(user)
        docRef.get().addOnSuccessListener {
            val users = ArrayList<User>()
            for (item in it.documents){
                val user = User()
                user.id = item.id
                user.firstName = item.data!!["firstName"] as String?
                user.lastName = item.data!!["lastName"] as String?
                user.userName = item.data!!["userName"] as String?
                user.email = item.data!!["email"] as String?
                user.mobile = item.data!!["moblie"] as String?
                user.registeredAt = item.data!!["registeredAt"] as String
                user.lastLogin = item.data!!["lastLogin"] as String
                users.add(user)
            }
            getListLiveData.postValue(users)
        }.addOnFailureListener {
            Log.d("get",it.localizedMessage!!)
            getListLiveData.postValue(null)
        }
    }
}