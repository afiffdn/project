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
            viewModel.getDetail(token,id)
            binding.btnSmstr1.setOnClickListener {
                viewModel.getDetail(token,id)
            }
            binding.smster2.setOnClickListener {
                viewModel.getDetail2(token, id)
            }

        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        val progressDialog = ProgressDialog(requireActivity())
        viewModel.getDetail.observe(viewLifecycleOwner){ resources ->
            when(resources.status){
                Status.LOADING ->{
                    progressDialog.setMessage("Loading")
                    progressDialog.show()
                }
                Status.SUCCESS ->{
                    binding.apply {
                        tvNama.text = resources.data?.nama
                        tvNisn.text = resources.data?.nisn
                        tvKelas.text = resources.data?.kelas
                        tvNilaiAgama.text = resources.data?.pend_agama.toString()
                        tvNilaiPancasila.text = resources.data?.pend_pancasila.toString()
                        tvNilaiBahasaIndo.text = resources.data?.bahasa_indo.toString()
                        tvNilaiMatematika.text = resources.data?.mtk.toString()
                        tvNilaiSejarahIndo.text = resources.data?.sejarah_indo.toString()
                        tvNilaiBhsIng.text = resources.data?.bahasa_ing.toString()
                        tvNilaiSeniBudaya.text = resources.data?.seni_budaya.toString()
                        tvNilaiPenjas.text = resources.data?.penjas.toString()
                        tvNilaiPrakarya.text = resources.data?.prakarya_dan_kwh.toString()
                        tvNilaiBiologi.text = resources.data?.biologi.toString()
                        tvNilaiFisika.text = resources.data?.fisika.toString()
                        tvNilaiKimia.text = resources.data?.kimia.toString()
                        tvNilaiSosiologi.text = resources.data?.sosiologi.toString()
                        tvNilaiEkonomi.text = resources.data?.ekonomi.toString()
                        tvNilaiKetAgama.text = resources.data?.ket_pend_agama.toString()
                        tvNilaiKetPancasila.text = resources.data?.ket_pend_pancasila.toString()
                        tvNilaiKetBahasaIndo.text = resources.data?.ket_bahasa_indo.toString()
                        tvNilaiKetMatematika.text = resources.data?.ket_matematika.toString()
                        tvNilaiKetSejarahIndo.text = resources.data?.ket_sejarah_indo.toString()
                        tvNilaiKetBhsIng.text = resources.data?.ket_bahasa_ing.toString()
                        tvNilaiKetSeniBudaya.text = resources.data?.ket_seni_budaya.toString()
                        tvNilaiKetPenjas.text = resources.data?.ket_penjas.toString()
                        tvNilaiKetPrakarya.text = resources.data?.ket_prakarya_dan_kwh.toString()
                        tvNilaiKetBiologi.text = resources.data?.ket_biologi.toString()
                        tvNilaiKetFisika.text = resources.data?.ket_fisika.toString()
                        tvNilaiKetKimia.text = resources.data?.ket_kimia.toString()
                        tvNilaiKetSosiologi.text = resources.data?.ket_sosiologi.toString()
                        tvNilaiKetEkonomi.text = resources.data?.ket_ekonomi.toString()
                        tvNilaiPramuka.text = resources.data?.pramuka.toString()
                        tvNilaiPmr.text = resources.data?.pmr.toString()
                        tvNilaiOlahraga.text = resources.data?.olahraga.toString()
                        tvNilaiSikapSpiritual.text = resources.data?.sikap_spiritual.toString()
                        tvNilaiSikapSosial.text = resources.data?.sikap_sosial.toString()
                        tvNilaiSakit.text = resources.data?.sakit.toString()
                        tvNilaiIzin.text = resources.data?.izin.toString()
                        tvNilaiTanpaKet.text = resources.data?.tanpa_keterangan.toString()
                    }

                    progressDialog.dismiss()
                }
                Status.ERROR ->{
                    progressDialog.dismiss()
                }
            }
        }
        viewModel.getDetail2.observe(viewLifecycleOwner){ resources ->
            when(resources.status){
                Status.LOADING ->{
                    progressDialog.setMessage("Loading")
                    progressDialog.show()
                }
                Status.SUCCESS ->{
                    binding.apply {
                        tvNama.text = resources.data?.nama
                        tvNisn.text = resources.data?.nisn
                        tvKelas.text = resources.data?.kelas
                        tvNilaiAgama.text = resources.data?.pend_agama.toString()
                        tvNilaiPancasila.text = resources.data?.pend_pancasila.toString()
                        tvNilaiBahasaIndo.text = resources.data?.bahasa_indo.toString()
                        tvNilaiMatematika.text = resources.data?.mtk.toString()
                        tvNilaiSejarahIndo.text = resources.data?.sejarah_indo.toString()
                        tvNilaiBhsIng.text = resources.data?.bahasa_ing.toString()
                        tvNilaiSeniBudaya.text = resources.data?.seni_budaya.toString()
                        tvNilaiPenjas.text = resources.data?.penjas.toString()
                        tvNilaiPrakarya.text = resources.data?.prakarya_dan_kwh.toString()
                        tvNilaiBiologi.text = resources.data?.biologi.toString()
                        tvNilaiFisika.text = resources.data?.fisika.toString()
                        tvNilaiKimia.text = resources.data?.kimia.toString()
                        tvNilaiSosiologi.text = resources.data?.sosiologi.toString()
                        tvNilaiEkonomi.text = resources.data?.ekonomi.toString()
                        tvNilaiKetAgama.text = resources.data?.ket_pend_agama.toString()
                        tvNilaiKetPancasila.text = resources.data?.ket_pend_pancasila.toString()
                        tvNilaiKetBahasaIndo.text = resources.data?.ket_bahasa_indo.toString()
                        tvNilaiKetMatematika.text = resources.data?.ket_matematika.toString()
                        tvNilaiKetSejarahIndo.text = resources.data?.ket_sejarah_indo.toString()
                        tvNilaiKetBhsIng.text = resources.data?.ket_bahasa_ing.toString()
                        tvNilaiKetSeniBudaya.text = resources.data?.ket_seni_budaya.toString()
                        tvNilaiKetPenjas.text = resources.data?.ket_penjas.toString()
                        tvNilaiKetPrakarya.text = resources.data?.ket_prakarya_dan_kwh.toString()
                        tvNilaiKetBiologi.text = resources.data?.ket_biologi.toString()
                        tvNilaiKetFisika.text = resources.data?.ket_fisika.toString()
                        tvNilaiKetKimia.text = resources.data?.ket_kimia.toString()
                        tvNilaiKetSosiologi.text = resources.data?.ket_sosiologi.toString()
                        tvNilaiKetEkonomi.text = resources.data?.ket_ekonomi.toString()
                        tvNilaiPramuka.text = resources.data?.pramuka.toString()
                        tvNilaiPmr.text = resources.data?.pmr.toString()
                        tvNilaiOlahraga.text = resources.data?.olahraga.toString()
                        tvNilaiSikapSpiritual.text = resources.data?.sikap_spiritual.toString()
                        tvNilaiSikapSosial.text = resources.data?.sikap_sosial.toString()
                        tvNilaiSakit.text = resources.data?.sakit.toString()
                        tvNilaiIzin.text = resources.data?.izin.toString()
                        tvNilaiTanpaKet.text = resources.data?.tanpa_keterangan.toString()
                    }

                    progressDialog.dismiss()
                }
                Status.ERROR ->{
                    progressDialog.dismiss()
                }
            }
        }


    }


}