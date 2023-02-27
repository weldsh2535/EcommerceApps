package com.weldsh2535.ecommerceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.weldsh2535.ecommerceapp.Adapter.UserAdapter
import com.weldsh2535.ecommerceapp.Model.Category
import com.weldsh2535.ecommerceapp.Model.User
import com.weldsh2535.ecommerceapp.Model.getCategoryId
import com.weldsh2535.ecommerceapp.Model.getId
import com.weldsh2535.ecommerceapp.ViewModel.UserViewModel
import com.weldsh2535.ecommerceapp.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),UserAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userAdapter: UserAdapter
    private lateinit var list:ArrayList<User>

    private var selected:User = User()
    private val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initElement()
       // initViewModel()

        //initialize cloud firestore
        val db = Firebase.firestore
        // Create a new user with a first and last name
        var users = User(
            id = getId(),
            firstName = "Abebe",
            middleName = "Lema",
            lastName = "Demeke",
            mobile = "0925357022",
            email = "weldsh253@gmail.com",
            passwordHash = "aweke@12As",
            admin = "admin",
            vendor = "true",
            registeredAt = Date(),
            lastLogin = Date(),
            intro = "yes",
            profile = "weldsh aweke"
        )
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )
        // Add a new document with a generated ID
        db.collection("User")
            .add(users)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
//To add category
        val category = Category(
            id = getCategoryId(),
            parentId = "shoes",
            title = "man clothes",
            metaTitle = "Man",
            slug = "",
            content = "shoes"
        )
        db.collection("Category")
            .add(category)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumnetSnapshot with ID:  ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding documnet", e)
            }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_shop,
                R.id.navigation_notifications,
                R.id.navigation_profile
            )
        )
        // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    private fun initElement() {
  /*      name = findViewById(R.id.name)
        price = findViewById(R.id.price)
        description = findViewById(R.id.description)
        submit = findViewById(R.id.submit)
        rvList = findViewById(R.id.rvList)*/

        list = ArrayList()

      /*  submit.setOnClickListener {
            create()
        }
*/
        // Get list
        userViewModel.getList()

    }

    override fun onClick(item: User, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onDelete(item: User, position: Int) {
        TODO("Not yet implemented")
    }

/*    private fun initViewModel() {
        productViewModel.createLiveData.observe(this, {
            onCreate(it)
        })

        productViewModel.updateLiveData.observe(this, {
            onUpdate(it)
        })

        productViewModel.deleteLiveData.observe(this, {
            onDelete(it)
        })

        productViewModel.getListLiveData.observe(this, {
            onGetList(it)
        })
    }

    private fun onCreate(it: Boolean) {
        if (it) {
            userViewModel.getList()
            resetText()
        }
    }

    private fun onUpdate(it: Boolean) {
        if (it) {
            userViewModel.getList()
            resetText()
        }
    }

    private fun onDelete(it: Boolean) {
        if (it) {
            userViewModel.getList()
            resetText()
        }
    }

    private fun onGetList(it: List<Product>) {
        list = ArrayList()
        list.addAll(it)

        productAdapter = ProductAdapter(list, this)

        rvList.adapter = productAdapter
        rvList.layoutManager = LinearLayoutManager(baseContext)

        productAdapter.notifyDataSetChanged()
    }

    private fun create() {
        val product = Product(
            selected.id,
            name.text.toString(),
            price.text.toString().toDouble(),
            description.text.toString(),
            selected.create_date ?: Timestamp.now(),
            selected.update_date
        )
        if (product.id != null) {
            productViewModel.update(product)
        } else {
            productViewModel.create(product)
        }
    }

    private fun resetText() {
        selected = Product()

        name.text = null
        price.text = null
        description.text = null
    }

    override fun onClick(item: Product, position: Int) {
        selected = item
        selected.update_date = Timestamp.now()

        name.setText(selected.name)
        price.setText(selected.price.toString())
        description.setText(selected.description)
    }

    override fun onDelete(item: Product, position: Int) {
        productViewModel.delete(item.id!!)
    }*/
}

}