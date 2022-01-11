package com.example.gdscdwp.profile

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.gdscdwp.R
import com.example.gdscdwp.data.AuthRepository
import com.example.gdscdwp.databinding.FragmentProfileBinding
import com.example.gdscdwp.network.AuthApi


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var repository: AuthRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)



        setHasOptionsMenu(true)

        repository = AuthRepository(
            AuthApi.retrofitService,
            requireContext()
        )
        val viewModelFactory = ProfileViewModelFactory(repository)


        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ProfileViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this


        viewModel.currentUser.observe(viewLifecycleOwner,{
            Log.i("is it working",it.toString())
                viewModel.putUserValue()
            }
        )

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_navigation_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

}