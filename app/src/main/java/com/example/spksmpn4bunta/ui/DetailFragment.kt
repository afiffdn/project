package com.example.spksmpn4bunta.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spksmpn4bunta.databinding.FragmentDetailBinding
import com.example.spksmpn4bunta.model.Status
import com.example.spksmpn4bunta.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getToken()
        viewModel.getToken.observe(viewLifecycleOwner){
            val token = it.token
            val id = args.id
            binding.btnSmstr1.setOnClickListener {
                viewModel.getDetail(token,id)
                val progressDialog = ProgressDialog(requireActivity())
                viewModel.getDetail.observe(viewLifecycleOwner){ resources ->
                    val nama = resources.data
                    Log.d("DetailFragment","nama : $nama")
                    when(resources.status){
                        Status.LOADING ->{
                            progressDialog.setMessage("Loading")
                            progressDialog.show()
                        }
                        Status.SUCCESS ->{
//                            binding.tvNama.text = resources.data?.nama
//                            binding.tvNilaiTanpaKet.text = resources.data?.tanpa_keterangan.toString()
                            progressDialog.dismiss()
                        }
                        Status.ERROR ->{
                            Toast.makeText(requireContext(), "data gagal disimpan", Toast.LENGTH_SHORT)
                                .show()
                            binding.apply {
//                                tvNama.text = resources.data?.nama.toString()
//                                tvNisn.text = resources.data?.nisn.toString()
                            }
                            progressDialog.dismiss()
                        }
                    }
                }

            }
        }


    }


}