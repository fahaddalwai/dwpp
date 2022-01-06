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
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.internal.ContextUtils.getActivity
import de.hdodenhof.circleimageview.CircleImageView
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.twentyFourSeven.android.apb.commons.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var toolbar: Toolbar
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var currentNavController: NavController
    private lateinit var binding:ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState

        bottomNavigation = binding.bottomNavigationView







            toolbar=binding.toolbar
            setSupportActionBar(toolbar)
            val navController = this.findNavController(R.id.myNavHostFragment)

            NavigationUI.setupActionBarWithNavController(this,navController)



            val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.fire_icon)
            binding.toolbar.overflowIcon = drawable



        }



    @RequiresApi(Build.VERSION_CODES.S)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setupBottomNavigationBar() {
        bottomNavigation = binding.bottomNavigationView

        val navGraphIds =
            listOf(R.navigation.discover_navigation, R.navigation.recommendation_navigation, R.navigation.profile_navigation)

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.myNavHostFragment,
            intent = intent
        )
        controller.observe(this, { navController ->
            currentNavController = navController
        })
    }


    }