package com.example.spksmpn4bunta.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spksmpn4bunta.adapter.ActivatedAdapater
import com.example.spksmpn4bunta.databinding.FragmentIsActiveBinding
import com.example.spksmpn4bunta.model.aktivasi.GetUsersRespItem
import com.example.spksmpn4bunta.model.Status
import com.example.spksmpn4bunta.viewmodel.ActivatedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IsActiveFragment : Fragment() {
    private var _binding: FragmentIsActiveBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ActivatedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIsActiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getToken()
        viewModel.getToken.observe(viewLifecycleOwner) {
            var token = it.token
            viewModel.getUsers(token)
            binding.btnAktivasi.setOnClickListener {
                val id = binding.etActivated.text
                viewModel.putActivated(token, id.toString().toInt())

            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val progressDialog = ProgressDialog(requireActivity())
        viewModel.getUsers.observe(viewLifecycleOwner) { resources ->
            when (resources.status) {
                Status.LOADING -> {
                    progressDialog.setMessage("Loading")
                    progressDialog.show()
                }
                Status.SUCCESS -> {

                    progressDialog.dismiss()
                    val adapter = ActivatedAdapater(object : ActivatedAdapater.onClickListener {
                        override fun onClickItem(data: GetUsersRespItem) {

                        }

                    })
                    adapter.submitData(resources.data)
                    binding.rvActivated.adapter = adapter
                    progressDialog.dismiss()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "data gagal disimpan", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                }
            }
        }

        viewModel.putActivated.observe(viewLifecycleOwner) { resources ->
            when (resources.status) {
                Status.LOADING -> {
                    progressDialog.setMessage("Loading")
                    progressDialog.show()
                }
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "user berhasil diaktivasi", Toast.LENGTH_SHORT)
                        .show()

                    progressDialog.dismiss()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "gagal aktivasi", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                }
            }
        }
    }
}