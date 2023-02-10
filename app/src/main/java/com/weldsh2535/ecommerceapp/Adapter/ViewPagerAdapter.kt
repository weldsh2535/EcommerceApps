package com.weldsh2535.ecommerceapp.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.weldsh2535.ecommerceapp.SignIn.LoginFragment
import com.weldsh2535.ecommerceapp.SignIn.RegisterFragment

class ViewPagerAdapter(fm: FragmentManager, var tabCount: Int): FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LoginFragment()
            1 -> RegisterFragment()
            else -> LoginFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }


    override fun getPageTitle(position: Int): CharSequence? {
        if (position == 0){
            return "Login "
        }else{
            return "Register "
        }
    }
}
