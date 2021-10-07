package com.example.gdscdwp.authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.gdscdwp.R
import com.example.gdscdwp.databinding.FragmentLoginBinding
import com.example.gdscdwp.discover.DiscoverViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.goBack.observe(viewLifecycleOwner, {
            if (it) {
                goBack()
                viewModel.setEventGoBackToFalse()
            }
        })

        return binding.root
    }

    private fun goBack() {

        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)

    }


}