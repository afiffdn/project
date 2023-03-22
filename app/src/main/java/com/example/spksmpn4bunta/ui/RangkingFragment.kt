package com.example.spksmpn4bunta.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spksmpn4bunta.R
import com.example.spksmpn4bunta.adapter.RangkingAdapter
import com.example.spksmpn4bunta.databinding.FragmentMainBinding
import com.example.spksmpn4bunta.databinding.FragmentRangkingBinding
import com.example.spksmpn4bunta.model.Status
import com.example.spksmpn4bunta.model.rangking.GetNilaiSawRespItem
import com.example.spksmpn4bunta.viewmodel.RangkingViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RangkingFragment : Fragment() {
    private var _binding: FragmentRangkingBinding? = null
    private val binding get() = _binding!!
    private val viewModel : RangkingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRangkingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MainFragment.currentPage = R.id.rangkingFragment
        viewModel.getToken()
        viewModel.getToken.observe(viewLifecycleOwner){ value->
            var token = " "
            token = value.token
            binding.btnRangking.setOnClickListener {
                viewModel.getNilaiSaw(token)
            }
        }

        viewModel.getUserPref()
        viewModel.userPref.observe(viewLifecycleOwner) {
            if (it.sebagai == "siswa") {
                binding.llRangking.visibility = View.GONE
                binding.btnRangking.visibility = View.GONE
                binding.btnCetak.visibility = View.GONE
                binding.tvMaaf.visibility = View.VISIBLE
            }
        }

        val progressDialog = ProgressDialog(requireActivity())
        viewModel.getSaw.observe(viewLifecycleOwner){ resources ->
            when(resources.status){
                Status.LOADING ->{
                    progressDialog.setMessage("Loading")
                    progressDialog.show()
                }
                Status.SUCCESS ->{
                    Toast.makeText(requireContext(), "data berhasil disimpan", Toast.LENGTH_SHORT)
                        .show()
                    val adapter = RangkingAdapter(object : RangkingAdapter.onClickListener{
                        override fun onClickItem(data: GetNilaiSawRespItem) {
                            MainFragment.currentPage = R.id.rangkingFragment
                            val direct = MainFragmentDirections.actionMainFragmentToDetailFragment3(data.users_id.toInt())
                            findNavController().navigate(direct)
//                            val id = bundleOf("id" to data.users_id.toInt() )
//                        findNavController().navigate(R.id.action_mainFragment_to_detailFragment3,id)
                        }

                    })
                    adapter.submitData(resources.data)
                    binding.rvRangking.adapter = adapter
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