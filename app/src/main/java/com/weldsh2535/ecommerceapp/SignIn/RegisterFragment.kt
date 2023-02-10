package com.weldsh2535.ecommerceapp.SignIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weldsh2535.ecommerceapp.R
import com.weldsh2535.ecommerceapp.databinding.FragmentLoginBinding
import com.weldsh2535.ecommerceapp.databinding.FragmentRegisterBinding


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
    }

    private fun setupData() {
        // binding.txtMain.text = getString(R.string.second_fragment)
        //   binding.imgMain.setImageResource(R.mipmap.ic_launcher)
    }
}