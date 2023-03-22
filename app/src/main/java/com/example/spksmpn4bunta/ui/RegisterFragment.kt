package com.example.spksmpn4bunta.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spksmpn4bunta.R
import com.example.spksmpn4bunta.databinding.FragmentRegisterBinding
import com.example.spksmpn4bunta.model.auth.RegistReq
import com.example.spksmpn4bunta.model.Status
import com.example.spksmpn4bunta.viewmodel.RegistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel : RegistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressDialog = ProgressDialog(requireActivity())
        viewModel.userRegister.observe(viewLifecycleOwner){ resources ->
            when(resources.status){
                Status.LOADING ->{
                    progressDialog.setMessage("Loading")
                    progressDialog.show()
                }
                Status.SUCCESS ->{
                    Toast.makeText(requireContext(), "Registrasi berhasil", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(), "Registrasi gagal", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                }
            }
        }

        val choose = binding.etSebagai
        val list = ArrayList<String>()
        list.add("siswa")
        list.add("admin")

        val adaptor = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,list)
        choose.setAdapter(adaptor)

        binding.apply {
            btnRegistrasi.setOnClickListener {
                regist()
            }
            tvMasukDiSini.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

    }

    private fun regist() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val sebagai = binding.etSebagai.text.toString()
        val registReq = RegistReq(username, password, sebagai)
        viewModel.registPost(registReq)

        if (username.isEmpty() || password.isEmpty() || sebagai.isEmpty()){
            Toast.makeText(requireContext(), "lengkapi data", Toast.LENGTH_SHORT).show()
        }
    }

}