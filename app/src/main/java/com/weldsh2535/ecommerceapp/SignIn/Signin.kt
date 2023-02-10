package com.weldsh2535.ecommerceapp.SignIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.weldsh2535.ecommerceapp.Adapter.ViewPagerAdapter
import com.weldsh2535.ecommerceapp.R
import com.weldsh2535.ecommerceapp.databinding.ActivitySigninBinding

class Signin : AppCompatActivity() {
    private lateinit var binding:ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabLayout()
        setupViewPager()


    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, binding.tabLayout.tabCount)
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        }

    }

    private fun setupTabLayout() {
        binding.tabLayout.apply {
            addTab(this.newTab().setText("Login"))
            addTab(this.newTab().setText("Register"))

            // tabGravity = TabLayout.GRAVITY_FILL

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let {
                        binding.viewPager.currentItem = it
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }

    }
}