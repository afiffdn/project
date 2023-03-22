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
import com.example.spksmpn4bunta.databinding.FragmentLoginBinding
import com.example.spksmpn4bunta.model.auth.LoginReq
import com.example.spksmpn4bunta.model.Status
import com.example.spksmpn4bunta.model.Users
import com.example.spksmpn4bunta.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LoginViewModel by viewModels()
    private var token:String= ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val choose = binding.etSebagai
        val list = ArrayList<String>()
        list.add("siswa")
        list.add("admin")

        val adaptor = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,list)
        choose.setAdapter(adaptor)

        binding.apply {
            btnLogin.setOnClickListener{
                login()
            }
            tvDaftarDiSini.setOnClickListener{
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)

            }
        }
        val progressDialog = ProgressDialog(requireActivity())
        viewModel.userLogin.observe(viewLifecycleOwner){ resources ->
            when(resources.status){
                Status.LOADING ->{
                    progressDialog.setMessage("Loading")
                    progressDialog.show()
                }
                Status.SUCCESS ->{
                    Toast.makeText(requireContext(), "Login berhasil", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                    if (resources.data?.token != null){
                        token = resources.data.token

                        val data2 = Users(token,resources.data.id_users)
                        viewModel.setUserToken(data2)
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    }

                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(), "Login gagal", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                }
            }
        }

    }

    private fun login() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val sebagai = binding.etSebagai.text.toString()
        val loginReq = LoginReq(username,password,sebagai)
        viewModel.loginPost(loginReq)
        viewModel.saveUserPref(loginReq)
    }
}