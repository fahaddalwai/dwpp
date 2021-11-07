package com.example.gdscdwp.discover

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gdscdwp.R
import com.example.gdscdwp.databinding.FragmentEnlargedImageBinding


class EnlargedImageFragment : Fragment() {

    private lateinit var binding:FragmentEnlargedImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_enlarged_image,container,false)

        val args = EnlargedImageFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(activity).application


        binding.lifecycleOwner = this


        val viewModelFactory = EnlargedImageViewModelFactory(args.catUrl, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(EnlargedImageViewModel::class.java)




        return binding.root
    }


}