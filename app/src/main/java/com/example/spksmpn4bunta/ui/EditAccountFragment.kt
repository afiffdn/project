package com.example.spksmpn4bunta.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spksmpn4bunta.R
import com.example.spksmpn4bunta.databinding.FragmentAccountBinding
import com.example.spksmpn4bunta.databinding.FragmentDataDiriBinding
import com.example.spksmpn4bunta.databinding.FragmentEditAccountBinding
import com.example.spksmpn4bunta.datastore.Data
import com.example.spksmpn4bunta.model.Status
import com.example.spksmpn4bunta.model.auth.ChangeRegistReq
import com.example.spksmpn4bunta.viewmodel.DataDiriViewModel
import com.example.spksmpn4bunta.viewmodel.EditAkunViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditAcountFragment : Fragment() {
    private var _binding: FragmentEditAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel : EditAkunViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditAccountBinding.inflate(inflater,container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getToken()
        viewModel.getToken.observe(viewLifecycleOwner){
            viewModel.getUserPref()
            var token = it.token
            binding.btnUbah.setOnClickListener {
                val reqData = ChangeRegistReq(
                    binding.etUsernameBaru.text.toString(),
                    binding.etPasswordBaru.text.toString()
                )
                viewModel.putRegist(token,reqData)
                val username = binding.etUsernameBaru.text.toString()
                val password = binding.etPasswordBaru .text.toString()
                viewModel.userPref.observe(viewLifecycleOwner){
                    val data = Data(username,password,it.sebagai)
                    viewModel.saveUserPref(data)
                }

            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        val progressDialog = ProgressDialog(requireActivity())
        viewModel.putRegist.observe(viewLifecycleOwner){ resources ->
            when(resources.status){
                Status.LOADING ->{
                    progressDialog.setMessage("Loading")
                    progressDialog.show()
                }
                Status.SUCCESS ->{
                    Toast.makeText(requireContext(), "data berhasil diubah", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(), "data gagal diubah", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                }
            }
        }

    }

}