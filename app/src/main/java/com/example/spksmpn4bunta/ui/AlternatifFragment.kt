package com.example.spksmpn4bunta.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.spksmpn4bunta.R
import com.example.spksmpn4bunta.databinding.FragmentAlternatifBinding
import com.example.spksmpn4bunta.databinding.FragmentDataDiriBinding
import com.example.spksmpn4bunta.model.Status
import com.example.spksmpn4bunta.model.alternatif.PostAlternatifReq
import com.example.spksmpn4bunta.viewmodel.AlternatifViewModel
import com.example.spksmpn4bunta.viewmodel.DataDiriViewModel
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlternatifFragment : Fragment() {
    private var _binding: FragmentAlternatifBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AlternatifViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAlternatifBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getToken()
        viewModel.getUserPref()
        viewModel.userPref.observe(viewLifecycleOwner) {
            if (it.sebagai == "admin") {
                binding.llAlternatif.visibility = View.GONE
                binding.tvMaaf.visibility = View.VISIBLE
            }
        }
        viewModel.getToken.observe(viewLifecycleOwner) {
            var token = it.token
            binding.btnSmstr1.setOnClickListener {
                val reqData = PostAlternatifReq(
                    binding.etBhsIndo.text.toString().toInt(),
                    binding.etBhsIng.text.toString().toInt(),
                    binding.etBiologi.text.toString().toInt(),
                    binding.etEkonomi.text.toString().toInt(),
                    binding.etFisika.text.toString().toInt(),
                    binding.etIzin.text.toString().toInt(),
                    binding.etKetBhsIndo.text.toString().toInt(),
                    binding.etKetBhsIng.text.toString().toInt(),
                    binding.etKetBiologi.text.toString().toInt(),
                    binding.etKetEkonomi.text.toString().toInt(),
                    binding.etKetFisika.text.toString().toInt(),
                    binding.etKetKimia.text.toString().toInt(),
                    binding.etKetMtk.text.toString().toInt(),
                    binding.etKetPendAgama.text.toString().toInt(),
                    binding.etPendKetPancasila.text.toString().toInt(),
                    binding.etKetPenjas.text.toString().toInt(),
                    binding.etKetPrakarya.text.toString().toInt(),
                    binding.etKetSejarahIndo.text.toString().toInt(),
                    binding.etKetSeniBudaya.text.toString().toInt(),
                    binding.etKetSosiologi.text.toString().toInt(),
                    binding.etKimia.text.toString().toInt(),
                    binding.etMtk.text.toString().toInt(),
                    binding.etOlahraga.text.toString().toInt(),
                    binding.etPendAgama.text.toString().toInt(),
                    binding.etPendPancasila.text.toString().toInt(),
                    binding.etPenjas.text.toString().toInt(),
                    binding.etPmr.text.toString().toInt(),
                    binding.etPrakarya.text.toString().toInt(),
                    binding.etPramuka.text.toString().toInt(),
                    binding.etSakit.text.toString().toInt(),
                    binding.etSejarahIndo.text.toString().toInt(),
                    binding.etSemester.text.toString().toInt(),
                    binding.etSeniBudaya.text.toString().toInt(),
                    binding.etSikapSosial.text.toString().toInt(),
                    binding.etSikapSpiritual.text.toString().toInt(),
                    binding.etSosiologi.text.toString().toInt(),
                    binding.etTanpaKet.text.toString().toInt()
                )
                viewModel.postNilai(token,reqData)
            }

        }

        val progressDialog = ProgressDialog(requireActivity())
        viewModel.postNilai.observe(viewLifecycleOwner){ resources ->
            when(resources.status){
                Status.LOADING ->{
                    progressDialog.setMessage("Loading")
                    progressDialog.show()
                }
                Status.SUCCESS ->{
                    Toast.makeText(requireContext(), "data berhasil disimpan", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(), "data gagal disimpan", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                }
            }
        }


    }


}