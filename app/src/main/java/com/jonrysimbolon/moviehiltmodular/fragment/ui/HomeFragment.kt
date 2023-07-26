package com.jonrysimbolon.moviehiltmodular.fragment.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.moviehiltmodular.fragment.viewmodel.HomeViewModel
import com.jonrysimbolon.navigation.R
import com.jonrysimbolon.navigation.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun FragmentHomeBinding.initialize() {}
    override fun getViewModel() = HomeViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().showToast(R.string.movies)
        val toDetailDirection = HomeFragmentDirections
        binding.detail.setOnClickListener {
            getViewModel().navigate(

            )
        }
    }
}