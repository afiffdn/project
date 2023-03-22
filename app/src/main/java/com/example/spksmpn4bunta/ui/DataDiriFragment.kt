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
import androidx.navigation.fragment.navArgs
import com.example.spksmpn4bunta.R
import com.example.spksmpn4bunta.databinding.FragmentDataDiriBinding
import com.example.spksmpn4bunta.model.datadiri.DataDiriReq
import com.example.spksmpn4bunta.model.Status
import com.example.spksmpn4bunta.viewmodel.DataDiriViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataDiriFragment : Fragment() {
    private var _binding: FragmentDataDiriBinding? = null
    private val binding get() = _binding!!
    private val viewModel : DataDiriViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDataDiriBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getToken()
        val nisn = binding.etNisn.text
        val nama = binding.etNama.text
        val alamat = binding.etAlamat.text
        val nip = binding.etNip.text
        val jabatan = binding.etjabatan.text


        val choose  = binding.sAagama
        val list = ArrayList<String>()
        list.add("islam")
        list.add("hindu")
        list.add("kristen")
        list.add("konghucu")
        list.add("budha")

        val chooseKelas= binding.sKelas
        val listKelas = ArrayList<String>()
        listKelas.add("3a")
        listKelas.add("3b")
        listKelas.add("3c")
        listKelas.add("3d")
        listKelas.add("3e")
        listKelas.add("3f")


        val chooseGender = binding.sgender
        val listGender = ArrayList<String>()
        listGender.add("Pria")
        listGender.add("Wanita")

        val adaptor = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,list)
        choose.setAdapter(adaptor)

        val adaptorKelas = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,listKelas)
        chooseKelas.setAdapter(adaptorKelas)

        val adaptorGender = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,listGender)
        chooseGender.setAdapter(adaptorGender)


        viewModel.getUserPref()
        viewModel.userPref.observe(viewLifecycleOwner) {

            if (it.sebagai == "admin") {
                binding.tiNisn.visibility = View.GONE
                binding.tiAlamat.visibility = View.GONE
                binding.tilGender.visibility = View.GONE
                binding.tilAgama.visibility = View.GONE
                binding.tilKelas.visibility = View.GONE
                binding.tiNip.visibility = View.VISIBLE
                binding.tiJabatan.visibility = View.VISIBLE
            }
        }
        viewModel.getToken.observe(viewLifecycleOwner){ value->
            var token = " "
            token = value.token
        binding.btnTambah.setOnClickListener {
                val reqdata = DataDiriReq(
                    nisn.toString(),
                    nama.toString(),
                    chooseGender.text.toString(),
                    choose.text.toString(),
                    chooseKelas.text.toString(),
                    alamat.toString(),
                    nip.toString(),
                    jabatan.toString()
                )
                viewModel.postDataDiri(token,reqdata)
            }
            binding.btnEdit.setOnClickListener{
                val reqdata = DataDiriReq(
                    nisn.toString(),
                    nama.toString(),
                    chooseGender.text.toString(),
                    choose.text.toString(),
                    chooseKelas.text.toString(),
                    alamat.toString(),
                    nip.toString(),
                    jabatan.toString()
                )
                viewModel.putDataDiri(token,reqdata)
            }

        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val progressDialog = ProgressDialog(requireActivity())
        viewModel.postDataDiri.observe(viewLifecycleOwner){ resources ->
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


        viewModel.putDataDiri.observe(viewLifecycleOwner){ resources ->
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
                    Toast.makeText(requireContext(), "data gagal disimpan", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                }
            }
        }


    }

}