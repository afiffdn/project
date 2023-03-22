package com.example.spksmpn4bunta.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spksmpn4bunta.R
import com.example.spksmpn4bunta.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        var currentPage = 0
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.menuNavigation.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.homeFragment -> {

                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.main_fragment_host, HomeFragment())
                        ?.commit()

                    true
                }

//                R.id.notificationFragment -> {
//                    activity?.supportFragmentManager?.beginTransaction()
//                        ?.replace(R.id.main_fragment_host, NotificationFragment())
//                        ?.commit()
//
//                    true
//                }

                R.id.alternatifFragment -> {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.main_fragment_host, AlternatifFragment())
                        ?.commit()

                    true
                }

                R.id.rangkingFragment -> {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.main_fragment_host, RangkingFragment())
                        ?.commit()

                    true
                }

                R.id.accountFragment -> {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.main_fragment_host, AccountFragment())
                        ?.commit()

                    true
                }

                else -> false
            }
        }

        binding.menuNavigation.selectedItemId = if (currentPage == 0) {
            R.id.homeFragment
        } else {
            currentPage
        }
    }

}