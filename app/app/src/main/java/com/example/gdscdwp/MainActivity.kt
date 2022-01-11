package com.example.gdscdwp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.gdscdwp.R
import com.example.gdscdwp.databinding.ActivityMainBinding
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.*
import androidx.navigation.Navigation.findNavController

import androidx.navigation.fragment.findNavController
import com.google.android.material.internal.ContextUtils.getActivity
import de.hdodenhof.circleimageview.CircleImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.gdscdwp.data.AuthRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.first


class MainActivity : AppCompatActivity() {


    private lateinit var toolbar: Toolbar
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController:NavController


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)



        navHostFragment =  supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavigation = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)


        toolbar=binding.toolbar







        val appBarConfiguration = AppBarConfiguration(setOf(R.id.profileFragment2,R.id.recommendationsFragment2,R.id.discoverFragment))
        setSupportActionBar(toolbar)


        toolbar.setupWithNavController(navController,appBarConfiguration)





//
//        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.fire_icon)
//        binding.toolbar.overflowIcon = drawable
    }






//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp(appBarConfiguration)
//    }


    }

