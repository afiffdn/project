package com.example.spksmpn4bunta.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spksmpn4bunta.R
import com.example.spksmpn4bunta.databinding.FragmentHomeBinding
import com.example.spksmpn4bunta.databinding.FragmentRegisterBinding
import com.example.spksmpn4bunta.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getUserPref()
        viewModel.userPref.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.hallo.text = getString(R.string.hallo, it.username)
                binding.role.text = getString(R.string.role, it.sebagai)
            }
        }

        binding.btnClose.setOnClickListener {
            AlertDialog.Builder(requireContext()).setMessage("apakah kamu ingin logout")
                .setPositiveButton("ya",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        viewModel.deleteDataStore()
                        findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
                    })
                .setNegativeButton("tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.cancel()
                }).create().show()

        }

    }
}