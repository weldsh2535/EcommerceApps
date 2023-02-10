package com.weldsh2535.ecommerceapp.SignIn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.weldsh2535.ecommerceapp.MainActivity
import com.weldsh2535.ecommerceapp.R
import com.weldsh2535.ecommerceapp.databinding.FragmentLoginBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var binding:FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin1.setOnClickListener {
            val intent = Intent (this@LoginFragment.context, MainActivity::class.java)
            startActivity(intent)
        }

        setupData()

    }

    private fun setupData() {
        //binding.txtMain.text = getString(R.string.second_fragment)
     //   binding.imgMain.setImageResource(R.mipmap.ic_launcher)
    }


}