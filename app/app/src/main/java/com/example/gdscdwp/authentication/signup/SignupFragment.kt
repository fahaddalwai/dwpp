package com.example.gdscdwp.authentication.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gdscdwp.MainActivity
import com.example.gdscdwp.R
import com.example.gdscdwp.authentication.isValidEmail
import com.example.gdscdwp.authentication.login.LoginViewModel
import com.example.gdscdwp.data.AuthRepository
import com.example.gdscdwp.database.CatDatabase
import com.example.gdscdwp.database.CatDatabase.Companion.getInstance
import com.example.gdscdwp.databinding.FragmentLoginBinding
import com.example.gdscdwp.databinding.FragmentSignupBinding
import com.example.gdscdwp.network.AuthApi
import com.example.gdscdwp.network.CatResponseApi
import com.example.gdscdwp.userPreferences.UserPreference


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: SignupViewModel
    private lateinit var repository: AuthRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        repository=AuthRepository(AuthApi.retrofitService,
            requireContext()
        )
        val viewModelFactory = SignupViewModelFactory(repository)


        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(SignupViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this




        viewModel.goBack.observe(viewLifecycleOwner, {
            if (it) {
                goBack()
                viewModel.setEventGoBackToFalse()
            }
        })

       viewModel.eventMainActivity.observe(viewLifecycleOwner, {
           if(it){
               goToMainActivity()
               viewModel.setEventMainActivityFalse()
           }
       })


        return binding.root
    }

    private fun goBack() {
        findNavController().navigate(R.id.action_signupFragment_to_welcomeFragment)
    }

    private fun goToMainActivity(){
        Log.i("moved to next activity","true")
       findNavController().navigate(R.id.action_signupFragment_to_mainActivity)
    }
}



