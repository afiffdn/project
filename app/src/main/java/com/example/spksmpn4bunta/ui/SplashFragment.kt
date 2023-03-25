package com.example.spksmpn4bunta.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spksmpn4bunta.R
import com.example.spksmpn4bunta.databinding.FragmentSplashBinding
import com.example.spksmpn4bunta.viewmodel.RangkingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RangkingViewModel by viewModels()
    private var token:String = "def_token"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getToken()
        viewModel.getToken.observe(viewLifecycleOwner) {
            token = it.token
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingFinished() && token!= "def_token") {
                findNavController().navigate(R.id.action_splashFragment2_to_mainFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment2_to_onBoardFragment)
            }
        }, 3000)
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireContext().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }



}

