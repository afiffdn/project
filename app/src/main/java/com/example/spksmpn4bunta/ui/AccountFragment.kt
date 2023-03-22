package com.example.spksmpn4bunta.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spksmpn4bunta.R
import com.example.spksmpn4bunta.databinding.FragmentAccountBinding
import com.example.spksmpn4bunta.viewmodel.AccountFragmentViewModel
import com.example.spksmpn4bunta.viewmodel.DataDiriViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel :AccountFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccountBinding.inflate(inflater,container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MainFragment.currentPage = R.id.accountFragment
        viewModel.getUserPref()
        viewModel.userPref.observe(viewLifecycleOwner) {

            if (it.sebagai == "siswa") {
                binding.btnAktivasiSiswa.visibility = View.GONE
            }
        }

        binding.apply {
            btnDataDiri.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_dataDiriFragment2)
            }
            btnAktivasiSiswa.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_isActiveFragment)
            }
            btnEditAkun.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_editAcountFragment3)
            }

        }
    }

}