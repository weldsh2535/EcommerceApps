package com.weldsh2535.ecommerceapp.SignIn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.weldsh2535.ecommerceapp.Adapter.UserAdapter
import com.weldsh2535.ecommerceapp.MainActivity
import com.weldsh2535.ecommerceapp.Model.User
import com.weldsh2535.ecommerceapp.ViewModel.UserViewModel
import com.weldsh2535.ecommerceapp.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(), UserAdapter.OnItemClickListener{

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var list:ArrayList<User>
    private val userViewModel:UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    private var selected:User = User()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
    }

    private fun setupData() {
        list = ArrayList()
        btnRegister.setOnClickListener {
            create()
        }
        // Get list
        userViewModel.getList()
    }
    private fun create() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val user = User(
            getId().toString(),
            firstname.text.toString(),
            lastname.text.toString(),
            phonenumber.text.toString(),
            username.text.toString() ,
            email.text.toString(),
            currentDate,
            currentDate,
        )
        userViewModel.create(user)
        val intent = Intent(context,MainActivity::class.java)
        startActivity(intent)
      /*  if (user.id != null) {
            userViewModel.update(user)
        } else {
            userViewModel.create(user)
        }*/
    }
    private fun initViewModel() {
        userViewModel.createLiveData.observe(viewLifecycleOwner, {
            onCreate(it)
        })

        userViewModel.updateLiveData.observe(viewLifecycleOwner, {
            onUpdate(it)
        })

        userViewModel.deleteLiveData.observe(viewLifecycleOwner, {
            onDelete(it)
        })

        userViewModel.getListLiveData.observe(viewLifecycleOwner, {
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

    private fun onGetList(it: List<User>) {
        list = ArrayList()
        list.addAll(it)

        userAdapter = UserAdapter(list, this)

      /*  rvList.adapter = userAdapter
        rvList.layoutManager = LinearLayoutManager(baseContext)*/

        userAdapter.notifyDataSetChanged()
    }



    private fun resetText() {
        selected = User()

        firstname.text = null
        lastname.text = null
        email.text = null
        phonenumber.text = null
        password.text = null

    }

    override fun onClick(item: User, position: Int) {
        selected = item
        // selected.registeredAt =

          firstname.setText(selected.firstName)
          lastname.setText(selected.lastName.toString())
          email.setText(selected.email)

    }

    override fun onDelete(item: User, position: Int) {
        userViewModel.delete(item.id!!)
    }
}